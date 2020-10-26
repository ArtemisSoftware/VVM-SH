package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Levantamento {

    @SerializedName("perigo")
    public String perigo;

    @SerializedName("tarefa")
    public String tarefa;

    @SerializedName("categoriasProfissionais")
    public List<Integer> categoriasProfissionais;

    @SerializedName("riscos")
    public List<Risco> riscos;
}
