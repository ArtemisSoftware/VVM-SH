package com.vvm.sh.ui.agenda;

public class Tarefa {

    private int id;
    private String descricao;
    private String empresa;

    public Tarefa(int id, String descricao, String empresa) {
        this.id = id;
        this.descricao = descricao;
        this.empresa = empresa;
    }

    public int obterId() {
        return id;
    }

    public String obterDescricao() {
        return descricao;
    }

    public String obterEmpresa() {
        return empresa;
    }
}
