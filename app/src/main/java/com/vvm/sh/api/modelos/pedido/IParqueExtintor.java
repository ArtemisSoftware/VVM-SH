package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

/**
 * Classe que representa um parque extintor do web service
 */
public class IParqueExtintor {

    @SerializedName("IdServico")
    public String idServico;

    @SerializedName("Contrato")
    public String contrato;

    @SerializedName("IdTipoExtintor")
    public String IdTipoExtintor;

    @SerializedName("Quantidade")
    public int quantidade;

    @SerializedName("DataValidade")
    public String dataValidade;

    @SerializedName("IdMorada")
    public int idMorada;
}
