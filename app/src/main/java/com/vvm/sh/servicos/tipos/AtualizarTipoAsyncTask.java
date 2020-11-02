package com.vvm.sh.servicos.tipos;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.servicos.tipos.atualizacao.AtualizarTipoAtividadesPlaneaveisAsyncTask;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public class AtualizarTipoAsyncTask extends AsyncTask<List<Object>, Void, List<Object>[]> {

    private String errorMessage;
    private Handler handlerUI;
    private VvmshBaseDados vvmshBaseDados;
    private CarregamentoTiposRepositorio repositorio;



    /**
     * Permite enviar mensagens para fora do servico
     */
    private AtualizacaoUI atualizacaoUI;

    public AtualizarTipoAsyncTask(Context contexto, VvmshBaseDados vvmshBaseDados, Handler handlerUI, CarregamentoTiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        atualizacaoUI = new AtualizacaoUI(handlerUI);

        this.handlerUI = handlerUI;
    }



    @Override
    protected List<Object>[] doInBackground(List<Object>... tipoRespostas) {

        if(tipoRespostas[0] == null)
            return null;

        List<Object> respostas = tipoRespostas[0];
        List<ITipoListagem> dados = new ArrayList<>();

        for (Object item : respostas) {

            if (item instanceof ITipoListagem) {
                dados.add((ITipoListagem) item);
            }
        }


        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    int index = 0;

                    for (ITipoListagem resposta : dados) {

                        Atualizacao atualizacao = DownloadMapping.INSTANCE.map(resposta);

                        List<Tipo> dadosNovos = new ArrayList<>();
                        List<Tipo> dadosAlterados = new ArrayList<>();

                        for (ITipo item : resposta.dadosNovos) {
                            dadosNovos.add(DownloadMapping.INSTANCE.map(item, resposta));
                        }

                        for (ITipo item : resposta.dadosAlterados) {
                            dadosAlterados.add(DownloadMapping.INSTANCE.map(item, resposta));
                        }

                        repositorio.atualizarTipo(atualizacao, dadosNovos, dadosAlterados);

                        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TIPOS, atualizacao.descricao, ++index, dados.size());

                    }
                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return tipoRespostas;
    }


    @Override
    protected void onPostExecute(List<Object>[] objects) {
        super.onPostExecute(objects);

        AtualizarTipoAtividadesPlaneaveisAsyncTask servico = new AtualizarTipoAtividadesPlaneaveisAsyncTask(vvmshBaseDados, handlerUI, repositorio);
        servico.execute(objects);
    }
}
