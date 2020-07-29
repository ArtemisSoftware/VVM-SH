package com.vvm.sh.util;

public enum ResultadoId {

    EMAIL(1);

    private final int value;

    ResultadoId(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

}
