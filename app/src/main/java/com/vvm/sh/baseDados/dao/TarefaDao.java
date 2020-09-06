package com.vvm.sh.baseDados.dao;


import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.ui.tarefa.modelos.TarefaDia;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public abstract class TarefaDao implements BaseDao<Tarefa> {


    @Transaction
    @Query("SELECT *, IFNULL(ct_anomalias, 0) as anomalias FROM tarefas as trf " +
            "LEFT JOIN (SELECT idTarefa, COUNT(id) as ct_anomalias FROM anomaliasResultado GROUP BY idTarefa) as anm ON trf.idTarefa = anm.idTarefa " +
            "WHERE trf.idTarefa = :idTarefa ")
    abstract public Flowable<TarefaDia> obterTarefaDia(int idTarefa);


    @Query("SELECT * FROM atividadeExecutadas WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<AtividadeExecutada>> obterAtividades(int idTarefa);

}
