package com.vvm.sh.api.modelos.bd;

import androidx.room.ColumnInfo;

public class AtividadePlanoAcaoBd {

    @ColumnInfo(name = "servId")
    public String servId;

    @ColumnInfo(name = "data")
    public String data;

    @ColumnInfo(name = "servicoTp")
    public String servicoTp;

    @ColumnInfo(name = "ordem")
    public String ordem;
}