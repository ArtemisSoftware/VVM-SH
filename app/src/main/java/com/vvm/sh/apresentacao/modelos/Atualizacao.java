package com.vvm.sh.apresentacao.modelos;

import com.vvm.sh.R;
import com.vvm.sh.util.Introducao;

public class Atualizacao extends Introducao {

    public Atualizacao(String texto){
        super(Introducao.TIPO_ATUALIZACAO, Introducao.ATUALIZACAO, texto);

        this.imagem = R.drawable.ic_atualizacao_app_150dp;
        this.corFundo = R.color.cor_fundo_atualizacao;
    }
}
