package com.vvm.sh.servicos.relatorio;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.documentos.DocumentoPdf;
import com.vvm.sh.repositorios.RegistoVisitaRepositorio;
import com.vvm.sh.servicos.email.EnvioEmailAsyncTask;
import com.vvm.sh.util.constantes.Sintaxe;

public class EnvioEmailPdfAsyncTask extends EnvioEmailAsyncTask{

    private VvmshBaseDados vvmshBaseDados;
    private DocumentoPdf linestenerPdf;
    private EnvioDocumento listener;
    private int idTarefa, idAtividade;

    public EnvioEmailPdfAsyncTask(Context contexto, int idTarefa, int idAtividade, VvmshBaseDados vvmshBaseDados, DocumentoPdf linestenerPdf, EnvioDocumento listener) {
        super(contexto);

        this.vvmshBaseDados = vvmshBaseDados;
        this.linestenerPdf = linestenerPdf;
        this.listener = listener;

        this.idTarefa = idTarefa;
        this.idAtividade = idAtividade;
    }


    @Override
    protected void completarEnvio() {
        super.completarEnvio();

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {
                    linestenerPdf.sincronizar(idTarefa, idAtividade);
                }
                catch(SQLiteConstraintException throwable){
                    erro = throwable.getMessage();
                }
            }
        });
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        listener.terminarExecucao();
    }
}
