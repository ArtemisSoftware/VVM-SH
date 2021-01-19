package com.vvm.sh.documentos.modelos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.InformacaoSstResultado;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.documentos.informacaoSst.InformacaoSst;

public class DadosCliente {


    @Embedded
    public Tarefa tarefa;


    @Relation(
            parentColumn = "idTarefa",
            entityColumn = "idTarefa"
    )
    public Cliente cliente;


    @Relation(
            parentColumn = "idTarefa",
            entityColumn = "idTarefa"
    )
    public RegistoVisitaResultado registo;

    @Relation(
            parentColumn = "idTarefa",
            entityColumn = "idTarefa"
    )
    public InformacaoSstResultado informacaoSst;

}
