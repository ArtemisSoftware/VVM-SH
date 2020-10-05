package com.vvm.sh.util.excepcoes;

import java.io.IOException;

public class MetodoWsInvalidoException extends IOException {

    public MetodoWsInvalidoException(String errorMessage) {
        super(errorMessage);
    }
}
