package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParqueExtintor {

    @SerializedName("extintores")
    public List<Extintor> extintores;

    @SerializedName("extintoresInalterados")
    public String inalterado;
}
