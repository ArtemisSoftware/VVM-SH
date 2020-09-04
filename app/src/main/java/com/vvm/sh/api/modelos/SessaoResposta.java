package com.vvm.sh.api.modelos;

import com.google.gson.annotations.SerializedName;
import com.vvm.sh.api.modelos.pedido.ITarefa;

import java.util.List;

public class SessaoResposta {

    @SerializedName("Sessoes")
    public List<Sessao> sessoes;


    @SerializedName("metodo")
    public String metodo;


    @SerializedName("app")
    public String app;

    public class Sessao {

        @SerializedName("Data")
        public String data;

        @SerializedName("ARealizar")
        public List<TrabalhoInfo> trabalho;

    }

    public class TrabalhoInfo{

        @SerializedName("Trabalho")
        public ITarefa tarefas;
    }
}
