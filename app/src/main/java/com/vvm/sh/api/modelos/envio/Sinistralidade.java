package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class Sinistralidade {

    @SerializedName("acidentesComBaixa")
    public String acidentesComBaixa;

    @SerializedName("diasUteis")
    public String diasUteis;

    @SerializedName("totalTrabalhadores")
    public String totalTrabalhadores;

    @SerializedName("horasAnoTrabalhadores")
    public String horasAnoTrabalhadores;

    @SerializedName("faltas")
    public String faltas;

}
