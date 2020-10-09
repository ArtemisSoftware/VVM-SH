package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
public abstract class AgendaDao {


    @Transaction
    @Query("SELECT * FROM tarefas WHERE data = :data AND idUtilizador = :idUtilizador")
    abstract public Observable<List<Marcacao>> obterMarcacoes(String idUtilizador, long data);


    @Query("SELECT  " +
            "CASE " +
            "WHEN IFNULL(COUNT(data), 0) = 0 THEN " + Identificadores.Sincronizacao.SEM_SINCRONIZACAO + " " +
            "WHEN sincronizado != 1 THEN " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " " +
            "WHEN data = date('now') AND sincronizado = 1 THEN  " + Identificadores.Sincronizacao.SINCRONIZADO + "  " +
            "WHEN data < date('now') AND sincronizado = 1 THEN  " + Identificadores.Sincronizacao.TRANCADO + "  " +
            "ELSE " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " END as estado " +
            "FROM tarefas as trf " +
            "LEFT JOIN (SELECT idTarefa, sincronizado FROM resultados) as res ON trf.idTarefa = res.idTarefa " +
            "WHERE data = :data AND idUtilizador = :idUtilizador " +
            "GROUP BY data, idUtilizador " +
            "LIMIT 1 ")
    abstract public Observable<Integer> obterCompletude(String idUtilizador, long data);

    @Query("SELECT DISTINCT data FROM tarefas WHERE idUtilizador = :idUtilizador ")
    abstract public Maybe<List<Date>> obterDatas(String idUtilizador);



}
