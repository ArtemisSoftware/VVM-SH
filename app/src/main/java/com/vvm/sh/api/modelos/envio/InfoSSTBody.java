package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class InfoSSTBody {


    @SerializedName("ordem")
    private int ordem;

    @SerializedName("marca")
    private int marca;

    @SerializedName("f")
    private byte[] stream;


}
