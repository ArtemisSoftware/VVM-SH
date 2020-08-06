package com.vvm.sh.ui.crossSelling.modelos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.Tipo;

public class CrossSelling_ {

    @Embedded
    public Tipo tipo;

//    @Embedded
//    public CrossSellingResultado resultado;
//
    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public CrossSellingResultado resultado;




//
//    @ColumnInfo(name = "dimensao")
//    public String dimensao;

//    @ColumnInfo(name = "tipoSinaletica")
//    public String tipoSinaletica;
}
