package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class Pergunta extends ItemSeccaoChecklist{



    @SerializedName("resposta")
    public String resposta;

    @SerializedName("nr")
    public String nr;
}
