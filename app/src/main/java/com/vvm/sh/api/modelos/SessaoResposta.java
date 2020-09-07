package com.vvm.sh.api.modelos;

import com.google.gson.annotations.SerializedName;
import com.vvm.sh.api.modelos.pedido.ITarefa;

import java.util.List;

public class SessaoResposta extends Base{

    @SerializedName("Sessoes")
    public List<Sessao> sessoes;


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
