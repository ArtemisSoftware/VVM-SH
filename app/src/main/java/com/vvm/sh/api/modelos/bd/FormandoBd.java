package com.vvm.sh.api.modelos.bd;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.FormandoResultado;

public class FormandoBd {

    @Embedded
    public FormandoResultado resultado;


    @ColumnInfo(name = "idImagem")
    public int idImagem;
}
