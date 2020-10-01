package com.vvm.sh.util.constantes;

import com.vvm.sh.baseDados.entidades.Tipo;

public class TiposConstantes {


    public static class MetodosTipos{

        public static final String CROSS_SELLING_PRODUTOS = "GetCrossSellingProdutos";
        public static final String CROSS_SELLING_DIMENSAO = "GetCrossSellingTpDimensao";
        public static final String CROSS_SELLING_TIPO = "GetCrossSellingTpTipo";
        public static final String TIPOS_ANOMALIA = "GetTiposAnomalia";
        public static final String TIPIFICACAO_OCORRENCIA = "GetTipificacoesOcorrencia_New";

        public static final String TIPOS [] = new String []{

                CROSS_SELLING_PRODUTOS, CROSS_SELLING_DIMENSAO, CROSS_SELLING_TIPO,
                TIPOS_ANOMALIA,
                TIPIFICACAO_OCORRENCIA,

        };
    }

    /**
     * Classe com contantes do email
     */
    public static class Email{

        public static final Tipo EMAIL_CLIENTE_NAO_TEM_EMAIL = new Tipo(38, Sintaxe.Opcoes.CLIENTE_NAO_TEM_EMAIL);
        public static final Tipo EMAIL_AUTORIZADO = new Tipo(40, Sintaxe.Opcoes.AUTORIZADO);
        public static final Tipo EMAIL_NAO_AUTORIZADO = new Tipo(41, Sintaxe.Opcoes.NAO_AUTORIZADO);
    }

    //Genero

    public static final Tipo GENERO_MASCULINO = new Tipo(1, Sintaxe.Opcoes.MASCULINO, Sintaxe.Codigos.MASCULINO);
    public static final Tipo GENERO_FEMININO = new Tipo(2, Sintaxe.Opcoes.FEMININO, Sintaxe.Codigos.FEMININO);


    //Consultar + registos

    public static class OpcoesRegistos{

        public static final Tipo CONSULTAR = new Tipo(1, Sintaxe.Opcoes.CONSULTAR);
        public static final Tipo NOVOS_REGISTOS = new Tipo(2, Sintaxe.Opcoes.NOVOS_REGISTOS);
    }


    public static class Checklist{


        public static final Tipo SIM = new Tipo(1, "NA");
        public static final Tipo NAO = new Tipo(2, "N");
        public static final Tipo NAO_APLICAVEL = new Tipo(3,"S");


        public static final Tipo RESPOSTAS[] = { SIM, NAO, NAO_APLICAVEL };


        public static final Tipo CATEGORIAS_RISCO_0 = new Tipo(1, "");
        public static final Tipo CATEGORIAS_RISCO_1 = new Tipo(2, "1");
        public static final Tipo CATEGORIAS_RISCO_2 = new Tipo(3,"2");
        public static final Tipo CATEGORIAS_RISCO_3 = new Tipo(4,"3");
        public static final Tipo CATEGORIAS_RISCO_4 = new Tipo(5,"4");

        public static final Tipo CATEGORIAS_RISCO[] = { CATEGORIAS_RISCO_0, CATEGORIAS_RISCO_1, CATEGORIAS_RISCO_2, CATEGORIAS_RISCO_3, CATEGORIAS_RISCO_4 };
    }


    public static class TiposNovos{

        public static final String TIPOS_MAQUINA = "Tipos_Maquina";

    }

}
