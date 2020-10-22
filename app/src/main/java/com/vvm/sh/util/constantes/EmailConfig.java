package com.vvm.sh.util.constantes;

public class EmailConfig {


    /**
     * Variaveis para um email de teste
     */
    public static class Teste{

        public final static String EMISSOR = "gustavomaia@vivamais.com";
        public final static String PALAVRA_CHAVE= "psyduckz";
    }


    public static class Configuracao{

        public final static String CABECALHO_TIPO_CONTEUDO =  "Content-Type";
        public final static String CABECALHO_FORMATO_DADOS =  "text/plain; charset=UTF-8";
        public final static String CODIFICACAO_CORPO_EMAIL =  "UTF-8";
        public final static String TIPO_CORPO_EMAIL =  "html";


        /**
         * Servidor smtp por omissão
         */
        public final static String SERVIDOR_SMTP = "smtp.gmail.com";

        /**
         * Porto smtp por omissão
         */
        public final static String PORTO_EMAIL = "465";//"587";


        /**
         * Porto socketfactory por omissão (default socketfactory port )
         */
        public final static String PORTO_SOCKET_EMAIL =  "465";//"587";

    }


    public static class Destinatarios{

        public final static String BCC_CONTAS_EMAIL_ADMINISTRADORES [] = {/*"patriciacandeias@vivamais.com"*/};
    }



    /*......................email dos acordos................*/




    /*......................email dos acordos................*/



    public String CONTAS_EMAIL_DESTINATARIOS_TESTE [] = {"patriciacandeias@vivamais.com", "brunogomes@vivamais.com", };

    //public int DESTINATARIOS_TESTE = 0; //patriciacandeias
    public int DESTINATARIOS_TESTE = 1; //brunogomes



    /*......................variaveis para um email de acordo................*/

    public String ENDERECO_EMAIL =  "documentossst2@vivamais.com";
    public String PALAVRAS_CHAVE = "Documentos_SST2";





    //public String EMISSOR_TESTE = "gustavo.isaac@b2b.com.pt";
    //public String PALAVRA_CHAVE_TESTE = "Trivalor.qzeroo1";


    //


    /*................help desk...................*/

    public String EMAIL_HELP_DESK = "helpdesk@vivamais.com";
    public String EMAIL_HELP_INFORMATICO = "gustavomaia@vivamais.com";

    public String EMAILS_HELP_DESK [] = {EMAIL_HELP_INFORMATICO/*, EMAIL_HELP_DESK*/};



    public String MIME_TIPO_PLAIN_TEXT = "plain/text";
    public String MIME_TIPO_TEXT_PLAIN = "text/plain";



}
