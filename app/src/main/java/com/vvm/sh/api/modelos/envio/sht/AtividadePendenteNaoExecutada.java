package com.vvm.sh.api.modelos.envio.sht;

import com.google.gson.annotations.SerializedName;
import com.vvm.sh.api.modelos.envio.AtividadePendente;

public class AtividadePendenteNaoExecutada extends AtividadePendente {

    @SerializedName("idAnomalia")
    public String idAnomalia;

    @SerializedName("obs")
    public String observacao;

}
