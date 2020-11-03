package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class MedidaAveriguacao {


    @SerializedName("medidaImplementada")
    public boolean medidaImplementada;

    @SerializedName("risco")
    public String risco;


    @SerializedName("dataImplementacao")
    public String dataImplementacao;

    @SerializedName("ponderacao")
    public String ponderacao;


    @SerializedName("idMedida")
    public String idMedida;

    @SerializedName("novaMedida")
    public String novaMedida;
}
