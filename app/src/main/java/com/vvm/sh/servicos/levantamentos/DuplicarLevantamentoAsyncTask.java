package com.vvm.sh.servicos.levantamentos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.util.AtualizacaoUI;

import java.util.List;

public class DuplicarLevantamentoAsyncTask  extends AsyncTask<Integer, Void, Void> {


    private String errorMessage;
    private int idLevantamentoOriginal;
    private VvmshBaseDados vvmshBaseDados;

    public DuplicarLevantamentoAsyncTask(VvmshBaseDados vvmshBaseDados, int idLevantamentoOriginal){
        this.vvmshBaseDados = vvmshBaseDados;
        this.idLevantamentoOriginal = idLevantamentoOriginal;
    }


    @Override
    protected Void doInBackground(Integer... integers) {

        if(integers[0] == null)
            return null;

        int idLevantamentoNovo = integers[0];

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){
                try {



                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }
}
