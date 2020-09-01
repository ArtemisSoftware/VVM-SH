package com.vvm.sh.api.modelos;

import com.google.gson.annotations.SerializedName;

public class Codigo {

    @SerializedName("codigo")
    public int codigo;

    @SerializedName("mensagem")
    public String mensagem;


    public Codigo() {
    }

    public Codigo(int codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }
}
