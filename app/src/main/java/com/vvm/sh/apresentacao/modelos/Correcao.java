package com.vvm.sh.apresentacao.modelos;

import com.vvm.sh.R;

public class Correcao extends Introducao {

    public Correcao(String texto){
        super(Introducao.TIPO_CORRECAO, Introducao.CORRECAO, texto);

        this.imagem = R.drawable.ic_correcao_app;
        this.corFundo = R.color.cor_fundo_correcao;
    }
}