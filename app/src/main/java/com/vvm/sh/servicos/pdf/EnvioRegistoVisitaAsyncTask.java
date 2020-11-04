package com.vvm.sh.servicos.pdf;

import android.content.Context;
import android.os.AsyncTask;

import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.RegistoVisitaDao;
import com.vvm.sh.repositorios.RegistoVisitaRepositorio;
import com.vvm.sh.servicos.email.EnvioEmailAsyncTask;
import com.vvm.sh.servicos.email.EnvioEmailRegistoVisitaAsyncTask;
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
    private EnvioEmailRegistoVisitaAsyncTask servico;

    public EnvioRegistoVisitaAsyncTask(Context contexto, CredenciaisEmail credenciaisEmail, VvmshBaseDados vvmshBaseDados, RegistoVisitaRepositorio repositorio, int idTarefa) {
        this.contexto = contexto;
        this.credenciaisEmail = credenciaisEmail;
        dialogo = new MensagensUtil(contexto);

        servico = new EnvioEmailRegistoVisitaAsyncTask(contexto, vvmshBaseDados, repositorio, idTarefa);
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
            email.adicionarAnexo(template.getPdfFile().getAbsolutePath());
            servico.execute(email);
        }
    }
}
