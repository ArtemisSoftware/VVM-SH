package com.vvm.sh.util;

import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

public class Email extends javax.mail.Authenticator{

    private String destino, palavraChave, titulo;
    private InternetAddress emissor;
    private InternetAddress[] enderecosDestinoBCC;

    private String corpoEmail;


    private boolean autenticacaoSmtp, _debuggable, resultadoEnvio;
    private Multipart _multipart;


    public Email() {

        try {
            this.emissor = new InternetAddress(EMISSOR_TESTE);
            this.palavraChave =PALAVRA_CHAVE_TESTE;
            this.destino = EMISSOR_TESTE;
        }
        catch (AddressException e) {
            this.emissor = null;
        }

        this.titulo = "teste 1 2 3";

        this.corpoEmail = "Email de teste. Não aceitamos cabelo postiço";

        resultadoEnvio = false;
        _multipart = new MimeMultipart();

    }
//
//
//    public Email(Context contexto, String emissor, String palavraChave, String idUtilizador) {
//
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
//    }
//
//
//
//
//
//
//    /**
//     * MEtodo que permite fixar o corpo do email
//     * @param corpoEmail o corpo do email
//     */
//    public void fixarCorpoEmail(String corpoEmail) {
//        this.corpoEmail = corpoEmail;
//    }
//
//
//
//    /**
//     * Metodo que permite obter o endereco de destino
//     * @return o endereco de destino
//     */
//    public String obterEnderecoDestino() {
//        return destino;
//    }
//
//
//    /**
//     * Metodo que permite fixar o titulo do email
//     * @param titulo o titulo do email
//     */
//    public void fixarTitulo(String titulo) {
//        this.titulo = titulo;
//    }
//
//
//    /**
//     * Metodo que permite fixar o email de destino
//     * @param endereco o endereco de email de destino
//     */
//    public void fixarDestinario(String endereco) {
//
//        if(!TESTE){
//            this.destino = endereco;
//        }
//    }
//
//
//    //---------------------------------
//    //Metodos locais - envio
//    //---------------------------------
//
//    /**
//     * Metodo que permite obter o resultado do envio do email
//     * @return true caso o envio tenha sido realizado com sucesso ou false caso contrário
//     */
//    public boolean obterResultadoEnvio(){
//        return resultadoEnvio;
//    }
//
//
//
//    /**
//     * MEtodo que permite enviar um email
//     * @return true caso o email tenha sido enviado e false caso contrário
//     */
//    public boolean enviarEmail(){
//
//        inicializarMail();
//
//        Properties propriedades = fixarPropriedadesLigacao();
//        boolean resultado = false;
//
//        if(validarDadosEmail() == true) {
//
//            LogApp_v4.obterInstancia().adicionarTexto(propriedades.toString());
//
//            Session sessao = Session.getInstance(propriedades, this);
//            MimeMessage mensagem = new MimeMessage(sessao);
//
//            try {
//
//                //emissor
//
//                mensagem.setFrom(emissor);
//
//                //destino
//
//                mensagem.setRecipients(MimeMessage.RecipientType.TO, destino);
//
//                //BCC
//
//                if(enderecosDestinoBCC != null){
//                    mensagem.addRecipients(MimeMessage.RecipientType.BCC, enderecosDestinoBCC);
//                }
//
//
//                //titulo
//                mensagem.setSubject(titulo);
//                mensagem.setSentDate(new Date());
//
//                //corpo
//
//                BodyPart corpoMensagem = new MimeBodyPart();
//                corpoMensagem.setHeader(EmailIF.CABECALHO_EMAIL__CONTEUDO, EmailIF.CABECALHO_EMAIL__FORMATACAO_DADOS);
//                corpoMensagem.setText(corpoEmail);
//
//
//                MimeBodyPart htmlPart = new MimeBodyPart();
//                //htmlPart.setText(corpoEmail, "US-ASCII", "html");
//                htmlPart.setText(corpoEmail, EmailIF.CODIFICACAO_CORPO_EMAIL, EmailIF.TIPO_CORPO_EMAIL); //--------
//
//                _multipart.addBodyPart(htmlPart);
//
//                // Put parts in message
//                mensagem.setContent(_multipart);
//
//                LogApp_v4.obterInstancia().adicionarTexto("Email :" + mensagem.toString());
//
//
//                //enviar email
//                Transport.send(mensagem);
//
//                resultado =  true;
//            }
//            catch (AddressException e) {
//                e.printStackTrace();
//
//                LogApp_v4.obterInstancia().adicionarExcecaoErro(e);
//
//            }
//            catch (MessagingException e) {
//                LogApp_v4.obterInstancia().adicionarExcecaoErro(e);
//            }
//        }
//        else{
//            LogApp_v4.obterInstancia().adicionarTexto("Os campos do emails são inválidos");
//        }
//
//        LogApp_v4.obterInstancia().adicionarTexto("Resultado do envio do email: " + resultado);
//        resultadoEnvio = resultado;
//        return resultado;
//    }
//
//
//
//    //---------------------------------
//    //Metodos locais
//    //---------------------------------
//
//
//    /**
//     * Metodo que permite formatar os enderecos de email
//     * @param enderecos enderecos a formatar
//     * @return uma lista de enderecos
//     */
//
//    private InternetAddress[] formatarEnderecos(String[] enderecos){
//
//        InternetAddress[] addressTo = new InternetAddress[enderecos.length];
//
//        for (int index = 0; index < enderecos.length; index++) {
//            try {
//                addressTo[index] = new InternetAddress(enderecos[index]);
//            }
//            catch (AddressException e) {
//                LogApp_v4.obterInstancia().adicionarExcecaoErro("Endere-o de email inv-lido: " + enderecos[index], e);
//            }
//        }
//
//        return addressTo;
//    }
//
//
//    /**
//     * Metodo que permite adicionar um anexo
//     * @param filename
//     */
//    public void adicionarAnexo(String filename) {
//
//        BodyPart messageBodyPart = new MimeBodyPart();
//        DataSource source = new FileDataSource(filename);
//        try {
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(filename.split("/")[filename.split("/").length -1]);
//            _multipart.addBodyPart(messageBodyPart);
//        }
//        catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//
//    /**
//     * MEtodo que permite validar os dados do email
//     * @return true caso os dados sejam v-lido e false caso contrário
//     */
//    public boolean validarDadosEmail(){
//
//        if(!TESTE){
//            if(emissor == null
//                    &&
//                    palavraChave.equals(AppIF.SEM_TEXTO) == false
//                    &&
//                    destino == null
//                    &&
//                    titulo == null
//                    &&
//                    corpoEmail == null ) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//    //---------------------------------------
//    //Metodos locais - configuracoes
//    //---------------------------------------
//
//
//    /**
//     * MEtodo que permite inicializar o email
//     */
//    private void inicializarMail() {
//
//        resultadoEnvio = false;
//        _debuggable = false; // debug mode on or off - default off
//        autenticacaoSmtp = true; // smtp authentication - default on
//
//        // There is something wrong with MailCap, javamail can not find a handler for the multipart/mixed part, so this bit needs to be added.
//        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
//        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
//        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
//        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
//        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
//        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
//        CommandMap.setDefaultCommandMap(mc);
//    }
//
//
//    /**
//     * MEtodo que permite definir as propriedades da ligação
//     * @return as propriedades da ligação
//     */
//    private Properties fixarPropriedadesLigacao() {
//
//        Properties propriedades = new Properties();
//
//        propriedades.put("mail.smtp.host", EmailIF.SERVIDOR_SMTP);
//
//        if(_debuggable) {
//            propriedades.put("mail.debug", "true");
//        }
//
//        if(autenticacaoSmtp) {
//            propriedades.put("mail.smtp.auth", "true");
//        }
//
//        propriedades.put("mail.smtp.port", PORTO_EMAIL);
//        propriedades.put("mail.smtp.socketFactory.port", PORTO_SOCKET_EMAIL);
//        propriedades.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        propriedades.put("mail.smtp.socketFactory.fallback", "false");
//
//        return propriedades;
//    }
//
//
//
//    @Override
//    public PasswordAuthentication getPasswordAuthentication() {
//        return new PasswordAuthentication(emissor.getAddress(), palavraChave);
//    }
//
//
//
//
//    //--------------------------
//    //
//    //--------------------------
//
//
//    /**
//     * Metodo que permite obter uma representação textual do objeto
//     */
//    public String toString(){
//
//        String mensagem = "EMAIL {\t";
//
//        mensagem += "Emissor: " + emissor.getAddress()  + "\t";
//        mensagem += "PalavraChave: " + palavraChave + "\r\n\t";
//
//        mensagem += "Emissor: " + emissor + "\t";
//        mensagem += "Destinatarios: "+ destino  + "\r\n\t";
//
//        mensagem += "Titulo: "+ titulo + "\t";
//        mensagem += "Corpo email :"+ corpoEmail  + "\r\n";
//
//        mensagem += "} ";
//
//        return mensagem;
//    }
//
//






}
