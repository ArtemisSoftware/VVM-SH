package com.vvm.sh.ui.apresentacao.modelos;

public class Apresentacao {

    public Introducao[] introducoes;
    public TipoAtualizacao tipoAtualizacao;

    public Apresentacao(Introducao[] introducoes, TipoAtualizacao tipoAtualizacao) {
        this.introducoes = introducoes;
        this.tipoAtualizacao = tipoAtualizacao;
    }
}

enum TipoAtualizacao {
    RECARRECAR_DADOS, ATUALIZAR_DADOS
}
