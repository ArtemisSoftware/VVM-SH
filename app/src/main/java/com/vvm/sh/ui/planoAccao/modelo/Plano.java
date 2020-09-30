package com.vvm.sh.ui.planoAccao.modelo;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.PlanoAcao;

public class Plano {

    @Embedded
    public PlanoAcao detalhe;

    @Relation(
            parentColumn = "idTarefa",
            entityColumn = "idTarefa"
    )
    public Cliente cliente;
}
