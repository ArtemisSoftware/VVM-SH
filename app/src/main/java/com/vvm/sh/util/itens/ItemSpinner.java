package com.vvm.sh.util.itens;

import com.vvm.sh.util.adaptadores.Item;

public class ItemSpinner extends Item {

    private String codigo;

    public ItemSpinner(int id, String descricao) {
        super(id, descricao);
    }

    public ItemSpinner(int id, String descricao, String codigo){
        super(id, descricao);
        this.codigo = codigo;
    }

    /**
     * Metodo que permite obter o codigo
     * @return o codigo
     */
    public String obterCodigo(){
        return codigo;
    }
}
