package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

/**
 * Classe que representa uma anomalia proveniente do web service
 */
public class IAnomalia {

    @SerializedName("Data")
    public String data;

    @SerializedName("Descricao")
    public String descricao;

    @SerializedName("Observacoes")
    public String observacao;

    @SerializedName("Contacto")
    public String contacto;

    @SerializedName("Tipo")
    public String tipo;

}
