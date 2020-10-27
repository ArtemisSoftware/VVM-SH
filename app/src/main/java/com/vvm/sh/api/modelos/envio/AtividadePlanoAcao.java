package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class AtividadePlanoAcao {

    @SerializedName("idActividade")
    public String servId;

    @SerializedName("codigo")
    public String servicoTP; ///o codigo é o servico TP dos dados do cliente

    @SerializedName("ordem")
    public String ordem;/// a ordem vem das atividades planeaveis

    @SerializedName("dataActividade")
    public String data;
}
