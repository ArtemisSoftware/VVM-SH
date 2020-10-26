package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class RelatorioAmbiental {


    @SerializedName("marca")
    public String marca;

    @SerializedName("numeroSerie")
    public String numeroSerie;

    @SerializedName("medidaRecomendada")
    public String medidaRecomendada;

    @SerializedName("dataAvaliacao")
    public String dataAvaliacao;

    @SerializedName("inicio")
    public String inicio;

    @SerializedName("termino")
    public String termino;

    @SerializedName("nebulosidade")
    public String nebulosidade;

//    @SerializedName("avaliacoes")
//    public String avaliacoes;


}
