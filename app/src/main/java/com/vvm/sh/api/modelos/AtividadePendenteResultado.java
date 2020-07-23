package com.vvm.sh.api.modelos;


import com.google.gson.annotations.SerializedName;

/**
 * Classe que representa uma atividade pendente proveniente do web service
 */
public class AtividadePendenteResultado {


    @SerializedName("ServID")
    public String servId;

    @SerializedName("Descricao")
    public String descricao;

    @SerializedName("DataProg")
    public String dataProgramada;

    @SerializedName("formacao")
    public boolean formacao;
}
