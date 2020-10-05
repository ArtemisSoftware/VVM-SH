package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class Extintor {

    @SerializedName("IdServico")
    public String idServico;

    @SerializedName("dataValidade")
    public String dataValidade;

    @SerializedName("novaDataValidade")
    public String novaDataValidade;

    @SerializedName("contrato")
    public String contrato;

    @SerializedName("IdTipoExtintor")
    public String idTipo;

    @SerializedName("quantidade")
    public String quantidade;

    @SerializedName("IdMorada")
    public String idMorada;

}
