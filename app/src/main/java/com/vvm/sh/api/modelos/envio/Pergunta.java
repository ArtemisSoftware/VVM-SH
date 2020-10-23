package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class Pergunta {

    @SerializedName("idItem")
    public String idItem;

    @SerializedName("resposta")
    public String resposta;

    @SerializedName("nr")
    public String nr;
}
