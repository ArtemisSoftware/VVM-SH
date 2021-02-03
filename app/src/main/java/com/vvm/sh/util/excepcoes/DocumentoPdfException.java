package com.vvm.sh.util.excepcoes;

import java.io.IOException;

public class DocumentoPdfException extends IOException {

    public DocumentoPdfException(String errorMessage) {
        super(errorMessage);
    }

}
