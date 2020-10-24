package com.vvm.sh.util.email;

import com.vvm.sh.util.constantes.EmailConfig;
import com.vvm.sh.util.constantes.Sintaxe;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class Email extends javax.mail.Authenticator{

    public String destino, titulo, corpoEmail;

    public String palavraChave;


    public boolean autenticacaoSmtp, _debuggable, resultadoEnvio;

    public InternetAddress emissor;
    public InternetAddress[] enderecosDestinoBCC;
    public Multipart _multipart;



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

        resultadoEnvio = false;
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

}
