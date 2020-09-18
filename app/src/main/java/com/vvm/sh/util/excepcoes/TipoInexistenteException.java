package com.vvm.sh.util.excepcoes;

import com.vvm.sh.util.constantes.Sintaxe;

import java.io.IOException;

public class TipoInexistenteException extends IOException {

    public TipoInexistenteException() {
        super(Sintaxe.Alertas.TIPO_NAO_REGISTADOS);
    }


    public TipoInexistenteException(String errorMessage) {
        super(Sintaxe.Alertas.TIPO_NAO_REGISTADO + errorMessage);
    }
}
