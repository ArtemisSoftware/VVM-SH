package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class Equipamento {

    @SerializedName("id")
    public String id;

    @SerializedName("descricao")
    public String descricao;

    @SerializedName("tipo")
    public String tipo;

    @SerializedName("idUtilizador")
    public String idUtilizador;

}
