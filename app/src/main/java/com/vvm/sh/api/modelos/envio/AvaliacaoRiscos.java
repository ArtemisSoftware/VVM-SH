package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvaliacaoRiscos {

    @SerializedName("Checklist")
    public List<Checklist> checklist;

    @SerializedName("Vulnerabilidades")
    public List<TrabalhadorVulneravel> trabalhadoresVulneraveis;

    @SerializedName("equipamentos")
    public List<String> equipamentos;

    @SerializedName("processoProdutivo")
    public String processoProdutivo;

    @SerializedName("levantamentosRisco")
    public List<Levantamento> levantamentosRisco;

    @SerializedName("RelatorioPlanoAcao")
    public List<Integer> propostasPlanoAcao;

    @SerializedName("capaRelatorio")
    public Imagem capaRelatorio = null;

}
