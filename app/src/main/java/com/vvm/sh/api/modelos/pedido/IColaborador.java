package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

public class IColaborador {

    @SerializedName("ID")
    public String idColaborado;

    @SerializedName("IDMorada")
    public String idMorada;

    @SerializedName("Nome")
    public String nome;


    @SerializedName("Estado")
    public String estado;

    @SerializedName("Sexo")
    public String sexo;

    @SerializedName("DtNasc")
    public String dataNascimento;

    @SerializedName("Nacionalidade")
    public String nacionalidade;


}
