package com.vvm.sh.ui.agenda;

public class Tarefa {

    private int id;
    private String descricao;

    public Tarefa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int obterId() {
        return id;
    }

    public String obterDescricao() {
        return descricao;
    }
}
