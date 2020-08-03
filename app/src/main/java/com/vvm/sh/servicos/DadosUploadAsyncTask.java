package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AgendaRepositorio;

import java.util.List;

public class DadosUploadAsyncTask  extends AsyncTask<List<Integer>, Void, Void> {

    private String errorMessage, idUtilizador;
    private VvmshBaseDados vvmshBaseDados;
    private AgendaRepositorio repositorio;

    public DadosUploadAsyncTask(VvmshBaseDados vvmshBaseDados, AgendaRepositorio repositorio, String idUtilizador){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.idUtilizador = idUtilizador;
    }

    @Override
    protected Void doInBackground(List<Integer>... resultados) {

        if(resultados[0] == null)
            return null;

        List<Integer> resposta = resultados[0];



        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    //repositorio.obterEmail();


                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });


        return null;
    }
}
