package com.vvm.sh.servicos.relatorio;

import android.content.Context;

import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.servicos.pdf.DocumentoPdfAsyncTask;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.MensagensUtil;

public class GerarPdfAsyncTask extends DocumentoPdfAsyncTask {

    private EnvioDocumento listener;

    public GerarPdfAsyncTask(Context contexto, Template template, MensagensUtil dialogo, EnvioDocumento listener) {
        super(contexto, template, dialogo);

        this.listener = listener;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);


        dialogo.progresso(true, "lolo");

        listener.enviarEmail(template.getPdfFile().getAbsolutePath());

    }
}
