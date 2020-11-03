package com.vvm.sh.api.modelos.bd;

import androidx.room.ColumnInfo;

public class RelatorioAveriguacaoBd {

    @ColumnInfo(name = "idRelatorio")
    public int idRelatorio;


    @ColumnInfo(name = "descricao")
    public String descricao;


    @ColumnInfo(name = "data")
    public String data;
}
