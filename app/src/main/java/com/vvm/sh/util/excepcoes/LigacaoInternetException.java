package com.vvm.sh.util.excepcoes;

import com.vvm.sh.util.constantes.Sintaxe;

import java.io.IOException;

public class LigacaoInternetException extends IOException {

    @Override
    public String getMessage() {
        return Sintaxe.Alertas.SEM_INTERNET;
    }
}