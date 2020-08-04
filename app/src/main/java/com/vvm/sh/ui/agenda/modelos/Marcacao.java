package com.vvm.sh.ui.agenda.modelos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.api.modelos.ClienteResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.ui.cliente.Cliente;

public class Marcacao {

    @Embedded
    public Tarefa tarefa;


    @Relation(
            parentColumn = "idTarefa",
            entityColumn = "idTarefa"
    )
    public Resultado resultado;


    @Relation(
            parentColumn = "idTarefa",
            entityColumn = "idTarefa"
    )
    public Cliente cliente;
}
