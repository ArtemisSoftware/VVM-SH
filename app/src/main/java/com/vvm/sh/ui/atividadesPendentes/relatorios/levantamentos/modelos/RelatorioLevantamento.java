package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

public class RelatorioLevantamento {

    @ColumnInfo(name = "id")
    public int id;

    @Ignore
    public boolean valido;

}
