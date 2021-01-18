package com.vvm.sh.util.constantes;

import com.vvm.sh.baseDados.entidades.Tipo;

public class TiposConstantes {


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


        public static final Tipo SIM = new Tipo(1, "S");
        public static final Tipo NAO = new Tipo(2, "N");
        public static final Tipo NAO_APLICAVEL = new Tipo(3,"NA");


        public static final Tipo RESPOSTAS[] = { SIM, NAO, NAO_APLICAVEL };


        public static final Tipo CATEGORIAS_RISCO_0 = new Tipo(1, "");
        public static final Tipo CATEGORIAS_RISCO_1 = new Tipo(2, "1");
        public static final Tipo CATEGORIAS_RISCO_2 = new Tipo(3,"2");
        public static final Tipo CATEGORIAS_RISCO_3 = new Tipo(4,"3");
        public static final Tipo CATEGORIAS_RISCO_4 = new Tipo(5,"4");

        public static final Tipo CATEGORIAS_RISCO[] = { CATEGORIAS_RISCO_0, CATEGORIAS_RISCO_1, CATEGORIAS_RISCO_2, CATEGORIAS_RISCO_3, CATEGORIAS_RISCO_4 };
    }


    public static class Averiguacao{

        public static final Tipo MEDIDA_IMPLEMENTADA = new Tipo(1, "Medida  implementada");
        public static final Tipo MEDIDA_NAO_IMPLEMENTADA= new Tipo(2, "Medida Não implementada");
        public static final Tipo MEDIDAS_IMPLEMENTACAO[] = { MEDIDA_IMPLEMENTADA, MEDIDA_NAO_IMPLEMENTADA };


        public static final Tipo RISCO_ELIMINADO = new Tipo(3, "Eliminado");
        public static final Tipo RISCO_REDUZIDO_ACEITAVEL = new Tipo(2, "Reduzido-Aceitável");
        public static final Tipo RISCO_REDUZIDO_NAO_ACEITAVEL = new Tipo(1, "Reduzido-Não Aceitável");
        public static final Tipo RISCOS_IMPLEMENTACAO[] = { RISCO_ELIMINADO, RISCO_REDUZIDO_ACEITAVEL, RISCO_REDUZIDO_NAO_ACEITAVEL };
    }


    public static class TiposNovos{

        public static final String TIPOS_MAQUINA = "Tipos_Maquina";

    }


    public static class CertificadoTratamento {

        public static class Pragas {

            public static final Tipo PRAGA_1 = new Tipo(1, "Baratas");

            public static final Tipo PRAGAS[] = { PRAGA_1 };

        }


        public static class Visitas {

            public static final Tipo VISITA_1 = new Tipo(1, "1ª Visita");
            public static final Tipo VISITA_2 = new Tipo(2, "2ª Visita");
            public static final Tipo VISITAS[] = { VISITA_1, VISITA_2 };

        }

        public static class Produtos {

            public static final Tipo PRODUTO_1 = new Tipo(1, "Goliath");
            public static final Tipo PRODUTO_2 = new Tipo(2, "Maxforce");

            public static final Tipo PRODUTOS[] = { PRODUTO_1, PRODUTO_2 };
        }

        public static class Avaliacoes {

            public static final Tipo AVALIACAO_1 = new Tipo(1, "Bom");
            public static final Tipo AVALIACAO_2 = new Tipo(2, "Médio");
            public static final Tipo AVALIACAO_3 = new Tipo(3, "Razoável");

            public static final Tipo AVALIACOES[] = { AVALIACAO_1, AVALIACAO_2, AVALIACAO_3 };
        }


    }
}
