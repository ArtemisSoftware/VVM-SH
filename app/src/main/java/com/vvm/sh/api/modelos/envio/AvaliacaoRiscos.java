package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvaliacaoRiscos {

    @SerializedName("Checklist")
    public List<Checklist> checklist;

    @SerializedName("Vulnerabilidades")
    public List<TrabalhadorVulneravel> trabalhadoresVulneraveis;
}
