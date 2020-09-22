package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;

public class Area {

    @Embedded
    public AreaChecklistResultado resultado;


    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "completos")
    public int completos;

    @ColumnInfo(name = "total")
    public int total;
}
