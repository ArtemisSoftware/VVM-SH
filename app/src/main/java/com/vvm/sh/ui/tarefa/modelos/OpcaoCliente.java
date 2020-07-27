package com.vvm.sh.ui.tarefa.modelos;

import com.vvm.sh.util.adaptadores.Item;

public class OpcaoCliente {


    public static final int OPCAO_INFORMACAO = 1;
    public static final int OPCAO_CROSS_SELLING = 2;
    public static final int OPCAO_SINISTRALIDADE = 3;
    public static final int OPCAO_EXTINTORES = 4;
    public static final int OPCAO_EMAIL = 5;
    public static final int OPCAO_ANOMALIA = 6;



    public int id;
    public String descricao, detalhe;
    public boolean ativo;

    public OpcaoCliente(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.ativo = false;
    }
}
