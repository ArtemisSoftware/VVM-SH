package com.vvm.sh.util.constantes;

import com.vvm.sh.R;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.util.metodos.TiposUtil;

public class Identificadores {

    public static final String SEM_VALOR = "-1";
    public static final int SEM_VALOR_INT = -1;


    public static final int VALOR_INT_0 = 0;
    public static final String VALOR_0 = "0";
    public static final String VALOR_1 = "1";
    public static final String SEXO_OMISSAO = "1";
    public static final int ID_FORMACAO_TRABALHO_REALIZADO = 15;


    public static final int TIPO_DATA = 1;
    public static final int TIPO_NOTA = 2;
    public static final String PLANO_ACCAO_ATIVIDADE_TOPO = "topo";
    public static final int ID_POSICAO_TOPO = 1;

    public static final String PLANO_ACCAO_ATIVIDADE_MEIO = "null";
    public static final int ID_POSICAO_MEIO = 2;

    public static final String PLANO_ACCAO_ATIVIDADE_FIM = "fim";
    public static final int ID_POSICAO_FIM = 3;



    public static final int EXECUTADA = 2, PROGRAMADA_FIXA = 4, PROGRAMADA = 5, REPROGRAMADA = 6;



    public static final int ID_ANOMALIA_FALTA_TEMPO = -99;

    public static final int ID_EMAIL_REGISTO_VISITA_KMED = 3;
    public static final int ID_EMAIL_REGISTO_VISITA_VM = 2;
    public static final String UTILIZADOR_INVALIDO = "000";

    public class Origens{

        public static final int ORIGEM_BD = 1;
        public static final int ORIGEM_MORADA = 2;
        public static final int ORIGEM_MORADA_EXTINTOR = 3;
        public static final int ORIGEM_WS = 4;

        public static final int ORIGEM_RELATORIO_ILUMINACAO = 5;
        public static final int ORIGEM_RELATORIO_ILUMINACAO_MEDIDAS_RECOMENDADAS = 51;
        public static final int ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE = 6;
        public static final int ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE_MEDIDAS_RECOMENDADAS = 61;


        public static final int VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_HOMENS = 70;
        public static final int VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_MULHERES = 71;

        public static final int CHECKLIST = 8;


        public static final int LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS = 9;
        public static final int LEVANTAMENTO_MEDIDAS_RECOMENDADAS = 91;
        public static final int LEVANTAMENTO_MEDIDAS_ADOPTADAS = 92;
        public static final int LEVANTAMENTO_DUPLICACADO = 93;
        public static final int ORIGEM_LEVANTAMENTO_RISCO = 94;
        public static final int LEVANTAMENTO_MODELO = 95;



        public static final int AVERIGUACAO_AVALIACAO_RISCOS = 100;
/**/


        //public static final int AVALIACAO_AMBIENTAL_ILUMINACAO_CATEGORIAS_PROFISSIONAIS = 9;



        public static final int MEDIDAS_RISCO_EXISTENTES = 12;
        public static final int MEDIDAS_RISCO_RECOMENDADAS = 13;


        public static final int AVALIACAO_AMBIENTAL_ILUMINACAO_MEDIDAS_RECOMENDADAS = 14;

        public static final int PROPOSTA_CONDICOES_ST = 15;
        public static final int PROPOSTA_MEDIDAS_AVALIACAO = 16;

        public static final int AVALIACAO_AMBIENTAL_TEMPERATURA_HUMIDADE= 17;

    }


    public class Estados{

        public static final int ESTADO_EXECUTADO = 1;
        public static final int ESTADO_NAO_EXECUTADO = 2;
        public static final int SEM_RELATORIO = 0;
        public static final String ST = "ST";

        public class Equipamentos{

            public static final int ESTADO_PENDENTE = 1;
            public static final int ESTADO_DEFINITIVO = 2;
            public static final int ESTADO_REJEITADO = 3;


            public static final int REJEITADO_PELA_CHEFIA = 1;
            public static final int APROVADO_PELA_CHEFIA = 0;

            /**
             * Equipamento inexistente -> nem aprovado nem rejeitado
             */
            public static final int CODIGO_403 = 403;
        }

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
        public static final int APP_SH = 3;
        public static final String SH = "SH";
    }

    public class CodigoAtividade {
        public static final int ASSINATURA = 1;
        public static final int PESQUISA = 2;

        public static final int PESQUISA_CATEGORIAS_PROFISSIONAIS_HOMENS = 3;
        public static final int PESQUISA_CATEGORIAS_PROFISSIONAIS_MULHERES = 4;
        public static final int PESQUISA_MEDIDAS_RECOMENDADAS = 5;
        public static final int PESQUISA_MEDIDAS_ADOPTADAS = 6;
    }


    public class Imagens{

        public static final int IMAGEM_ASSINATURA_FORMANDO = 1;
        public static final int IMAGEM_ASSINATURA_REGISTO_VISITA = 2;
        public static final int IMAGEM_CHECKLIST = 3;
        public static final int IMAGEM_RISCO = 4;
        public static final int IMAGEM_CAPA_RELATORIO = 5;
        public static final int IMAGEM_ASSINATURA_INFORMACAO_SST = 6;
        public static final int IMAGEM_ASSINATURA_CERTIFICADO_TRATAMENTO = 7;
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
        public static final int ID_REGISTO_VISITA = 9;
        public static final int ID_PLANO_ACAO = 10;
        public static final int ID_INFORMCAO_SST = 11;
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


        public static final int OPCAO_PLANO_ACAO = 8;
        public static final String PLANO_ACAO = "Plano de ação";
        public static final int ICON_PLANO_ACAO= R.drawable.ic_plano_acao;


        public static final int OPCAO_SEM_TEMPO = 9;
        public static final String SEM_TEMPO = "Sem tempo";
        public static final int ICON_SEM_TEMPO= R.drawable.ic_sem_tempo_24;


        public static final int OPCAO_INFORMACAO_SST = 10;
        public static final String INFORMACAO_SST = "Informação SST";
        public static final int ICON_INFORMACAO_SST= R.drawable.ic_info_sst_24;


        public static final int OPCAO_ANOMALIA = 6;
    }


    public class Sincronizacao {
        public static final int SINCRONIZADO = 1;
        public static final int NAO_SINCRONIZADO = 2;
        public static final int TRANCADO = 3;
        public static final int SEM_SINCRONIZACAO = 4;
    }

    public class Checklist {
        public static final int TIPO_AREA = 1;
        public static final int TIPO_SECCAO = 2;


        public static final int ID_AREA_GERAL = 10;
        public static final String AREA_GERAL_SUB_DESCRICAO = "Principios gerais de prevenção";

        /**
         * questões
         */
        public static final String TIPO_QUESTAO = "q";

        /**
         * observacao
         */
        public static final String TIPO_OBSERVACOES = "ta";

        /**
         * uts
         */
        public static final  String TIPO_UTS = "map-i";

        /**
         * Fotos
         */
        public static final String TIPO_FOTOS = "foto";
    }



    public class Relatorios {

        public static final int SEM_RELATORIO = 0;

        public static final int ID_RELATORIO_FORMACAO = 1;
        public static final String FORMACAO = "Formação";

        public static final int ID_RELATORIO_ILUMINACAO = 2;
        public static final String ILUMINACAO = "Iluminação";

        public static final int ID_RELATORIO_TEMPERATURA_HUMIDADE = 3;
        public static final String TEMPERATURA_E_HUMIDADE = "Temperatura e humidade";

        public static final int ID_RELATORIO_AVALIACAO_RISCO = 4;
        public static final String AVALIACAO_RISCO = "Avaliação de riscos";

        public static final int ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO = 5;
        public static final String AVERIGUACAO_AVALIACAO_RISCO = "Avaliação de riscos";

        public static final int ID_RELATORIO_AVERIGUACAO_AUDITORIA = 6;
        public static final String AVERIGUACAO_AUDITORIA = "Auditoria";

        public static final int ID_RELATORIO_CERTIFICADO_TRATAMENTO = 7;
        public static final String CERTIFICADO_TRATAMENTO = "Certificado de tratamento";

    }

    public static class CodigosWs {

        public static final int ID_100 = 100;
        public static final String MSG_100 = "Sucesso no envio de dados";
        public static final Codigo Codigo_100 = new Codigo(ID_100, MSG_100);

        public static final int CODIGO_101 = 101;
        public static final String MSG_101 = "Não existe trabalho para download";
        public static final Codigo Codigo_101 = new Codigo(CODIGO_101, MSG_101);

        public static final int ID_110 = 110;
        public static final String MSG_110 = "Sucesso no envio do email";
        public static final Codigo Codigo_110 = new Codigo(ID_110, MSG_110);


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
        public static final Codigo Codigo_600 = new Codigo(ID_600, MSG_600);

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

    public class Codigos {
        public static final String ILUMINACAO = "iluminacao";
        public static final String TERMICO = "termico";
        public static final String EQUIPAMENTO = "eq";
    }

    public class Atualizacoes {
        public static final int TIPO = 1;
        public static final int TEMPLATE = 2;
        public static final int CHECKLIST = 3;
        public static final int ATIVIDADES_PLANEAVEIS = 4;
    }

    public class Download {
        public static final int DOWNLOAD_TRABALHO_DIA = 1;
        public static final int RECARREGAR_TRABALHO_DIA = 2;
        public static final int RECARREGAR_TAREFA = 3;
    }

    public static class FrasesApoio {
        public static final int ID_FRASE_APOIO_REGISTO_VISITA = 1;

        public static final int ID_FRASE_APOIO_CORPO_EMAIL_CERTIFICIADO_TRATAMENTO = 1;

        public static final int ID_FRASE_APOIO_CORPO_EMAIL_INFORMACAO_SST = 10;


        public static final int ID_FRASE_APOIO_INFORMACAO_SST_CLIENTE_1 = 4;
        public static final int ID_FRASE_APOIO_INFORMACAO_SST_CLIENTE_2 = 5;
        public static final int ID_FRASE_APOIO_INFORMACAO_SST_CLIENTE_3 = 6;

        public static final int ID_FRASE_APOIO_INFORMACAO_SST__CLIENTE [] = {ID_FRASE_APOIO_INFORMACAO_SST_CLIENTE_1, ID_FRASE_APOIO_INFORMACAO_SST_CLIENTE_2, ID_FRASE_APOIO_INFORMACAO_SST_CLIENTE_3};



        public static final int ID_FRASE_APOIO_INFORMACAO_SST_RODAPE_1 = 7;
        public static final int ID_FRASE_APOIO_INFORMACAO_SST_RODAPE_2 = 8;
        public static final int ID_FRASE_APOIO_INFORMACAO_SST_RODAPE_3 = 9;

        public static final int ID_FRASE_APOIO_INFORMACAO_SST__RODAPE [] = {ID_FRASE_APOIO_INFORMACAO_SST_RODAPE_1, ID_FRASE_APOIO_INFORMACAO_SST_RODAPE_2, ID_FRASE_APOIO_INFORMACAO_SST_RODAPE_3};



    }


}
