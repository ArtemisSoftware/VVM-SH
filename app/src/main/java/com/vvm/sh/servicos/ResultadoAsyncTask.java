package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Resultado;


public class ResultadoAsyncTask extends AsyncTask<Resultado, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;

    private ResultadoDao resultadoDao;
    private int idAtividade;

    public ResultadoAsyncTask(VvmshBaseDados vvmshBaseDados, ResultadoDao resultadoDao){
        this.vvmshBaseDados = vvmshBaseDados;
        this.resultadoDao = resultadoDao;
        this.idAtividade = 0;
    }

    public ResultadoAsyncTask(VvmshBaseDados vvmshBaseDados, ResultadoDao resultadoDao, int idAtividade){
        this.vvmshBaseDados = vvmshBaseDados;
        this.resultadoDao = resultadoDao;
        this.idAtividade = idAtividade;
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
                    if(idAtividade == 0) {
                        resultadoDao.upsert(resposta);
                    }
                    else{
                        resultadoDao.upsert(resposta, idAtividade);
                    }
                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }


}
