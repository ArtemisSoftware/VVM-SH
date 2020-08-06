package com.vvm.sh.api;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;

public class AtividadePendenteResultado_ {

    @Embedded
    public AtividadePendenteResultado resultado;


    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public AtividadePendente atividade;


}
