package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

/**
 * Classe que representa uma morada do extintor do web service
 */
public class IMoradaExtintor {

    @SerializedName("ID")
    public int id;

    @SerializedName("Rua")
    public String rua;


    @SerializedName("CP4")
    public String cp4;


    @SerializedName("Localidade")
    public String localidade;
}
