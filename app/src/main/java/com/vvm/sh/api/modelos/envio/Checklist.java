package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Checklist {

    @SerializedName("versaoChecklist")
    public String versao;

    @SerializedName("idChecklist")
    public String idChecklist;

    @SerializedName("dadosChecklist")
    public List<Area> areas;

}
