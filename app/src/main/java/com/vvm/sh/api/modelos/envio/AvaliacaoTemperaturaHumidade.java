package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvaliacaoTemperaturaHumidade extends AvaliacaoAmbiental{


    @SerializedName("mulheres")
    public String mulheres;

    @SerializedName("homens")
    public String homens;

    @SerializedName("temperatura")
    public String temperatura;

    @SerializedName("humidadeRelativa")
    public String humidadeRelativa;

}
