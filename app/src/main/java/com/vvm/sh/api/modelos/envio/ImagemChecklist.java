package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagemChecklist extends ItemSeccaoChecklist{

    @SerializedName("fotos")
    public List<Integer> ids;
}
