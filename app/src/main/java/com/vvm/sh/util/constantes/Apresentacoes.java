package com.vvm.sh.util.constantes;

import com.vvm.sh.ui.apresentacao.modelos.Introducao;
import com.vvm.sh.ui.apresentacao.modelos.IntroducaoFactory;

import static com.vvm.sh.util.constantes.Identificadores.ApresentacaoApp.*;


/**
 * Classe que contem as apresentações que devem aparecer quando a app é atualizada
 */
public class Apresentacoes {



    private static final Introducao[] atualizacao_1_0_1 = new Introducao[]{

            IntroducaoFactory.obterIntroducao(TIPO_CORRECAO, "Correção da apresentação da completude do estado de um dia de trabalho"),
            IntroducaoFactory.obterIntroducao(TIPO_FUNCIONALIDADE, "Adicionado o relatório de certificado de tratamento às atividades pendentes"),
    };


    /**
     * Paginas referentes à ultima atualizacao da app
     */
    public static final Introducao[] PAGINAS_ATUALIZACAO = atualizacao_1_0_1;



    private static final Introducao BOAS_VINDAS = IntroducaoFactory.obterIntroducao(TIPO_APRESENTACAO, "Bem vindo a app vvm.sh.\n\nApp para o registo de auditorias de segurança alimentar");


    public static final Introducao[] PAGINAS_BOAS_VINDAS = new Introducao[]{ BOAS_VINDAS };


}
