package com.vvm.sh.util.constantes;

import com.titan.pdfdocumentlibrary.models.Index;

public class Pdf {

    public static class Seccoes {


        public int ID_CABECALHO = 0;
        public int ID_CLIENTE = 1;
        public static final int ID_TRABALHOS_REALIZADOS = 2;
        public int ID_HOMOLOGACAO = 3;
        public int ID_OBSERVACAO = 4;
        public int ID_RUBRICA = 5;


        public static final Index TRABALHOS_REALIZADOS = new Index(ID_TRABALHOS_REALIZADOS, "Trabalhos realizados");


//        put(SECCAO_CABECALHO, "Cabecalho");
//        put(SECCAO_CLIENTE, "Cliente");
//        put(SECCAO_HOMOLOGACAO, "Homologacao");
//        put(SECCAO_OBSERVACAO, "Observacao");
//        put(SECCAO_RUBRICA, "Rubrica");


    }

    public class Texto {
        public static final String TRABALHOS_REALIZADOS = "Trabalhos realizados";
    }

    public class Fontes {

        public static final float FONTE_TEXTO = 7f;
    }

    public class RegistoVisita {

        public static final float ALTURA____ENTRE_SECCOES = 3f/*151f*/;
        public static final float ALTURA_LINHA___TABELA_IDENTIFICACAO_CLIENTE = 25f/*30f*/;
        public static final int ALTURA_LINHA___TABELA_TRABALHOS_REALIZADOS = 18/*21f*/;
        public static final float ALTURA_LINHA___TABELA_HOMOLOGACAO = 20f/*35f*/;
        public static final float ALTURA_LINHA___TABELA_OBSERVACAO = /*90f*/20f;
    }
}
