package com.vvm.sh.util;

import com.vvm.sh.util.constantes.Identificadores;

public enum ResultadoId {

    EMAIL(Identificadores.Resultados.ID_EMAIL),
    ANOMALIA_CLIENTE(Identificadores.Resultados.ID_ANOMALIA_CLIENTE),
    ATIVIDADE_PENDENTE(3),
    CROSS_SELLING(4),
    OCORRENCIA(5);

    private final int value;

    ResultadoId(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

}
