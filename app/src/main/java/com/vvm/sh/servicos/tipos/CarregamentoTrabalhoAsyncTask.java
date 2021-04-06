package com.vvm.sh.servicos.tipos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.repositorios.DownloadTrabalhoRepositorio;
import com.vvm.sh.ui.transferencias.modelos.Sessao;

import java.util.List;

public abstract class CarregamentoTrabalhoAsyncTask extends AsyncTask<Sessao, Void, Void> {

    protected String erro;
    protected VvmshBaseDados vvmshBaseDados;
    protected DownloadTrabalhoRepositorio repositorio;

    public CarregamentoTrabalhoAsyncTask(VvmshBaseDados vvmshBaseDados, DownloadTrabalhoRepositorio repositorio) {
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.erro = null;
    }

    @Override
    protected Void doInBackground(Sessao... sessoes) {


        if(sessoes[0] == null)
            return null;

        Sessao resposta = sessoes[0];

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    executar(resposta);
                }
                catch(SQLiteConstraintException throwable){
                    erro = throwable.getMessage();
                }


                executar(resposta);
            }
        });

        return null;
    }




    //-----------------------
    //Metodos abstratos
    //-----------------------

    protected abstract void executar(Sessao resposta);
}
