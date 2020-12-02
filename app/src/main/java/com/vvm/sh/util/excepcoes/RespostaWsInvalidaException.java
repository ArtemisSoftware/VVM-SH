package com.vvm.sh.util.excepcoes;

import com.google.gson.Gson;
import com.vvm.sh.api.modelos.pedido.Codigo;

import java.io.IOException;

public class RespostaWsInvalidaException extends IOException {

    public RespostaWsInvalidaException(String errorMessage) {
        super(errorMessage);
    }


    public RespostaWsInvalidaException(Codigo codigo) {
        super(formatarCodigo(codigo));
    }

    private static String formatarCodigo(Codigo codigo) {

        Gson gson = new Gson();
        return gson.toJson(codigo, Codigo.class);
    }

}
