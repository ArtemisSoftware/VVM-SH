package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

/**
 * Classe que representa uma atividade executada proveniente do web service
 */
public class IAtividadeExecutada {


    @SerializedName("Ordem")
    public String ordem;

    @SerializedName("ServID")
    public String idServico;

    @SerializedName("Descricao")
    public String descricao;

    @SerializedName("DataProg")
    public String dataProgramada;

    @SerializedName("DataExecucao")
    public String dataExecucao;
}
