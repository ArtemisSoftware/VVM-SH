package com.vvm.sh.api.modelos;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Classe que representa o trabalho proveniente do web service
 */
public class TrabalhoResultado {

    @SerializedName("Data")
    public String data;

    @SerializedName("ARealizar")
    public List<TarefaResultado> tarefas;
}
