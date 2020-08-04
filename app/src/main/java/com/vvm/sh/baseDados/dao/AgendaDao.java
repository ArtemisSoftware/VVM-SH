package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.ui.agenda.modelos.TarefaDia;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public abstract class AgendaDao {


    @Transaction
    @Query("SELECT * FROM tarefas ")
    abstract public Flowable<List<TarefaDia>> obterTarefasDia();

}
