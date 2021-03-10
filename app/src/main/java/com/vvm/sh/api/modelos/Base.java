package com.vvm.sh.api.modelos;

import com.google.gson.annotations.SerializedName;
import com.vvm.sh.api.modelos.pedido.Codigo;

public class Base {

    @SerializedName("metodo")
    public String metodo;

    @SerializedName("api")
    public int api;

    @SerializedName("codigo")
    public int codigo;
}
