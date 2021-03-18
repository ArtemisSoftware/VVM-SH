package com.vvm.sh.ui.apresentacao.modelos;

public class Apresentacao {

    public enum TipoAtualizacao {
        RECARRECAR_DADOS, ATUALIZAR_DADOS
    }

    public Introducao[] paginas;
    public TipoAtualizacao tipoAtualizacao;

    public Apresentacao(Introducao[] paginas, TipoAtualizacao tipoAtualizacao) {
        this.paginas = paginas;
        this.tipoAtualizacao = tipoAtualizacao;
    }
}


