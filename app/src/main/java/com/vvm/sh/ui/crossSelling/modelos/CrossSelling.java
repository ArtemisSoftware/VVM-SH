package com.vvm.sh.ui.crossSelling.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.Tipo;

public class CrossSelling {

    @Embedded
    public Tipo tipo;


    @ColumnInfo(name = "idTarefa")
    public int idTarefa;

    @ColumnInfo(name = "idAreaRecomendacao")
    public int idAreaRecomendacao;

    @ColumnInfo(name = "idDimensaoSinaletica")
    public int idDimensaoSinaletica;

    @ColumnInfo(name = "idTipoSinaletica")
    public int idTipoSinaletica;

    @ColumnInfo(name = "dimensaoSinaletica")
    public String dimensaoSinaletica;

    @ColumnInfo(name = "tipoSinaletica")
    public String tipoSinaletica;


    @ColumnInfo(name = "possuiSinaletica")
    public boolean possuiSinaletica;

    @ColumnInfo(name = "selecionado")
    public boolean selecionado;


}
