package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Seccao {

    @SerializedName("idSeccao")
    public String idSeccao;

    @SerializedName("dados")
    public List<ItemSeccaoChecklist> dados;
}
