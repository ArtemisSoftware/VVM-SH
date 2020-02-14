package com.vvm.sh.ui.atividadesExecutadas;

import com.vvm.sh.util.adaptadores.Item;

public class AtividadeExecutada extends Item {

    private String data;

    public AtividadeExecutada(int id, String descricao, String data) {
        super(id, descricao);

        this.data = data;
    }


    /**
     * Metodo que permite obter a data
     * @return a data
     */
    public String obterData() {
        return data;
    }
}
