package com.vvm.sh.api.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Classe que representa a sessao proveniente do web service
 */
public class Sessao {

    @SerializedName("Sessoes")
    public List<TrabalhoResultado> trabalho;
}
