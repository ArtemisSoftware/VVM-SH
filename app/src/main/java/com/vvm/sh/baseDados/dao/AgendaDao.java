package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.ui.agenda.modelos.DataAgendamento;
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




    //SELECT CASE WHEN IFNULL(COUNT(data), 0) = 0 THEN 'SEMSINC' WHEN total_ != sinc_ THEN 'NAO_SINC' WHEN data = date('now') AND total_ = sinc_ THEN 'SINC' WHEN data < date('now') AND total_ = sinc_ THEN 'TRANC' ELSE 'NAOSINC' END as estado FROM (SELECT data, idUtilizador, COUNT(*) as total_, sum(case when sinc> 0 then 1 else 0 end) AS sinc_, sum(case when naosinc> 0 then 1 else 0 end) AS naosinc_ FROM(SELECT *, count(*) AS total, sum(case when sincronizado= 1 then 1 else 0 end) AS sinc, sum(case when sincronizado= 0 then 1 else 0 end) AS naosinc FROM resultados GROUP BY idTarefa) as res LEFT JOIN (SELECT idTarefa, data, idUtilizador FROM tarefas) as trf ON res.idTarefa = trf.idTarefa GROUP BY data) WHERE data = 1602802800001 GROUP BY data, idUtilizador


    @Query("SELECT " +
            "CASE WHEN IFNULL(COUNT(data), 0) = 0 THEN " + Identificadores.Sincronizacao.SEM_SINCRONIZACAO + " " +
            "WHEN total_ != sinc_ THEN " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " " +
            "WHEN strftime('%Y-%m-%d', (data * 0.001), 'unixepoch') = date('now') AND total_ = sinc_ THEN " + Identificadores.Sincronizacao.SINCRONIZADO + "  " +
            "WHEN strftime('%Y-%m-%d', (data * 0.001), 'unixepoch') < date('now') AND total_ = sinc_ THEN " + Identificadores.Sincronizacao.TRANCADO + "  " +
            "ELSE " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " " +
            "END as estado " +
            "FROM (" +
            "SELECT data, idUtilizador, COUNT(*) as total_, " +
            "sum(case when sinc> 0 then 1 else 0 end) AS sinc_, " +
            "sum(case when naosinc> 0 then 1 else 0 end) AS naosinc_ " +
            "FROM(" +
            "SELECT *, count(*) AS total, " +
            "sum(case when sincronizado= 1 then 1 else 0 end) AS sinc, " +
            "sum(case when sincronizado= 0 then 1 else 0 end) AS naosinc " +
            "FROM resultados " +
            "GROUP BY idTarefa) as res " +
            "LEFT JOIN (SELECT idTarefa, data, idUtilizador FROM tarefas) as trf ON res.idTarefa = trf.idTarefa GROUP BY data) " +
            "WHERE data = :data AND idUtilizador =:idUtilizador GROUP BY data, idUtilizador")
    abstract public Observable<Integer> obterCompletude(String idUtilizador, long data);


    @Query("SELECT data, SUM(cont) as ct_pendencias_upload " +
            "FROM(" +
            "SELECT data, idUtilizador, IFNULL(ct,0) as cont FROM tarefas as trf " +
            "LEFT JOIN (SELECT idTarefa, COUNT(id) as ct FROM resultados WHERE sincronizado = 0 GROUP BY idTarefa) as res " +
            "ON trf.idTarefa = res.idTarefa) " +
            "WHERE idUtilizador = :idUtilizador " +
            "GROUP BY data  ")
    abstract public Maybe<List<DataAgendamento>> obterDatas(String idUtilizador);



}
