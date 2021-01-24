package com.vvm.sh.servicos.pdf;

import android.content.Context;
import android.os.AsyncTask;

import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.MensagensUtil;


/**
 * Servico que permite gerar um pdf e abri-lo para vizualizacao
 */
public class DocumentoPdfAsyncTask extends AsyncTask<Void, Void, Void> {

    private Context contexto;
    protected Template template;
    protected MensagensUtil dialogo;

    public DocumentoPdfAsyncTask(Context contexto, Template template){
        this.contexto = contexto;
        this.template = template;
        dialogo = new MensagensUtil(contexto);
    }

    protected DocumentoPdfAsyncTask(Context contexto, Template template, MensagensUtil dialogo){
        this.contexto = contexto;
        this.template = template;
        this.dialogo = dialogo;
    }


    @Override
    protected void onPreExecute() {
        dialogo.progresso(true, Sintaxe.Frases.GERAR_PRE_VISUALIZACAO_PDF);
    }


    @Override
    protected Void doInBackground(Void... voids) {

        this.template.createFile();

        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        dialogo.terminarProgresso();

        try {
            this.template.openPdf();
        }
        catch (IllegalArgumentException e){
            MensagensUtil dialogo = new MensagensUtil(contexto);
            dialogo.erro("erro", Sintaxe.Alertas.ERRO_GERAR_PDF + e.getMessage());
        }
    }
}