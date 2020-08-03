package com.vvm.sh.util;

import com.vvm.sh.util.constantes.Identificadores;

public enum ResultadoId {

    EMAIL(Identificadores.Resultados.ID_EMAIL),
    ANOMALIA_CLIENTE(Identificadores.Resultados.ID_ANOMALIA_CLIENTE),
    ATIVIDADE_PENDENTE(Identificadores.Resultados.ID_ATIVIDADE_PENDENTE),
    CROSS_SELLING(Identificadores.Resultados.ID_ANOMALIA_CLIENTE),
    OCORRENCIA(Identificadores.Resultados.ID_OCORRENCIA);

    private final int value;

    ResultadoId(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

}
