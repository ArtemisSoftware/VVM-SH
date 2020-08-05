package com.vvm.sh.ui.anomalias.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.AnomaliaResultado;

public class AnomaliaRegistada {

    @Embedded
    public AnomaliaResultado resultado;

    @ColumnInfo(name = "descricao")
    public String descricao;
}
