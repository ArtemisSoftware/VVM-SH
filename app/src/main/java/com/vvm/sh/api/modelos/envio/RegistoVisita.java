package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RegistoVisita {

    @SerializedName("recebidoPor")
    public String recebidoPor;

    @SerializedName("funcao")
    public String funcao;

    @SerializedName("obs")
    public String observacao;

    @SerializedName("trabalhosRealizados")
    public List<TrabalhoRealizado> trabalhosRealizados;

    @SerializedName("Album")
    public ArrayList<Imagem> album;

    @SerializedName("data")
    public String data;

    @SerializedName("homologado")
    public boolean homologado;

}
