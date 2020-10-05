package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public class CarregarTipoAsyncTask extends AsyncTask<List<ITipoListagem>, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;
    private TiposRepositorio repositorio;


    /**
     * Permite enviar mensagens para fora do servico
     */
    private AtualizacaoUI atualizacaoUI;

    public CarregarTipoAsyncTask(VvmshBaseDados vvmshBaseDados, Handler handler, TiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        atualizacaoUI = new AtualizacaoUI(handler);
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


                    int index = 0;

                    for(ITipoListagem resposta : respostas){

                        List<Tipo> dadosNovos = new ArrayList<>();
                        List<Tipo> dadosAlterados = new ArrayList<>();

                        Atualizacao atualizacao = DownloadMapping.INSTANCE.map(resposta);


                        for (ITipo item : resposta.dadosNovos) {
                            dadosNovos.add(DownloadMapping.INSTANCE.map(item, resposta));
                        }

                        for (ITipo item : resposta.dadosAlterados) {
                            dadosAlterados.add(DownloadMapping.INSTANCE.map(item, resposta));
                        }

                        repositorio.carregarTipo(atualizacao, dadosNovos, dadosAlterados);

                        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, atualizacao.descricao, ++index, respostas.size());
                    }

                    atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TIPOS_CONCLUIDO, "Concluido", index, respostas.size());

                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }
}
