package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class AvaliacaoAmbiental {

    @SerializedName("idArea")
    public String idArea;

    @SerializedName("descricaoArea")
    public String descricaoArea;

    @SerializedName("idCategoriasProfissionais")
    public List<Integer> categoriasProfissionais;

    @SerializedName("idsMedidasRecomendadas")
    public List<Integer> medidasRecomendadas;


}
