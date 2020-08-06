package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.api.modelos.TipoResultado;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.mapeamento.ModelMapping;

import java.util.ArrayList;
import java.util.List;

public class AtualizarTipoAsyncTask extends AsyncTask<List<TipoResposta>, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;
    private TiposRepositorio repositorio;

    public AtualizarTipoAsyncTask(VvmshBaseDados vvmshBaseDados, TiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
    }


    @Override
    protected Void doInBackground(List<TipoResposta>... tipoRespostas) {

        if(tipoRespostas[0] == null)
            return null;

        List<TipoResposta> respostas = tipoRespostas[0];

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    for(TipoResposta resposta : respostas){

                        List<Tipo> tipos = new ArrayList<>();

                        Atualizacao atualizacao = ModelMapping.INSTANCE.map(resposta);

                        for (TipoResultado item : resposta.dadosNovos) {

                            Tipo resultado = ModelMapping.INSTANCE.map(item, resposta);
                            tipos.add(resultado);
                        }

                        repositorio.atualizarTipo(atualizacao, tipos);
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
