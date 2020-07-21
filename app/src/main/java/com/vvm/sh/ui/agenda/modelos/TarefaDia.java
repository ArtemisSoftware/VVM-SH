package com.vvm.sh.ui.agenda.modelos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.ui.cliente.Cliente;

public class TarefaDia {

    @Embedded
    public Tarefa tarefa;


    @Relation(
            parentColumn = "idTarefa",
            entityColumn = "idTarefa"
    )
    public Cliente cliente;


}
