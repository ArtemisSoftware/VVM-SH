package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.SeccaoChecklist;

public class Seccao {

    @Embedded
    public SeccaoChecklist registo;

    @ColumnInfo(name = "completos")
    public int completos;

    @ColumnInfo(name = "total")
    public int total;
}
