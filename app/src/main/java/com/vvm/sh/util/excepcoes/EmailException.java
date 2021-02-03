package com.vvm.sh.util.excepcoes;

import java.io.IOException;

public class EmailException extends IOException {

    public EmailException(String errorMessage) {
        super(errorMessage);
    }

}
