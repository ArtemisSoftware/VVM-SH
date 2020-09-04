package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class Ocorrencia {

    @SerializedName("idOcorrencia")
    public String idOcorrencia;

    @SerializedName("obs")
    public String observacao;

    @SerializedName("fiscalizado")
    public boolean fiscalizado;

    @SerializedName("dias")
    public String dias;

}
