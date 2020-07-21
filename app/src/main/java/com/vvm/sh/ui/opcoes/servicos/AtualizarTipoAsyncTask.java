package com.vvm.sh.ui.opcoes.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.api.modelos.TipoResultado;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.ui.opcoes.modelos.Atualizacao;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.mapeamento.ModelMapping;

import java.util.ArrayList;
import java.util.List;

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

        Atualizacao atualizacao = ModelMapping.INSTANCE.map(resposta);

        List<Tipo> tipos = new ArrayList<>();

        for (TipoResultado item : resposta.dadosNovos) {

            Tipo resultado = ModelMapping.INSTANCE.map(item);
            resultado.tipo = resposta.tipo;
            tipos.add(resultado);
        }


        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    repositorio.atualizarTipo(resposta.tipo, atualizacao, tipos);
                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }
}
