package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.ui.agenda.modelos.Resultado;


public class ResultadoAsyncTask extends AsyncTask<Resultado, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;

    private ResultadoDao resultadoDao;

    public ResultadoAsyncTask(VvmshBaseDados vvmshBaseDados, ResultadoDao resultadoDao){
        this.vvmshBaseDados = vvmshBaseDados;
        this.resultadoDao = resultadoDao;
    }


    @Override
    protected Void doInBackground(Resultado... resultados) {

        if(resultados[0] == null)
            return null;

        Resultado resposta = resultados[0];

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {
                    resultadoDao.upsert(resposta);
                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }


}
