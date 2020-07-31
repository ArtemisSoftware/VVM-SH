package com.vvm.sh.ui.atividadesPendentes.modelos;

import androidx.room.Embedded;
import androidx.room.Relation;

public class AtividadePendenteRegisto {


    @Embedded
    public AtividadePendente atividade;


    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public AtividadePendenteResultado resultado;

}
