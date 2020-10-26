package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvaliacaoTemperaturaHumidade {

    @SerializedName("idArea")
    public String idArea;

    @SerializedName("descricaoArea")
    public String descricaoArea;

    @SerializedName("idCategoriasProfissionais")
    public List<Integer> categoriasProfissionais;

    @SerializedName("idsMedidasRecomendadas")
    public List<Integer> medidasRecomendadas;


    @SerializedName("mulheres")
    public String mulheres;

    @SerializedName("homens")
    public String homens;

    @SerializedName("temperatura")
    public String temperatura;

    @SerializedName("humidadeRelativa")
    public String humidadeRelativa;

}
