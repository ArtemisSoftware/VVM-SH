package com.vvm.sh.ui.anomalias;

import com.vvm.sh.util.adaptadores.Item;

public class Anomalia extends Item {


    public final static int TIPO_ANOMALIA = 1;
    public final static int TIPO_NOVA_ANOMALIA = 2;

    private String data, observacoes, contacto, tipo;
    private int tipoAnomalia;

    public Anomalia(int id, String descricao, String observacoes) {
        super(id, descricao);

        this.observacoes = observacoes;
        this.tipoAnomalia = TIPO_NOVA_ANOMALIA;
    }


    public Anomalia(int id, String descricao, String data, String observacoes, String contacto, String tipo) {
        super(id, descricao);

        this.data = data;
        this.observacoes = observacoes;
        this.contacto = contacto;
        this.tipo = tipo;

        this.tipoAnomalia = TIPO_ANOMALIA;
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



    /**
     * Metodo que permite obter o tipo de anomalia
     * @return o tipo de anomalia
     */
    public int obterTipoAnomalia(){
        return tipoAnomalia;
    }
}
