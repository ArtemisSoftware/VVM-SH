package com.vvm.sh.api.modelos.bd;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.ParqueExtintorResultado;

public class ExtintorBd {

    @Embedded
    public ParqueExtintorResultado resultado;

    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public ParqueExtintor extintor;
}
