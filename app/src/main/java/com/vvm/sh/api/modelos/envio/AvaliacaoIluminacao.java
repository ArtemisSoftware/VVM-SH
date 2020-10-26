package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvaliacaoIluminacao extends AvaliacaoAmbiental{


    @SerializedName("idTipoIluminacao")
    public String idTipoIluminacao;

    @SerializedName("emedioElx")
    public String emedioElx;

    @SerializedName("idElx")
    public String idElx;

    @SerializedName("nome")
    public String nome;

    @SerializedName("sexo")
    public String sexo;


}
