package com.vvm.sh.baseDados;


import androidx.room.Dao;
import androidx.room.Insert;

import com.vvm.sh.ui.agenda.modelos.Tarefa;

@Dao
public abstract class TarefaDao implements BaseDao<Tarefa>{

    @Insert
    abstract public Long inserirRegisto(Tarefa tarefa);
}
