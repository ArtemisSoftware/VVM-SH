package com.vvm.sh.api.modelos;

import com.google.gson.annotations.SerializedName;

public class TipoResultado {

    @SerializedName("Id")
    public String id;

    @SerializedName("Descricao")
    public String descricao;

    @SerializedName("Codigo")
    public String codigo;

    @SerializedName("ParentId")
    public String idPai;

    @SerializedName("Activo")
    public int ativo;

    @SerializedName("detailData")
    public boolean detalhe;

}
