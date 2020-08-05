package com.vvm.sh.util.constantes;

import com.vvm.sh.ui.opcoes.modelos.Tipo;

public class TiposConstantes {


    public static final String CROSS_SELLING_PRODUTOS = "GetCrossSellingProdutos";
    public static final String CROSS_SELLING_DIMENSAO = "GetCrossSellingTpDimensao";
    public static final String CROSS_SELLING_TIPO = "GetCrossSellingTpTipo";
    public static final String TIPOS_ANOMALIA = "GetTiposAnomalia";
    public static final String TIPIFICACAO_OCORRENCIA = "GetTipificacoesOcorrencia_New";
    public static final String CURSOS = "getCoursesInfo";


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

    public static final Tipo CONSULTAR = new Tipo(1, Sintaxe.Opcoes.CONSULTAR);
    public static final Tipo NOVOS_REGISTOS = new Tipo(2, Sintaxe.Opcoes.NOVOS_REGISTOS);

}
