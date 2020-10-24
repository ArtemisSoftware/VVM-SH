package com.vvm.sh.servicos;

import android.content.Context;
import android.os.AsyncTask;

import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.servicos.email.EnvioEmailAsyncTask;
import com.vvm.sh.util.email.CredenciaisEmail;
import com.vvm.sh.util.email.Email;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.MensagensUtil;

public class EnvioRegistoVisitaAsyncTask extends AsyncTask<Template, Void, CredenciaisEmail> {

    private Context contexto;
    private MensagensUtil dialogo;
    public String erro = null;
    private Template template;
    private CredenciaisEmail credenciaisEmail;

    public EnvioRegistoVisitaAsyncTask(Context contexto, CredenciaisEmail credenciaisEmail) {
        this.contexto = contexto;
        this.credenciaisEmail = credenciaisEmail;
        dialogo = new MensagensUtil(contexto);
    }

    @Override
    protected void onPreExecute() {
        dialogo.progresso(true, Sintaxe.Frases.GERAR_PDF);
    }


    @Override
    protected CredenciaisEmail doInBackground(Template... templates) {

        this.template = templates[0];

        if(template == null){
            erro = Sintaxe.Alertas.NAO_EXISTEM_DOCUMENTOS;
        }
        else{
            template.createFile();
        }

        return credenciaisEmail;
    }

    @Override
    protected void onPostExecute(CredenciaisEmail credenciaisEmail) {
        super.onPostExecute(credenciaisEmail);

        dialogo.terminarProgresso();

        if(template == null){
            MensagensUtil dialogo = new MensagensUtil(contexto);
            dialogo.erro("erro", Sintaxe.Alertas.ERRO_GERAR_PDF + erro);
        }
        else{
            Email email = new Email(credenciaisEmail);
            //--email.adicionarAnexo(template.getPdfFile().getAbsolutePath(););
            EnvioEmailAsyncTask servico = new EnvioEmailAsyncTask(contexto);
            servico.execute(email);
        }
    }
}
