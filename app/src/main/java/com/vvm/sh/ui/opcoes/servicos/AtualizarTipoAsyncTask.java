package com.vvm.sh.ui.opcoes.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TiposRepositorio;

public class AtualizarTipoAsyncTask extends AsyncTask<TipoResposta, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;
    private TiposRepositorio repositorio;

    public AtualizarTipoAsyncTask(VvmshBaseDados vvmshBaseDados, TiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
    }


    @Override
    protected Void doInBackground(TipoResposta... tipoRespostas) {

        if(tipoRespostas[0] == null)
            return null;

        TipoResposta resposta = tipoRespostas[0];


        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    repositorio.atualizarTipo(resposta.tipo);
                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }
}
