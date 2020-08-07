package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;

@Dao
public abstract class AgendaDao {


    @Transaction
    @Query("SELECT * FROM tarefas WHERE data = :data ")
    abstract public Flowable<List<Marcacao>> obterMarcacoes(long data);


    //TODO: completar a query com a data e o id do Utilizador

    @Query("SELECT  " + /*data,*/
            "CASE WHEN sincronizado != 1 THEN " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " " +
            "WHEN data = date('now') AND sincronizado = 1 THEN  " + Identificadores.Sincronizacao.SINCRONIZADO + "  " +
            "WHEN data < date('now') AND sincronizado = 1 THEN  " + Identificadores.Sincronizacao.TRANCADO + "  " +
            "ELSE " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " END as estado " +
            "FROM (" +
            "SELECT data, COUNT(data) as numeroTarefas, sincronizado, idUtilizador " +
            "FROM tarefas as trf " +
            "LEFT JOIN (SELECT idTarefa, sincronizado FROM resultados) as res ON trf.idTarefa = res.idTarefa " +
            "GROUP BY data, idUtilizador" +
            ") as datas " +
            "WHERE data = :data " +
            "LIMIT 1 ")
    abstract public Flowable<Integer> obterCompletude(long data);

    @Query("SELECT DISTINCT data FROM tarefas WHERE idUtilizador = :idUtilizador ")
    abstract public Flowable<List<Date>> obterDatas(String idUtilizador);
}
