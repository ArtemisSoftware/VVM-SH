package com.vvm.sh.util;

import com.vvm.sh.apresentacao.modelos.Atualizacao;
import com.vvm.sh.apresentacao.modelos.Correcao;
import com.vvm.sh.apresentacao.modelos.Funcionalidade;

public class IntroducaoFactory {

    public Introducao obterIntroducao(Introducao introducao){


        if(introducao == null){
            return null;
        }


        switch (introducao.obterTipo()){

            case Introducao.TIPO_ATUALIZACAO:

                return new Atualizacao(introducao.obterTexto());

            case Introducao.TIPO_CORRECAO:

                return new Correcao(introducao.obterTexto());

            case Introducao.TIPO_FUNCIONALIDADE:

                return new Funcionalidade(introducao.obterTexto());

            default:
                return null;
        }
    }


}
