package com.vvm.sh.api.modelos.envio.sht;

import com.google.gson.annotations.SerializedName;
import com.vvm.sh.api.modelos.envio.AcaoFormacao;
import com.vvm.sh.api.modelos.envio.AtividadePendente;
import com.vvm.sh.api.modelos.envio.AvaliacaoRiscos;
import com.vvm.sh.api.modelos.envio.RelatorioAmbiental;

public class AtividadePendenteExecutada extends AtividadePendente {

    @SerializedName("tempoExecucao")
    public String tempoExecucao;

    @SerializedName("dataExecucao")
    public String dataExecucao;


    @SerializedName("formacao")
    public AcaoFormacao formacao;

    @SerializedName("AvaliacaoRiscosNova")
    public AvaliacaoRiscos avaliacaoRiscos;

    @SerializedName("RelatorioIluminacao")
    public RelatorioAmbiental iluminacao;


    @SerializedName("RelatorioTemperaturaHumidade")
    public RelatorioAmbiental temperaturaHumidade;

}
