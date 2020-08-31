package com.vvm.sh.apresentacao.modelos;

import com.vvm.sh.R;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

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


    public static Introducao obterIntroducao(int tipo, String texto){

        switch (tipo){

            case Identificadores.ApresentacaoApp.TIPO_APRESENTACAO:

                return new Introducao(Sintaxe.Palavras.APRESENTACAO, texto, R.drawable.ic_correcao_app);


            case Identificadores.ApresentacaoApp.TIPO_CORRECAO:

                return new Introducao(Sintaxe.Palavras.CORRECAO, texto, R.drawable.ic_correcao_app);


            case Identificadores.ApresentacaoApp.TIPO_FUNCIONALIDADE:

                return new Introducao(Sintaxe.Palavras.FUNCIONALIDADE, texto, R.drawable.ic_funcionalidade_app_150dp);


            case Identificadores.ApresentacaoApp.TIPO_ATUALIZACAO:

                return new Introducao(Sintaxe.Palavras.ATUALIZACAO, texto, R.drawable.ic_atualizacao_app_150dp);

            default:
                return null;
        }
    }



}
