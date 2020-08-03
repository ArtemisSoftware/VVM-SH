package com.vvm.sh.api;

import com.google.gson.annotations.SerializedName;

public class CrossSelling {

    @SerializedName("idAreaRecomendacao")
    public String idAreaRecomendacao;

    @SerializedName("idProduto")
    public String id;

    @SerializedName("idDimensao")
    public String idDimensao;

    @SerializedName("idTipo")
    public String idTipo;
}
