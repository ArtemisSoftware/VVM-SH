package com.vvm.sh.ui.tarefa.modelos;

import com.vvm.sh.util.adaptadores.Item;

public class OpcaoCliente {

    public int id;
    public String descricao;
    public boolean ativo;

    public OpcaoCliente(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.ativo = false;
    }
}
