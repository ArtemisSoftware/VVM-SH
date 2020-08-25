package com.vvm.sh.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlocoDados {

    @SerializedName("Dados_Formularios")
    public List<DadosFormulario> dadosFormulario;


    @SerializedName("versaoApp")
    public String versaoApp;

    @SerializedName("ficheiroImagens")
    public int numeroFicheiroImagens;

}
