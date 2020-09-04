package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class Email {

    @SerializedName("email")
    public String email;

    @SerializedName("estadoEmail")
    public String estadoEmail;
}
