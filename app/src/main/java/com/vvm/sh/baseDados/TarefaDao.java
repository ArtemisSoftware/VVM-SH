package com.vvm.sh.baseDados;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.ui.agenda.modelos.Tarefa;
import com.vvm.sh.ui.agenda.modelos.TarefaDia;

import java.util.List;

import io.reactivex.Single;

@Dao
public abstract class TarefaDao implements BaseDao<Tarefa>{

    @Insert
    abstract public Long inserirRegisto(Tarefa tarefa);


    @Transaction
    @Query("SELECT * FROM tarefas ")
    abstract public Single<List<TarefaDia>> obterTarefasDia();

}
