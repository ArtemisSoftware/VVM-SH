package com.vvm.sh.util.excepcoes;

import java.io.IOException;

public class RespostaWsInvalidaException extends IOException {

    public RespostaWsInvalidaException(String errorMessage) {
        super(errorMessage);
    }

}
