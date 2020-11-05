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
    @Query("SELECT *, " +
            "CASE WHEN total = ct_sinc THEN " + Identificadores.Sincronizacao.SINCRONIZADO +" " +
            "WHEN total = ct_nao_sinc THEN " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " " +
            "ELSE " + Identificadores.Sincronizacao.SEM_SINCRONIZACAO + " END as estado_sinc " +
            "FROM tarefas as trf " +
            "LEFT JOIN (" +
            "SELECT idTarefa, count(*) AS total, " +
            "sum(case when sincronizado = 1 then 1 else 0 end) AS ct_sinc, " +
            "sum(case when sincronizado = 0 then 1 else 0 end) AS ct_nao_sinc " +
            "FROM resultados WHERE id = " + Identificadores.Resultados.ID_ATIVIDADE_PENDENTE + " " +
            "GROUP BY idTarefa" +
            ") as sinc ON trf.idTarefa = sinc.idTarefa " +
            "WHERE data = :data AND idUtilizador = :idUtilizador")
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


    @Query("SELECT COUNT(*) FROM resultados WHERE sincronizado = 0 AND idTarefa IN(SELECT idTarefa FROM tarefas WHERE idUtilizador =:idUtilizador AND data < :data)")
    abstract public Maybe<Integer> obterEstadoPendencias(String idUtilizador, long data);


    @Query("SELECT data, SUM(cont) as ct_pendencias_upload " +
            "FROM(" +
            "SELECT data, idUtilizador, IFNULL(ct,0) as cont FROM tarefas as trf " +
            "LEFT JOIN (SELECT idTarefa, COUNT(id) as ct FROM resultados WHERE sincronizado = 0 GROUP BY idTarefa) as res " +
            "ON trf.idTarefa = res.idTarefa) " +
            "WHERE idUtilizador = :idUtilizador " +
            "GROUP BY data  ")
    abstract public Maybe<List<Date>> obterDatas(String idUtilizador);



}
