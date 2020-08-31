package com.vvm.sh.apresentacao.modelos;

import com.vvm.sh.R;

public class Funcionalidade extends Introducao {

    public Funcionalidade(String texto){
        super(Introducao.TIPO_FUNCIONALIDADE, Introducao.FUNCIONALIDADE, texto);

        this.imagem = R.drawable.ic_funcionalidade_app_150dp;
        this.corFundo = R.color.cor_fundo_funcionalidade;
    }
}