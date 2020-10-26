package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class AtividadePendenteExecutada extends AtividadePendente {

    @SerializedName("tempoExecucao")
    public String tempoExecucao;

    @SerializedName("dataExecucao")
    public String dataExecucao;

    @SerializedName("idAnomalia")
    public String idAnomalia = "";

    @SerializedName("obs")
    public String observacao = "";

    @SerializedName("formacao")
    public AcaoFormacao formacao;

    @SerializedName("AvaliacaoRiscosNova")
    public AvaliacaoRiscos avaliacaoRiscos;

    @SerializedName("RelatorioIluminacao")
    public RelatorioAmbiental iluminacao;


    @SerializedName("RelatorioTemperaturaHumidade")
    public RelatorioAmbiental temperaturaHumidade;

}
