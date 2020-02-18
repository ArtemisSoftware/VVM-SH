package com.vvm.sh.util.interfaces;

public interface CheckBoxIF {

    /**
     * MEtodo que permite fixar a selecao
     * @param selecao o valor da selecao
     */
    void fixarSelecao(boolean selecao);


    /**
     * Metodo que permite obter o valor da selecao
     * @return o valor da selecao
     */
    boolean obterSelecao();
}
