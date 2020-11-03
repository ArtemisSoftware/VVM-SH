package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RelatorioAveriguacao {

    @SerializedName("seccao_categoria")
    public String seccao;

    @SerializedName("dataRelatorio")
    public String dataRelatorio;

//    @SerializedName("medidas")
//    public List<AvaliacaoAmbiental> avaliacoes = new ArrayList<>();
}
