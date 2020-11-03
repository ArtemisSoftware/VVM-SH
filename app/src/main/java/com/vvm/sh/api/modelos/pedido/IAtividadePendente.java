package com.vvm.sh.api.modelos.pedido;


import com.google.gson.annotations.SerializedName;

/**
 * Classe que representa uma atividade pendente proveniente do web service
 */
public class IAtividadePendente {


    @SerializedName("ServID")
    public String servId;

    @SerializedName("Descricao")
    public String descricao;

    @SerializedName("DataProg")
    public String dataProgramada;

    @SerializedName("Anuidade")
    public String anuidade;


    @SerializedName("ChecklistAVR")
    public String idChecklist;


    @SerializedName("formacao")
    public int formacao;

    @SerializedName("relatorioIluminacao")
    public int relatorioIluminacao;

    @SerializedName("relatorioTermico")
    public int relatorioTermico;

    @SerializedName("AvRisco")
    public int relatorioAvaliacaoRisco;


    @SerializedName("Verificacao")
    public String verificacao;
}
