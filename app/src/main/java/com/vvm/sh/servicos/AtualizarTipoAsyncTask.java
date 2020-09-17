package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public class AtualizarTipoAsyncTask extends AsyncTask<List<ITipoListagem>, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;
    private TiposRepositorio repositorio;

    public AtualizarTipoAsyncTask(VvmshBaseDados vvmshBaseDados, TiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
    }


    @Override
    protected Void doInBackground(List<ITipoListagem>... tipoRespostas) {

        if(tipoRespostas[0] == null)
            return null;

        List<ITipoListagem> respostas = tipoRespostas[0];

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    List<Tipo> tipos = new ArrayList<>();

                    Atualizacao atualizacao = DownloadMapping.INSTANCE.map(respostas.get(0));

                    for(ITipoListagem resposta : respostas){
                        tipos.addAll(obterTipos(resposta));
                    }

                    repositorio.atualizarTipo(atualizacao, tipos);

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
    private List<Tipo> obterTipos(ITipoListagem resposta) {

        List<Tipo> tipos = new ArrayList<>();

        for (ITipo item : resposta.dadosNovos) {

            Tipo resultado = DownloadMapping.INSTANCE.map(item, resposta);
            tipos.add(resultado);
        }

        return tipos;
    }
}
