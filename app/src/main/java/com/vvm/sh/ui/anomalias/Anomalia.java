package com.vvm.sh.ui.anomalias;

import com.vvm.sh.util.adaptadores.Item;

public class Anomalia extends Item {

    private String data, descricao, observacoes, contacto, tipo;

    public Anomalia(int id, String descricao, String data, String observacoes,  String contacto, String tipo) {
        super(id, descricao);

        this.data = data;
        this.observacoes = observacoes;
        this.contacto = contacto;
        this.tipo = tipo;
    }

    
    /**
     * Metodo que permite obter o contacto
     * @return a contacto
     */
    public String obterContacto(){
        return contacto;
    }


    /**
     * Metodo que permite obter o tipo
     * @return o tipo
     */
    public String obterTipo(){
        return tipo;
    }

    /**
     * Metodo que permite obter a observacao
     * @return a observacao
     */
    public String obterObservacao(){
        return observacoes;
    }


    /**
     * Metodo que permite obter a data
     * @return a data
     */
    public String obterData(){
        return data;
    }
}
