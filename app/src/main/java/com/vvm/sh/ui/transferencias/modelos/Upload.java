package com.vvm.sh.ui.transferencias.modelos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Tarefa;

import java.util.List;

public class Upload {

    @Embedded
    public Tarefa tarefa;

    @Relation(parentColumn = "idTarefa", entityColumn = "idTarefa")
    public List<Resultado> resultados;

    @Relation(parentColumn = "idTarefa", entityColumn = "idTarefa")
    public Cliente cliente;
}
