package com.vvm.sh.api.modelos.pedido;


import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    @SerializedName("certificadoPragas")
    public int relatorioCertificadoTratamento;


    @SerializedName("Verificacao")
    public String verificacao;


    @SerializedName("formandos")
    public List<IFormando> formandos;

}
