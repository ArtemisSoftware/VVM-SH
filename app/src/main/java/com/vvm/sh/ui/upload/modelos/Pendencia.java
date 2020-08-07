package com.vvm.sh.ui.upload.modelos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Tarefa;

import java.util.List;

public class Pendencia {

    @Embedded
    public Cliente cliente;

    @Relation(parentColumn = "idTarefa", entityColumn = "idTarefa")
    public Tarefa tarefa;
}
