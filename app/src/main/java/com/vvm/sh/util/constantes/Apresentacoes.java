package com.vvm.sh.util.constantes;

import com.vvm.sh.ui.apresentacao.modelos.Introducao;
import com.vvm.sh.ui.apresentacao.modelos.IntroducaoFactory;

import static com.vvm.sh.util.constantes.Identificadores.ApresentacaoApp.*;

public class Apresentacoes {

    public static final Introducao[] PAGINAS_ATUALIZACAO = new Introducao[]{

            IntroducaoFactory.obterIntroducao(TIPO_CORRECAO, "Correção da  funcionalidade abc \nCorreção da  funcionalidade com muito esforco e suor da minha cara, nem tomei banho"),
            IntroducaoFactory.obterIntroducao(TIPO_FUNCIONALIDADE, "Adicionada a funcionalidade 1"),
            IntroducaoFactory.obterIntroducao(TIPO_ATUALIZACAO, "Atualizado o funcionamento da funcionalidade 0"),
    };





    private static final Introducao BOAS_VINDAS = IntroducaoFactory.obterIntroducao(TIPO_APRESENTACAO, "Bem vindo a app vvm.sh.\n\nApp para o registo de auditorias de segurança alimentar");


    public static final Introducao[] PAGINAS_BOAS_VINDAS = new Introducao[]{ BOAS_VINDAS };


}
