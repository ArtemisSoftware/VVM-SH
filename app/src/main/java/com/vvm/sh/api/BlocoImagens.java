package com.vvm.sh.api;

import com.google.gson.annotations.SerializedName;
import com.vvm.sh.api.modelos.envio.Imagem;

import java.util.List;

public class BlocoImagens {

    @SerializedName("Dados_Fotos")
    public List<Imagem> dadosImagens;
}
