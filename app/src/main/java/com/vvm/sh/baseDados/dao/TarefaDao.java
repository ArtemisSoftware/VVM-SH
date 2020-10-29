package com.vvm.sh.baseDados.dao;


import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.ui.tarefa.modelos.TarefaDia;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public abstract class TarefaDao implements BaseDao<Tarefa> {

//
//    String query = "SELECT COUNT(atp_res.id)   ";
//    query += "FROM atividadesPendentes_resultado as atp_res ";
//
//    query += "OUTER LEFT JOIN (SELECT idTarefa, id FROM atividadesPendentes) as atp ON atp_res.id = atp.id  ";
//    query += "WHERE idAnomalia IS NOT NULL and idTarefa = ? ";


    @Transaction
    @Query("SELECT *, IFNULL(ct_anomalias, 0) as anomalias, existe_extintores, estadoBaixas, existeAnomalias " +
            "FROM tarefas as trf " +
            "LEFT JOIN (SELECT idTarefa, COUNT(id) as ct_anomalias FROM anomaliasResultado GROUP BY idTarefa) as anm ON trf.idTarefa = anm.idTarefa " +
            "LEFT JOIN (SELECT idTarefa, CASE WHEN IFNULL(COUNT(id), 0) > 0 THEN 1 ELSE 0 END existe_extintores  FROM parqueExtintores GROUP BY idTarefa) as prq_ext ON trf.idTarefa = prq_ext.idTarefa " +

            "LEFT JOIN(" +
            "SELECT atp.idTarefa as idTarefa, CASE WHEN IFNULL(COUNT (idEstado), 0) > 0 THEN 1 ELSE 0 END estadoBaixas " +
            "FROM atividadesPendentesResultado as atp_res " +
            "LEFT JOIN (SELECT idTarefa, id FROM atividadesPendentes) as atp ON atp_res.id = atp.id " +
            "WHERE (idEstado = " + Identificadores.Estados.ESTADO_EXECUTADO + " OR idEstado = " + Identificadores.Estados.ESTADO_NAO_EXECUTADO + ") " +
            "GROUP BY atp.idTarefa " +
            ") as atp ON trf.idTarefa = atp.idTarefa " +

            "LEFT JOIN(" +
            "SELECT atp.idTarefa as idTarefa, CASE WHEN IFNULL(COUNT(atp_res.id), 0) > 0 THEN 1 ELSE 0 END existeAnomalias " +
            "FROM atividadesPendentesResultado as atp_res " +
            "LEFT JOIN (SELECT idTarefa, id FROM atividadesPendentes) as atp ON atp_res.id = atp.id " +
            "WHERE idAnomalia > 0 GROUP BY atp.idTarefa " +
            ") as anom ON trf.idTarefa = anom.idTarefa " +
            "" +

            "WHERE trf.idTarefa = :idTarefa ")
    abstract public Observable<TarefaDia> obterTarefaDia(int idTarefa);


    @Query("SELECT * FROM atividadeExecutadas WHERE idTarefa = :idTarefa")
    abstract public Single<List<AtividadeExecutada>> obterAtividades(int idTarefa);



    @Query("UPDATE atividadesPendentesResultado " +
            "SET " +
            "idEstado = " + Identificadores.Estados.ESTADO_NAO_EXECUTADO +" , idAnomalia = :idAnomalia ,observacao = :observacao " +
            "WHERE id IN(SELECT id FROM  atividadesPendentes WHERE idTarefa = :idTarefa )  ")
    abstract public Completable atualizarAnomalia(int idTarefa, int idAnomalia, String observacao);



    @Query("INSERT INTO atividadesPendentesResultado(id, idEstado, idAnomalia, observacao) " +
            "SELECT atp.id, " +  Identificadores.Estados.ESTADO_NAO_EXECUTADO + " as estado, :idAnomalia as idAnomalia , :observacao as observacao " +
            "FROM atividadesPendentes as atp  " +
            "LEFT JOIN (SELECT id FROM  atividadesPendentesResultado) as atp_res ON  atp.id = atp_res.id " +
            "WHERE  atp_res.id IS NULL AND idTarefa = :idTarefa")
    abstract public Completable inserirAnomalia(int idTarefa, int idAnomalia, String observacao);
}
