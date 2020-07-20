package com.vvm.sh.api.modelos;

import com.google.gson.annotations.SerializedName;

public class UtilizadorResultado {

    @SerializedName("Area")
    public String area;

    @SerializedName("NColaborador")
    public String id;

    @SerializedName("NOME")
    public String nome;


    @SerializedName("Password")
    public String palavraChave;

    @SerializedName("Activo")
    public boolean ativo;

    @SerializedName("Email")
    public String email;

}
