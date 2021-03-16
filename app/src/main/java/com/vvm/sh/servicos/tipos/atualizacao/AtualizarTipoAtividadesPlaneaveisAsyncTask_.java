package com.vvm.sh.servicos.tipos.atualizacao;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavel;
import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavelListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public class AtualizarTipoAtividadesPlaneaveisAsyncTask_ extends AtualizarTipoAsyncTask_ {


    public AtualizarTipoAtividadesPlaneaveisAsyncTask_(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio){
        super(listener, vvmshBaseDados, repositorio);
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
                            listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_ATIVIDADES_PLANEAVEIS, ++index, dados.size(), obterDescricaoApi(atualizacao) + " " + atualizacao.descricao));

                        }
                    }
                }
                catch(SQLiteConstraintException throwable){
                    erro = throwable.getMessage();
                }
            }
        });

        return tipoRespostas;
    }



    @Override
    protected void onPostExecute(List<Object>[] objects) {

        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_ATIVIDADES_PLANEAVEIS, erro));


        AtualizarTipoTemplatesAvrAsyncTask_ servico = new AtualizarTipoTemplatesAvrAsyncTask_(listener, vvmshBaseDados, repositorio);
        servico.execute(objects);
    }
}
