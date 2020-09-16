package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

public class ITipoTemplateAvrLevantamento {

    @SerializedName("Id")
    public int id;

    @SerializedName("Tarefa")
    public String tarefa;


    @SerializedName("Perigo")
    public String perigo;


    @SerializedName("ParentId")
    public String idPai;

    @SerializedName("Activo")
    public int ativo;

    @SerializedName("detailData")
    public String detalhe;

}
