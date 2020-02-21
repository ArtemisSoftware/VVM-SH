package com.vvm.sh.ui.agenda;

import com.vvm.sh.util.adaptadores.Item;

public class Tarefa extends Item {

    private String empresa;

    public Tarefa(int id, String descricao, String empresa) {
        super(id, descricao);
        this.empresa = empresa;
    }

    public String obterEmpresa() {
        return empresa;
    }
}
