package com.vvm.sh.ui.tarefa.modelos;

import com.vvm.sh.util.constantes.Identificadores;

import java.util.Objects;

import static com.vvm.sh.util.constantes.Identificadores.OpcoesCliente.*;

public class OpcaoCliente {


    public int id, icon, app;
    public String descricao, detalhe;
    public boolean ativo;

    public OpcaoCliente(int id, String descricao, int icon) {
        this.id = id;
        this.descricao = descricao;
        this.icon = icon;
        this.ativo = false;
        this.app = Identificadores.App.APP;
    }

    public OpcaoCliente(int id, String descricao, int icon, int app) {
        this.id = id;
        this.descricao = descricao;
        this.icon = icon;
        this.ativo = false;
        this.app = app;
    }


    public static OpcaoCliente email() {
        return new OpcaoCliente(OPCAO_EMAIL, EMAIL, ICON_EMAIL);
    }
    public static OpcaoCliente crossSelling() {
        return new OpcaoCliente(OPCAO_CROSS_SELLING, CROSS_SELLING, ICON_CROSS_SELLING);
    }
    public static OpcaoCliente informacao() {
        return new OpcaoCliente(OPCAO_INFORMACAO, INFORMACAO, ICON_INFORMACAO);
    }
    public static OpcaoCliente sinistralidade() {
        return new OpcaoCliente(OPCAO_SINISTRALIDADE, SINISTRALIDADE, ICON_SINISTRALIDADE, Identificadores.App.APP_ST);
    }
    public static OpcaoCliente parqueExtintores() {
        return new OpcaoCliente(OPCAO_PARQUE_EXTINTORES, PARQUE_EXTINTORES, ICON_PARQUE_EXTINTORES, Identificadores.App.APP_ST);
    }
    public static OpcaoCliente quadroPessoal() {
        return new OpcaoCliente(OPCAO_QUADRO_PESSOAL, QUADRO_PESSOAL, ICON_QUADRO_PESSOAL, Identificadores.App.APP_ST);
    }
    public static OpcaoCliente registoVisita() {
        return new OpcaoCliente(OPCAO_REGISTO_VISITA, REGISTO_VISITA, ICON_REGISTO_VISITA, Identificadores.App.APP_ST);
    }
    public static OpcaoCliente planoAcao() {
        return new OpcaoCliente(OPCAO_PLANO_ACAO, PLANO_ACAO, ICON_PLANO_ACAO, Identificadores.App.APP_ST);
    }

    public static OpcaoCliente semTempo() {
        return new OpcaoCliente(OPCAO_SEM_TEMPO, SEM_TEMPO, ICON_SEM_TEMPO, Identificadores.App.APP_ST);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpcaoCliente that = (OpcaoCliente) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
