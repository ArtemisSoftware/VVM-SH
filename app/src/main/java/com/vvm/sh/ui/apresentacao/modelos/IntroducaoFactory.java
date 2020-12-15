package com.vvm.sh.ui.apresentacao.modelos;

import com.vvm.sh.R;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

public class IntroducaoFactory {


    /**
     * Metodo que permite obter uma introducao
     * @param tipo o tipo de introducao
     * @param texto o texto a aparecer
     * @param icon o icon a associar
     * @return uma introducao
     */
    public static Introducao obterIntroducao(int tipo, String texto, int icon){

        switch (tipo){

            case Identificadores.ApresentacaoApp.TIPO_APRESENTACAO:

                return new Introducao(Sintaxe.Palavras.APRESENTACAO, texto, icon);


            case Identificadores.ApresentacaoApp.TIPO_CORRECAO:

                return new Introducao(Sintaxe.Palavras.CORRECAO, texto, icon);


            case Identificadores.ApresentacaoApp.TIPO_FUNCIONALIDADE:

                return new Introducao(Sintaxe.Palavras.FUNCIONALIDADE, texto, icon);


            case Identificadores.ApresentacaoApp.TIPO_ATUALIZACAO:

                return new Introducao(Sintaxe.Palavras.ATUALIZACAO, texto, icon);

            default:
                return null;
        }
    }


    /**
     * Metodo que permite obter uma introducao
     * @param tipo o tipo de introducao
     * @param texto o texto a aparecer
     * @return uma introducao
     */
    public static Introducao obterIntroducao(int tipo, String texto){

        switch (tipo){

            case Identificadores.ApresentacaoApp.TIPO_APRESENTACAO:

                return new Introducao(Sintaxe.Palavras.APRESENTACAO, texto, R.drawable.ic_app_logo_1);


            case Identificadores.ApresentacaoApp.TIPO_CORRECAO:

                return new Introducao(Sintaxe.Palavras.CORRECAO, texto, R.drawable.ic_correcao_app);


            case Identificadores.ApresentacaoApp.TIPO_FUNCIONALIDADE:

                return new Introducao(Sintaxe.Palavras.FUNCIONALIDADE, texto, R.drawable.ic_funcionalidade_app);


            case Identificadores.ApresentacaoApp.TIPO_ATUALIZACAO:

                return new Introducao(Sintaxe.Palavras.ATUALIZACAO, texto, R.drawable.ic_atualizacao_app);

            default:
                return null;
        }
    }



}
