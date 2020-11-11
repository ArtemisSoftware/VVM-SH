package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class Imagem {

    @SerializedName("idFoto")
    public String idFoto;

    @SerializedName("foto")
    public String foto;

    public Imagem() {
    }

    public Imagem(int idImagem) {
        this.idFoto = idImagem + "";
    }
}
