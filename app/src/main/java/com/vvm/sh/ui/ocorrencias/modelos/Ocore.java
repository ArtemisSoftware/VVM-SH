package com.vvm.sh.ui.ocorrencias.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

public class Ocore {

    @Embedded
    public Tipo tipo;


//    @Relation(
//            parentColumn = "id",
//            entityColumn = "id"
//    )
    @Ignore
    public OcorrenciaResultado resultado;

    @Ignore
    //@ColumnInfo(name = "ultimoRegisto")
    public int ultimoRegisto;
}
