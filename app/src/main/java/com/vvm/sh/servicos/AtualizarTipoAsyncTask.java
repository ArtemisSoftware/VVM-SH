package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.api.modelos.TipoResultado;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.mapeamento.DownloadMapping;

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

                    //TODO: verificar a api
                    for(TipoResposta resposta : respostas){

                        Atualizacao atualizacao = DownloadMapping.INSTANCE.map(resposta);
                        List<Tipo> tipos = obterTipos(resposta);

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


    /**
     * Metodo que permite obter os tipos
     * @param resposta os dados dos tipos
     * @return uma lista de tipos
     */
    private List<Tipo> obterTipos(TipoResposta resposta) {

        List<Tipo> tipos = new ArrayList<>();

        for (TipoResultado item : resposta.dadosNovos) {

            Tipo resultado = DownloadMapping.INSTANCE.map(item, resposta);
            tipos.add(resultado);
        }

        return tipos;
    }
}
