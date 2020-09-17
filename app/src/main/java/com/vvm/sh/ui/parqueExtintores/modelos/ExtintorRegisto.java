package com.vvm.sh.ui.parqueExtintores.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.ParqueExtintorResultado;


public class ExtintorRegisto {


    @Embedded
    public ParqueExtintor parqueExtintor;


    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public ParqueExtintorResultado resultado;


    @ColumnInfo(name = "morada")
    public String morada;

    @ColumnInfo(name = "extintor")
    public String extintor;

}
