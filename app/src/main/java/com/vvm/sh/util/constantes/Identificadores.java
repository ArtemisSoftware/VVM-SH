package com.vvm.sh.util.constantes;

import android.graphics.Color;

import com.vvm.sh.R;

public class Identificadores {

    public static final String SEM_VALOR = "-1";
    public static final int SEM_VALOR_INT = -1;

    public class Origens{

        public static final int ORIGEM_BD = 1;
    }


    public class Estados{

        public static final int ESTADO_EXECUTADO = 1;
        public static final int ESTADO_NAO_EXECUTADO = 2;
        public static final int SEM_RELATORIO =   -1;
        public static final String ST = "ST";
    }

    public class Dias {

        /**
         * Intervalo de dias das atividades executadas
         */
        public static final int DIAS_ACTIVIDADES_EXECUTADA = 2;
    }

    public class App {
        public static final int APP_SA = 1;
        public static final String SA = "SA";
        public static final int COR_SA = R.color.cor_app_sa;
    }

    public class CodigoAtividade {
        public static final int ASSINATURA = 1;
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
    }

    public class OpcoesCliente {

        public static final int OPCAO_INFORMACAO = 1;
        public static final String INFORMACAO = "Informação";
        public static final int ICON_INFORMACAO = R.drawable.ic_informacao_24dp;

        public static final int OPCAO_CROSS_SELLING = 2;
        public static final String CROSS_SELLING = "Cross Selling";
        public static final int ICON_CROSS_SELLING = R.drawable.ic_shopping_basket_24dp;

        public static final int OPCAO_SINISTRALIDADE = 3;
        public static final int OPCAO_EXTINTORES = 4;


        public static final int OPCAO_EMAIL = 5;
        public static final String EMAIL = "Email";
        public static final int ICON_EMAIL = R.drawable.ic_email_24dp;

        public static final int OPCAO_ANOMALIA = 6;
    }


    public class Sincronizacao {
        public static final int SINCRONIZADO = 1;
        public static final int NAO_SINCRONIZADO = 2;
        public static final int TRANCADO = 3;
    }


    public class Relatorios {
        public static final int ID_RELATORIO_FORMACAO = 1;
        public static final String FORMACAO = "Formação";
    }

    public class CodigosWs {

        public static final int CODIGO_100 = 100;
        public static final String MSG_100 = "Sucesso no envio de dados";

        public static final int CODIGO_101 = 101;
        public static final String MSG_101 = "Não existe trabalho para download";

        public static final int CODIGO_400 = 400;
        public static final String MSG_400 = "Ocorreu um erro.";
    }
}
