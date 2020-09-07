package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class Sessao {

    @SerializedName("ordem")
    public String ordem;

    @SerializedName("prefixoCT")
    public String prefixoCT;

    @SerializedName("timestamp")
    public String data;
}
