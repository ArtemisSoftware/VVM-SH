package com.vvm.sh.util.constantes;

public class EmailConfig {



    /*......................email dos acordos................*/

    /**
     * Servidor smtp por omissão
     */
    public String SERVIDOR_SMTP = "smtp.gmail.com";

    /**
     * Porto smtp por omissão
     */
    public String PORTO_EMAIL = "465";//"587";


    /**
     * Porto socketfactory por omissão (default socketfactory port )
     */
    public String PORTO_SOCKET_EMAIL =  "465";//"587";


    /*......................email dos acordos................*/

    public String BCC_CONTAS_EMAIL_ADMINISTRADORES [] = {/*"patriciacandeias@vivamais.com"*/};

    public String CONTAS_EMAIL_DESTINATARIOS_TESTE [] = {"patriciacandeias@vivamais.com", "brunogomes@vivamais.com", };

    //public int DESTINATARIOS_TESTE = 0; //patriciacandeias
    public int DESTINATARIOS_TESTE = 1; //brunogomes



    /*......................variaveis para um email de acordo................*/

    public String ENDERECO_EMAIL =  "documentossst2@vivamais.com";
    public String PALAVRAS_CHAVE = "Documentos_SST2";


    /*......................variaveis para um email de teste................*/

    public String EMISSOR_TESTE = "gustavomaia@vivamais.com";
    public String PALAVRA_CHAVE_TESTE = "psyduckz";


    //public String EMISSOR_TESTE = "gustavo.isaac@b2b.com.pt";
    //public String PALAVRA_CHAVE_TESTE = "Trivalor.qzeroo1";


    //


    /*................help desk...................*/

    public String EMAIL_HELP_DESK = "helpdesk@vivamais.com";
    public String EMAIL_HELP_INFORMATICO = "gustavomaia@vivamais.com";

    public String EMAILS_HELP_DESK [] = {EMAIL_HELP_INFORMATICO/*, EMAIL_HELP_DESK*/};



    public String MIME_TIPO_PLAIN_TEXT = "plain/text";
    public String MIME_TIPO_TEXT_PLAIN = "text/plain";

    public String CABECALHO_EMAIL__CONTEUDO =  "Content-Type";
    public String CABECALHO_EMAIL__FORMATACAO_DADOS =  "text/plain; charset=UTF-8";
    public String CODIFICACAO_CORPO_EMAIL =  "UTF-8";
    public String TIPO_CORPO_EMAIL =  "html";
}
