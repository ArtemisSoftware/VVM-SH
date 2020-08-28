package com.vvm.sh.util.excepcoes;

import java.io.IOException;

public class UtilizadorException extends IOException {

    public UtilizadorException(String errorMessage) {
        super(errorMessage);
    }

}
