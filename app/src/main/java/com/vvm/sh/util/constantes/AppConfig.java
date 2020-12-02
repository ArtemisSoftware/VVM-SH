package com.vvm.sh.util.constantes;

public class AppConfig {


    /**
     * Quando a true <br>
     *     o email é sempre enviado para o administrador
     */
    public static final boolean VERSAO_TESTE = true;

    public static final String MIME_VERSAO_APP = "application/vnd.android.package-archive";
    /*
    public static final String MIME_TIPO_PLAIN_TEXT = "plain/text";
    public static final String MIME_TIPO_TEXT_PLAIN = "text/plain";
    public static final String CODIFICACAO_UTF_8 = "UTF-8";

    public static final String MIME_TIPO_APLICACAO_PDF = "application/pdf";

     */


    public static final int DIAS_VALIDADE_AUTENTICACAO = 1;

    public static final long LIMITE_BYTES_FICHEIRO_JSON = 3000000;


    public static final long TEMPO_SPLASH = 4000;
    public static final long TEMPO_CONSULTA_UPLOAD = 1200;


    public static final int NUMERO_RESULTADOS_QUERY = 15;

    /**
     * Modos de funcionamento da app
     */
    public static final int APP_SA = 1;
    public static final int APP_SHT = 2;
    public static final int APP_COMBINADO = 3;
    public static final int APP_MODO = APP_SA;
}
