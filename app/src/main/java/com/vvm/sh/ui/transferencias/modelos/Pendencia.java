package com.vvm.sh.ui.transferencias.modelos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Tarefa;

public class Pendencia {

    @Embedded
    public Cliente cliente;

    @Relation(parentColumn = "idTarefa", entityColumn = "idTarefa")
    public Tarefa tarefa;
}
