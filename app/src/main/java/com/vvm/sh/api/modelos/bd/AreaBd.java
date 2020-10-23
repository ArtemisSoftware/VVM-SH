package com.vvm.sh.api.modelos.bd;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;

public class AreaBd {

    @Embedded
    public AreaChecklistResultado resultado;


    @ColumnInfo(name = "descricao")
    public String descricao;
}
