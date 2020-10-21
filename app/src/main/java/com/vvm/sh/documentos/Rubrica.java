package com.vvm.sh.documentos;

import androidx.room.ColumnInfo;

public class Rubrica {

    @ColumnInfo(name = "imagem")
    public byte[] imagem;

    @ColumnInfo(name = "cap")
    public String cap;

    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "data")
    public String data;
}
