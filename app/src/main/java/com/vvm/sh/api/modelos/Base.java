package com.vvm.sh.api.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Base<T> {

    @SerializedName("TimeStamp")
    public String timeStamp;

    @SerializedName(value="dadosNovos", alternate={"Novos", "userNovos"})
    public List<T> dadosNovos;


    @SerializedName(value="dadosAlterados", alternate={"Alterados", "userAlterados"})
    public List<T> dadosAlterados;
}