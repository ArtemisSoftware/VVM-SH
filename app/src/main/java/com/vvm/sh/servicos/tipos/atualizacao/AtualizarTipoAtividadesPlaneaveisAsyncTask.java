package com.vvm.sh.servicos.tipos.atualizacao;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavel;
import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavelListagem;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public class AtualizarTipoAtividadesPlaneaveisAsyncTask extends AsyncTask<List<Object>, Void, List<Object>[]> {

    private String errorMessage;
    private Handler handlerUI;
    private VvmshBaseDados vvmshBaseDados;
    private CarregamentoTiposRepositorio repositorio;


    /**
     * Permite enviar mensagens para fora do servico
     */
    private AtualizacaoUI atualizacaoUI;

    public AtualizarTipoAtividadesPlaneaveisAsyncTask(VvmshBaseDados vvmshBaseDados, Handler handlerUI, CarregamentoTiposRepositorio repositorio){
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
        List<ITipoAtividadePlaneavelListagem> dados = new ArrayList<>();

        for (Object item : respostas) {

            if (item instanceof ITipoAtividadePlaneavelListagem) {
                dados.add((ITipoAtividadePlaneavelListagem) item);
            }
        }

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    int index = 0;
                    
                    for (ITipoAtividadePlaneavelListagem resposta : dados) {

                        Atualizacao atualizacao = DownloadMapping.INSTANCE.map(resposta);

                        if(atualizacao.tipo == Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS) {

                            List<TipoAtividadePlaneavel> dadosNovos = new ArrayList<>();
                            List<TipoAtividadePlaneavel> dadosAlterados = new ArrayList<>();

                            for (ITipoAtividadePlaneavel item : resposta.dadosNovos) {
                                dadosNovos.add(DownloadMapping.INSTANCE.map(item, resposta));
                            }

                            for (ITipoAtividadePlaneavel item : resposta.dadosAlterados) {
                                dadosNovos.add(DownloadMapping.INSTANCE.map(item, resposta));
                            }

                            repositorio.atualizarTipoAtividadesPlaneaveis(atualizacao, dadosNovos, dadosAlterados);

                            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TIPOS, atualizacao.descricao, ++index, dados.size());
                        }
                    }
                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }



    @Override
    protected void onPostExecute(List<Object>[] objects) {
        super.onPostExecute(objects);

//        AtualizarTipoAtividadesPlaneaveisAsyncTask servico = new AtualizarTipoAtividadesPlaneaveisAsyncTask(vvmshBaseDados, handlerUI, repositorio);
//        servico.execute(objects);
    }
}
