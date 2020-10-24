package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrabalhadorVulneravel {

    @SerializedName("idColaborador")
    public String id;

    @SerializedName("quantidadeHomens")
    public String quantidadeHomens;

    @SerializedName("categoriasProfissionaisHomens")
    public List<Integer> categoriasProfissionaisHomens;


    @SerializedName("quantidadeMulheres")
    public String quantidadeMulheres;

    @SerializedName("categoriasProfissionaisMulheres")
    public List<Integer> categoriasProfissionaisMulheres;

}
