package com.vvm.sh.ui.pesquisa.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;

import com.vvm.sh.baseDados.entidades.Tipo;

public class Medida {

    @Embedded
    public Tipo tipo;

    @ColumnInfo(name = "selecionado")
    public boolean selecionado;

}
