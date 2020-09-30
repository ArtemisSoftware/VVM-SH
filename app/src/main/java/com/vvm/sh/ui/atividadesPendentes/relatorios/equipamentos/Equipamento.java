package com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos;

import androidx.room.ColumnInfo;

public class Equipamento {

    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "estado")
    public int estado;
}
