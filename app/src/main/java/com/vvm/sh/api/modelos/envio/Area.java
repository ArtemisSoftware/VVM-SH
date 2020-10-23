package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Area {

    @SerializedName("idArea")
    public String idArea;

    @SerializedName("area")
    public String area;

    @SerializedName("descricaoArea")
    public String subDescricao;

    @SerializedName("dadosArea")
    public List<Seccao> seccoes;

}
