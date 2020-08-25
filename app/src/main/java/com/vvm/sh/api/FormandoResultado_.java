package com.vvm.sh.api;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.FormandoResultado;

public class FormandoResultado_ {

    @Embedded
    public FormandoResultado resultado;


    @ColumnInfo(name = "idImagem")
    public int idImagem;
}
