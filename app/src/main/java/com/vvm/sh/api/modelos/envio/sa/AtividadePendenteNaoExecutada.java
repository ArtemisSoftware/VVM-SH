package com.vvm.sh.api.modelos.envio.sa;

import com.google.gson.annotations.SerializedName;
import com.vvm.sh.api.modelos.envio.AtividadePendente;

public class AtividadePendenteNaoExecutada extends AtividadePendente {

    @SerializedName("tempoExecucao")
    public String tempoExecucao = "";

    @SerializedName("dataExecucao")
    public String dataExecucao = "";

    @SerializedName("idAnomalia")
    public String idAnomalia;

    @SerializedName("obs")
    public String observacao;

}
