package com.vvm.sh.util.constantes;

import com.vvm.sh.ui.opcoes.modelos.Tipo;

public class TiposConstantes {


    public static final String CROSS_SELLING_PRODUTOS = "GetCrossSellingProdutos";
    public static final String CROSS_SELLING_DIMENSAO = "GetCrossSellingTpDimensao";
    public static final String CROSS_SELLING_TIPO = "GetCrossSellingTpTipo";
    public static final String TIPOS_ANOMALIA = "GetTiposAnomalia";
    public static final String TIPIFICACAO_OCORRENCIA = "GetTipificacoesOcorrencia_New";


    //EmailResultado


    public static final Tipo EMAIL_CLIENTE_NAO_TEM_EMAIL = new Tipo(38, Sintaxe.Opcoes.CLIENTE_NAO_TEM_EMAIL);
    public static final Tipo EMAIL_AUTORIZADO = new Tipo(40, Sintaxe.Opcoes.AUTORIZADO);
    public static final Tipo EMAIL_NAO_AUTORIZADO = new Tipo(41, Sintaxe.Opcoes.NAO_AUTORIZADO);


}
