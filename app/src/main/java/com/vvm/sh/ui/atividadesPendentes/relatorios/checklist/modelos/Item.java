package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos;

import androidx.room.ColumnInfo;

public class Item {


    public int idArea;

    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "subDescricao")
    public String subDescricao;


    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "completos")
    public int completos;

    @ColumnInfo(name = "total")
    public int total;

    @ColumnInfo(name = "tipo")
    public int tipo;
}
