package com.vvm.sh.util.constantes;

import com.vvm.sh.R;

public class Identificadores {

    public static final String SEM_VALOR = "-1";
    public static final int SEM_VALOR_INT = -1;

    public static final String VALOR_0 = "0";
    public static final String SEXO_OMISSAO = "1";

    public class Origens{

        public static final int ORIGEM_BD = 1;
        public static final int ORIGEM_MORADA = 2;
        public static final int ORIGEM_MORADA_EXTINTOR = 3;
        public static final int ORIGEM_WS = 4;

        public static final int ORIGEM_RELATORIO_ILUMINACAO = 5;
        public static final int ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE = 6;
        public static final int ORIGEM_LEVANTAMENTO_RISCO = 7;
        public static final int LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS = 8;

        public static final int AVALIACAO_AMBIENTAL_ILUMINACAO_CATEGORIAS_PROFISSIONAIS = 9;

    }


    public class Estados{

        public static final int ESTADO_EXECUTADO = 1;
        public static final int ESTADO_NAO_EXECUTADO = 2;
        public static final int SEM_RELATORIO = 0;
        public static final String ST = "ST";
    }

    public class Dias {

        /**
         * Intervalo de dias das atividades executadas
         */
        public static final int DIAS_ACTIVIDADES_EXECUTADA = 2;
        public static final int UM_ANO = 365;
    }

    public class App {

        public static final int APP = 0;

        public static final int APP_SA = 1;
        public static final String SA = "SA";
        public static final int COR_SA = R.color.cor_app_sa;

        public static final int APP_ST = 2;
        public static final String ST = "ST";
        public static final int COR_ST = R.color.cor_app_sht;
    }

    public class CodigoAtividade {
        public static final int ASSINATURA = 1;
        public static final int PESQUISA = 2;
    }


    public class Imagens{

        public static final int IMAGEM_ASSINATURA_FORMANDO = 1;
    }

    public class Resultados {
        public static final int ID_EMAIL = 1;
        public static final int ID_ANOMALIA_CLIENTE = 2;
        public static final int ID_ATIVIDADE_PENDENTE = 3;
        public static final int ID_CROSS_SELLING = 4;
        public static final int ID_OCORRENCIA = 5;
        public static final int ID_SINISTRALIDADE = 6;
        public static final int ID_PARQUE_EXTINTOR = 7;
        public static final int ID_QUADRO_PESSOAL = 8;
    }

    public class OpcoesCliente {

        public static final int OPCAO_INFORMACAO = 1;
        public static final String INFORMACAO = "Informação";
        public static final int ICON_INFORMACAO = R.drawable.ic_informacao_24dp;

        public static final int OPCAO_CROSS_SELLING = 2;
        public static final String CROSS_SELLING = "Cross Selling";
        public static final int ICON_CROSS_SELLING = R.drawable.ic_shopping_basket_24dp;

        public static final int OPCAO_SINISTRALIDADE = 3;
        public static final String SINISTRALIDADE = "Sinistralidade";
        public static final int ICON_SINISTRALIDADE = R.drawable.ic_sinistralidade;

        public static final int OPCAO_PARQUE_EXTINTORES = 4;
        public static final String PARQUE_EXTINTORES = "Parque Extintores";
        public static final int ICON_PARQUE_EXTINTORES = R.drawable.ic_extintor;


        public static final int OPCAO_EMAIL = 5;
        public static final String EMAIL = "Email";
        public static final int ICON_EMAIL = R.drawable.ic_email_24dp;


        public static final int OPCAO_QUADRO_PESSOAL = 6;
        public static final String QUADRO_PESSOAL = "Quadro Pessoal";
        public static final int ICON_QUADRO_PESSOAL= R.drawable.ic_quadro_pessoal_24dp;


        public static final int OPCAO_REGISTO_VISITA = 7;
        public static final String REGISTO_VISITA = "Registo de visita";
        public static final int ICON_REGISTO_VISITA= R.drawable.ic_registo_visita_24dp;


        public static final int OPCAO_ANOMALIA = 6;
    }


    public class Sincronizacao {
        public static final int SINCRONIZADO = 1;
        public static final int NAO_SINCRONIZADO = 2;
        public static final int TRANCADO = 3;
        public static final int SEM_SINCRONIZACAO = 4;
    }


    public class Relatorios {

        public static final int SEM_RELATORIO = 0;

        public static final int ID_RELATORIO_FORMACAO = 1;
        public static final String FORMACAO = "Formação";

        public static final int ID_RELATORIO_ILUMINACAO = 2;
        public static final String ILUMINACAO = "Iluminação";

        public static final int ID_RELATORIO_TEMPERATURA_HUMIDADE = 3;
        public static final String TEMPERATURA_E_HUMIDADE = "Temperatura e humidade";
    }

    public class CodigosWs {

        public static final int ID_100 = 100;
        public static final String MSG_100 = "Sucesso no envio de dados";

        public static final int CODIGO_101 = 101;
        public static final String MSG_101 = "Não existe trabalho para download";

        public static final int CODIGO_400 = 400;
        public static final String MSG_400 = "Ocorreu um erro.";

        public static final int CODIGO_401 = 401;
        public static final String MSG_401 = "Ocorreu um erro na ligação à base de dados";

        public static final int ID_402 = 402;
        public static final String MSG_402 = "A integridade dos dados enviados foi comprometida";


        public static final int ID_500 = 500;
        public static final String MSG_500 = "Erro desconhecido";

        public static final int ID_501 = 501;
        public static final String MSG_501 = "Connection reset by peer";

        public static final int ID_502 = 502;
        public static final String MSG_502 = "Connection to ip timed out";

        public static final int ID_503 = 503;
        public static final String MSG_503 = "Rede paga.";

        public static final int ID_504 = 504;
        public static final String MSG_504 = "Resposta inválida do servidor";

        public static final int ID_505 = 505;
        public static final String MSG_505 = "Servidor em manutenção.";


        public static final int ID_600 = 600;
        public static final String MSG_600 = "Erro no upload de dados";

        public static final int ID_601 = 601;
        public static final String MSG_601 = "Comunicação com o web service realizada sem header";

    }


    public class ApresentacaoApp {

        public static final int TIPO_APRESENTACAO = 0;
        public static final int TIPO_ATUALIZACAO = 1;
        public static final int TIPO_CORRECAO = 2;
        public static final int TIPO_FUNCIONALIDADE = 3;
    }

    public class Notificacoes {

        public static final int ID_ATUALIZACAO_APP = 1;
        public static final String CANAL_ATUALIZACAO_APP = "Atualização app";
    }

}
