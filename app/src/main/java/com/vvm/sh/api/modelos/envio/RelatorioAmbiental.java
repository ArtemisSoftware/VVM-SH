package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RelatorioAmbiental {


    @SerializedName("marca")
    public String marca;

    @SerializedName("numeroSerie")
    public String numeroSerie;

    @SerializedName("medidaRecomendada")
    public int medidaRecomendada;

    @SerializedName("dataAvaliacao")
    public String dataAvaliacao;

    @SerializedName("inicio")
    public String inicio;

    @SerializedName("termino")
    public String termino;

    @SerializedName("nebulosidade")
    public String nebulosidade;

    @SerializedName("equipamento")
    public String equipamento;

    @SerializedName("avaliacoes")
    public List<AvaliacaoAmbiental> avaliacoes = new ArrayList<>();


}
