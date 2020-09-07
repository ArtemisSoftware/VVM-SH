package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

/**
 * Classe que representa um tipo proveniente do web service
 */
public class ITipo {

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
    public String detalhe;

}
