package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Formando {

    @SerializedName("nome")
    public String nome;

    @SerializedName("biCartaoCidadao")
    public String biCartaoCidadao;

    @SerializedName("niss")
    public String niss;

    @SerializedName("dataNascimento")
    public String dataNascimento;

    @SerializedName("nacionalidade")
    public String nacionalidade;

    @SerializedName("naturalidade")
    public String naturalidade;

    @SerializedName("genero")
    public String genero;

    @SerializedName("Album")
    public ArrayList<String> album;
}
