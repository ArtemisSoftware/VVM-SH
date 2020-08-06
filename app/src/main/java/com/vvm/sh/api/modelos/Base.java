package com.vvm.sh.api.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Base<T> {

    @SerializedName("TimeStamp")
    public String seloTemporal;

    @SerializedName(value="dadosNovos", alternate={"Novos", "UtilizadoresNovos"})
    public List<T> dadosNovos;


    @SerializedName(value="dadosAlterados", alternate={"Alterados", "UtilizadoresAlterados"})
    public List<T> dadosAlterados;

    @SerializedName("metodo")
    public String metodo;
}
