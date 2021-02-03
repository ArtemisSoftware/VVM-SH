package com.vvm.sh.util.email;

import com.vvm.sh.util.constantes.EmailConfig;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.excepcoes.EmailException;

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email extends javax.mail.Authenticator{

    public String destino, titulo, corpoEmail;

    public String palavraChave;


    public boolean autenticacaoSmtp, _debuggable;

    public InternetAddress emissor;
    public InternetAddress[] enderecosDestinoBCC;
    public Multipart _multipart;

    public MimeMessage mensagem;

    private boolean teste;


    public Email() {

        try {
            this.emissor = new InternetAddress(EmailConfig.Teste.ENDERECO_EMAIL);
            this.palavraChave = EmailConfig.Teste.PALAVRA_CHAVE;
            this.destino = EmailConfig.Teste.ENDERECO_EMAIL;
        }
        catch (AddressException e) {
            this.emissor = null;
        }

        this.titulo = EmailConfig.Teste.TITULO_EMAIL;
        this.corpoEmail = EmailConfig.Teste.CORPO_EMAIL;

        _multipart = new MimeMultipart();

        _debuggable = false; // debug mode on or off - default off
        autenticacaoSmtp = true; // smtp authentication - default on
        teste = true;
    }




    public Email(CredenciaisEmail credenciaisEmail) {

        try {
            this.emissor = new InternetAddress(credenciaisEmail.emissor);
            this.palavraChave = credenciaisEmail.palavraChave;
            this.destino = credenciaisEmail.destino;

            if(EmailConfig.Destinatarios.BCC_CONTAS_EMAIL_ADMINISTRADORES.length != 0){
                enderecosDestinoBCC = formatarEnderecos(EmailConfig.Destinatarios.BCC_CONTAS_EMAIL_ADMINISTRADORES);
            }
        }
        catch (AddressException e) {
            this.emissor = null;
        }
        _debuggable = false; // debug mode on or off - default off
        autenticacaoSmtp = true; // smtp authentication - default on
        this.titulo = credenciaisEmail.titulo;
        this.corpoEmail = credenciaisEmail.corpoEmail;
        _multipart = new MimeMultipart();

//        boolean teste = false;
//
//        try {
//
//            if(AppConfigIF.VERSAO_TESTE){ //teste
//                teste = true;
//                this.emissor = new InternetAddress(EMISSOR_TESTE);
//                this.palavraChave = PALAVRA_CHAVE_TESTE;
//                this.destino = EMISSOR_TESTE;
//            }
//            else if(idUtilizador.equals(UtilizadorIF.UTILIZADOR_TESTE_PROGRAMADOR)){ //teste para que utilizar o id utilizador de teste
//                teste = true;
//                this.emissor = new InternetAddress(EMISSOR_TESTE);
//                this.palavraChave = PALAVRA_CHAVE_TESTE;
//                this.destino = CONTAS_EMAIL_DESTINATARIOS_TESTE [DESTINATARIOS_TESTE];
//            }
//            else{
//                teste = false;
//                this.emissor = new InternetAddress(emissor);
//                this.palavraChave = palavraChave;
//
//                if(BCC_CONTAS_EMAIL_ADMINISTRADORES.length != 0){
//                    enderecosDestinoBCC = formatarEnderecos(BCC_CONTAS_EMAIL_ADMINISTRADORES);
//                }
//            }
//        }
//        catch (AddressException e) {
//            this.emissor = null;
//
//        }
//
//        TESTE = teste;
//        _multipart = new MimeMultipart();
    }






    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(emissor.getAddress(), palavraChave);
    }



    /**
     * MEtodo que permite validar os dados do email
     * @return true caso os dados sejam v-lido e false caso contrário
     */
    public boolean validarDadosEmail(){

        if(teste == false){
            if(emissor == null
                    &&
                    palavraChave.equals(Sintaxe.SEM_TEXTO) == false
                    &&
                    destino == null
                    &&
                    titulo == null
                    &&
                    corpoEmail == null ) {
                return false;
            }
        }
        return true;
    }


    //---------------------------------
    //Metodos locais
    //---------------------------------


    /**
     * Metodo que permite formatar os enderecos de email
     * @param enderecos enderecos a formatar
     * @return uma lista de enderecos
     */

    private InternetAddress[] formatarEnderecos(String[] enderecos){

        InternetAddress[] addressTo = new InternetAddress[enderecos.length];

        for (int index = 0; index < enderecos.length; index++) {
            try {
                addressTo[index] = new InternetAddress(enderecos[index]);
            }
            catch (AddressException e) {
                //LogApp_v4.obterInstancia().adicionarExcecaoErro("Endere-o de email inv-lido: " + enderecos[index], e);
            }
        }

        return addressTo;
    }


    /**
     * Metodo que permite adicionar um anexo
     * @param ficheiro o caminho para o ficheiro
     */
    public void adicionarAnexo(String ficheiro) {

        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(ficheiro);
        try {
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(ficheiro.split("/")[ficheiro.split("/").length -1]);
            _multipart.addBodyPart(messageBodyPart);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }



    //---------------------------------
    //Configurar
    //---------------------------------




    public void configurar() throws MessagingException, EmailException {

        inicializarMail();

        Properties propriedades = fixarPropriedadesLigacao();

        if(validarDadosEmail() == true) {

            Session sessao = Session.getInstance(propriedades, this);
            mensagem = new MimeMessage(sessao);

            //emissor

            mensagem.setFrom(emissor);

            //destino

            mensagem.setRecipients(MimeMessage.RecipientType.TO, destino);

            //BCC

            if(enderecosDestinoBCC != null){
                mensagem.addRecipients(MimeMessage.RecipientType.BCC, enderecosDestinoBCC);
            }


            //titulo
            mensagem.setSubject(titulo);
            mensagem.setSentDate(new Date());

            //corpo

            BodyPart corpoMensagem = new MimeBodyPart();
            corpoMensagem.setHeader(EmailConfig.Configuracao.CABECALHO_TIPO_CONTEUDO, EmailConfig.Configuracao.CABECALHO_FORMATO_DADOS);
            corpoMensagem.setText(corpoEmail);


            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setText(corpoEmail, EmailConfig.Configuracao.CODIFICACAO_CORPO_EMAIL, EmailConfig.Configuracao.TIPO_CORPO_EMAIL);

            _multipart.addBodyPart(htmlPart);

            // Put parts in message
            mensagem.setContent(_multipart);

        }
        else{
            throw new EmailException(Sintaxe.Alertas.EMAIL_CAMPOS_INVALIDOS);
        }

    }


    /**
     * MEtodo que permite inicializar o email
     */
    private void inicializarMail() {

        // There is something wrong with MailCap, javamail can not find a handler for the multipart/mixed part, so this bit needs to be added.
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);
    }


    /**
     * MEtodo que permite definir as propriedades da ligação
     * @return as propriedades da ligação
     */
    private Properties fixarPropriedadesLigacao() {

        Properties propriedades = new Properties();

        propriedades.put("mail.smtp.host", EmailConfig.Configuracao.SERVIDOR_SMTP);

        if(_debuggable) {
            propriedades.put("mail.debug", "true");
        }

        if(autenticacaoSmtp) {
            propriedades.put("mail.smtp.auth", "true");
        }

        propriedades.put("mail.smtp.port", EmailConfig.Configuracao.PORTO_EMAIL);
        propriedades.put("mail.smtp.socketFactory.port", EmailConfig.Configuracao.PORTO_SOCKET_EMAIL);
        propriedades.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        propriedades.put("mail.smtp.socketFactory.fallback", "false");

        return propriedades;
    }



}
