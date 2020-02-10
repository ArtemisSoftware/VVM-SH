package com.vvm.sh.apresentacao.modelos;

import com.vvm.sh.R;
import com.vvm.sh.util.Introducao;

public class Correcao extends Introducao {

    public Correcao(String texto){
        super(Introducao.TIPO_CORRECAO, Introducao.CORRECAO, texto);

        this.imagem = R.drawable.ic_correcao_app_150p;
        this.corFundo = R.color.cor_fundo_correcao;
    }
}