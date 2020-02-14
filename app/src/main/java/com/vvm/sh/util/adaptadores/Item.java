package com.vvm.sh.util.adaptadores;

/**
 * Classe que representa a base dos objectos de uma RecyclerView
 */
public abstract class Item {

    private int id;
    private String descricao;

    public Item(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }


    /**
     * Metodo que permite obter o identificador
     * @return o identificador
     */
    public int obterId() {
        return id;
    }


    /**
     * Metodo que permite obter a descricao
     * @return uma descricao
     */
    public String obterDescricao() {
        return descricao;
    }

}
