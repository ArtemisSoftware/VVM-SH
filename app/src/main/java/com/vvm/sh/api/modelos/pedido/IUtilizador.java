package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

/**
 * Classe que representa um utilizador proveniente do web service
 */
public class IUtilizador {

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


    @SerializedName("api")
    public int api;

}
