package com.vvm.sh.util.constantes;

import com.itextpdf.text.BaseColor;
import com.titan.pdfdocumentlibrary.models.Index;
import com.vvm.sh.R;

public class Pdf {

    public static class Seccoes {


        public static final int ID_CABECALHO = 0;
        public static final int ID_CLIENTE = 1;
        public static final int ID_TRABALHOS_REALIZADOS = 2;
        public static final int ID_HOMOLOGACAO = 3;
        public static final int ID_OBSERVACAO = 4;
        public static final int ID_RUBRICA = 5;
        public static final int ID_OBRIGACOES_LEGAIS = 6;
        public static final int ID_CERTIFICADO_TRATAMENTO = 7;

        public static final Index CABECALHO = new Index(ID_CABECALHO, Texto.CABECALHO);
        public static final Index CLIENTE = new Index(ID_CLIENTE, Texto.CLIENTE);
        public static final Index TRABALHOS_REALIZADOS = new Index(ID_TRABALHOS_REALIZADOS, Texto.TRABALHOS_REALIZADOS);

        public static final Index HOMOLOGACAO = new Index(ID_HOMOLOGACAO, Texto.HOMOLOGACAO);
        public static final Index OBSERVACAO = new Index(ID_OBSERVACAO, Texto.OBSERVACAO);
        public static final Index RUBRICA = new Index(ID_RUBRICA, Texto.RUBRICA);


        public static final Index OBRIGACOES_LEGAIS = new Index(ID_OBRIGACOES_LEGAIS, Texto.OBRIGACOES_LEGAIS);
        public static final Index CERTIFICADO_TRATAMENTO = new Index(ID_CERTIFICADO_TRATAMENTO, Texto.CERTIFICADO_TRATAMENTO);
    }

    public class Texto {
        public static final String TRABALHOS_REALIZADOS = "Trabalhos realizados";
        public static final String DATA = "Data";
        public static final String CLIENTE = "Cliente";
        public static final String TECNICO = "Técnico";
        public static final String CAP_N = "CAP nº";
        public static final String RELATORIO_VISITA = "Relatório de Visita e";
        public static final String PEDIDO_INFORMACAO = "Pedido de Informação";
        public static final String TITULO_IDENTIFICACAO_CLIENTE = "Identificação do Cliente";
        public static final String N_CLIENTE = "Nº Cliente";
        public static final String N_ORDEM = "Nº Ordem";
        public static final String EMPRESA = "Empresa";
        public static final String RECEBIDO_POR = "Recebido por";
        public static final String FUNCAO = "Função";
        public static final String OBSERVACOES = "Observações";
        public static final String CABECALHO = "Cabeçalho";
        public static final String RUBRICA = "Rubrica";
        public static final String HOMOLOGACAO = "Homologação";

        public static final String HOMOLOGACAO_REGISTO_VISITA = "O cliente aprova o Plano de Acção das atividades de SST apresentado e entregue pelo Técnico de segurança";


        public static final String OBSERVACAO = "Observação";
        public static final String OBRIGACOES_LEGAIS = "Obrigacoes legais";
        public static final String CERTIFICADO_TRATAMENTO = "Certificado de tratamento";
    }

    public class Fontes {

        public static final float FONTE_TEXTO = 7f;
        public static final float FONTE_TEXTO_GRANDE = 9f;
        public static final float FONTE_ASSINATURA = 8f;
        public static final float FONTE_HOMOLOGACAO = 8f;
        public static final float FONTE_CABECALHO = 11f;
        public static final float FONTE_ONSERVACAO = 8f;

        public static final float FONTE_7 = 7f;
        public static final float FONTE_8 = 8f;
        public static final float FONTE_9 = 9f;

        /**
         * Titulos
         */
        public static final float FONTE_11 = 11f;
    }

    public class RegistoVisita {

        public static final float ALTURA____ENTRE_SECCOES = 3f/*151f*/;
        public static final int ALTURA_LINHA___TABELA_IDENTIFICACAO_CLIENTE = 25/*30f*/;
        public static final int ALTURA_LINHA___TABELA_TRABALHOS_REALIZADOS = 18/*21f*/;
        public static final int ALTURA_LINHA___TABELA_HOMOLOGACAO = 20/*35f*/;
        public static final int ALTURA_LINHA___TABELA_OBSERVACAO = /*90f*//*20*/10;


        public static final String RODAPE_TITULO_SM_v2 = "VivaMais - Segurança e Saúde do Trabalho, SA";
        public static final String RODAPE_TEXTO_1_SM = "Av. do Brasil, nº 7B 1700-062 Lisboa";
        public static final String RODAPE_TEXTO_2_SM = "Capital Social: 2.385.000€ - Contribuinte: 504518569";
        public static final String RODAPE_TEXTO_3_SM = "Tel.: 210317300 - Fax: 210317329 - E-mail: geral@vivamais.com";
    }

    public class Imagens {
        /**
         * 21 x 21 (pixels)
         */
        public static final int IMAGEM_CHECKBOX_CINZENTA = R.drawable.icon_chk_box_pdf;//img_chk_cinzento;

        /**
         * 21 x 21 (pixels)
         */
        public static final int IMAGEM_CHECKBOX_VAZIA_CINZENTA = R.drawable.icon_chk_box_vazia_pdf;//img_chk_vazio_cinzento;


        public static final int LOGOTIPO_VIVAMAIS = R.drawable.img_vivamais_logo;

        public static final int LOGOTIPO_VIVAMAIS_NEGATIVO = R.drawable.img_vivamais_logo_negativo;
    }

    public class TipoObservacao {

        /**
         * A observacao - apresentada como uma frase
         */
        public static final int TIPO_FRASE = 1;


        /**
         * A observacao - apresentada como um quadro
         */
        public static final int TIPO_QUADRO = 2;
    }

    public static class Cores {
        public static final BaseColor TINTA_CINZENTA = new BaseColor(242, 242, 242);
        public static final BaseColor TINTA_BORDA_CELULA = new BaseColor(179, 179, 179);
        public static final BaseColor VERMELHO_VIVAMAIS = new BaseColor(192, 0, 0);
    }
}
