package com.vvm.sh.util.interfaces;

public interface EstadoModelo {

    int MODELO = 1;
    int LOADING = 2;
    int EXHAUSTED = 3;

    int obterEstado();

}
