package com.vvm.sh.servicos.email;

import android.content.Context;
import android.os.AsyncTask;

import com.vvm.sh.util.email.Email;
import com.vvm.sh.util.constantes.EmailConfig;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.MensagensUtil;

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

public class EnvioEmailAsyncTask extends AsyncTask<Email, Void, Void> {

    private Context contexto;
    public String erro = null;
    private MensagensUtil dialogo;


    public EnvioEmailAsyncTask(Context contexto) {
        this.contexto = contexto;
        dialogo = new MensagensUtil(contexto);
    }

    @Override
    protected void onPreExecute() {
        dialogo.progresso(true, Sintaxe.Frases.ENVIAR_EMAIL);
    }

    @Override
    protected Void doInBackground(Email... emails) {

        Email email = emails[0];

        try {

            inicializarMail();

            Properties propriedades = fixarPropriedadesLigacao(email);


            if(email.validarDadosEmail() == true) {

                Session sessao = Session.getInstance(propriedades, email);
                MimeMessage mensagem = new MimeMessage(sessao);

                try {

                    //emissor

                    mensagem.setFrom(email.emissor);

                    //destino

                    mensagem.setRecipients(MimeMessage.RecipientType.TO, email.destino);

                    //BCC

                    if(email.enderecosDestinoBCC != null){
                        mensagem.addRecipients(MimeMessage.RecipientType.BCC, email.enderecosDestinoBCC);
                    }


                    //titulo
                    mensagem.setSubject(email.titulo);
                    mensagem.setSentDate(new Date());

                    //corpo

                    BodyPart corpoMensagem = new MimeBodyPart();
                    corpoMensagem.setHeader(EmailConfig.Configuracao.CABECALHO_TIPO_CONTEUDO, EmailConfig.Configuracao.CABECALHO_FORMATO_DADOS);
                    corpoMensagem.setText(email.corpoEmail);


                    MimeBodyPart htmlPart = new MimeBodyPart();
                    htmlPart.setText(email.corpoEmail, EmailConfig.Configuracao.CODIFICACAO_CORPO_EMAIL, EmailConfig.Configuracao.TIPO_CORPO_EMAIL);

                    email._multipart.addBodyPart(htmlPart);

                    // Put parts in message
                    mensagem.setContent(email._multipart);


                    //enviar email
                    Transport.send(mensagem);

                    completarEnvio();
                }
                catch (MessagingException e) {
                    e.printStackTrace();
                    erro = e.getMessage();
                }
            }
            else{
                erro = Sintaxe.Alertas.EMAIL_CAMPOS_INVALIDOS;
            }

        }
        catch(Exception e) {
            erro = e.getMessage();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        dialogo.terminarProgresso();

        if(erro != null){
            dialogo.erro(Sintaxe.Alertas.PROBLEMA_EMAIL  + erro);
        }
        else{
            dialogo.sucesso(Sintaxe.Frases.DADOS_ENVIADOS_SUCESSO);
        }
    }

    //---------------------------------------
    //Metodos locais - configuracoes
    //---------------------------------------


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
    private Properties fixarPropriedadesLigacao(Email email) {

        Properties propriedades = new Properties();

        propriedades.put("mail.smtp.host", EmailConfig.Configuracao.SERVIDOR_SMTP);

        if(email._debuggable) {
            propriedades.put("mail.debug", "true");
        }

        if(email.autenticacaoSmtp) {
            propriedades.put("mail.smtp.auth", "true");
        }

        propriedades.put("mail.smtp.port", EmailConfig.Configuracao.PORTO_EMAIL);
        propriedades.put("mail.smtp.socketFactory.port", EmailConfig.Configuracao.PORTO_SOCKET_EMAIL);
        propriedades.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        propriedades.put("mail.smtp.socketFactory.fallback", "false");

        return propriedades;
    }


    /**
     * Metodo que permite completar o envio
     */
    protected void completarEnvio(){
        erro = null;
    }
}
