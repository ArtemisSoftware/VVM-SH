package com.vvm.sh.servicos.tipos.atualizacao;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrLevantamento;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrLevantamentoListagem;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRisco;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRiscoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public class AtualizarTipoTemplatesAvrAsyncTask_ extends AtualizarTipoAsyncTask_ {


    public AtualizarTipoTemplatesAvrAsyncTask_(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio){
        super(listener, vvmshBaseDados, repositorio);
    }


    @Override
    protected List<Object>[] doInBackground(List<Object>... tipoRespostas) {

        if(tipoRespostas[0] == null)
            return null;


        List<Object> respostas = tipoRespostas[0];
        ITipoTemplateAvrLevantamentoListagem dadosLevantamento = null;
        ITipoTemplateAvrRiscoListagem dadosRisco  = null;

        for (Object item : respostas) {

            if (item instanceof ITipoTemplateAvrLevantamentoListagem) {
                dadosLevantamento = (ITipoTemplateAvrLevantamentoListagem) item;
            }

            if (item instanceof ITipoTemplateAvrRiscoListagem) {
                dadosRisco = (ITipoTemplateAvrRiscoListagem) item;
            }
        }

        ITipoTemplateAvrLevantamentoListagem finalDadosLevantamento = dadosLevantamento;
        ITipoTemplateAvrRiscoListagem finalDadosRisco = dadosRisco;

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

//                try {
//
//                    Atualizacao atualizacao = null;
//                    List<TipoTemplateAvrLevantamento> dadosNovos = new ArrayList<>();
//                    List<TipoTemplateAvrLevantamento> dadosAlterados = new ArrayList<>();
//
//
//                    if(finalDadosLevantamento != null) {
//
//                        atualizacao = DownloadMapping.INSTANCE.map(finalDadosLevantamento);
//
//                        for (ITipoTemplateAvrLevantamento item : finalDadosLevantamento.dadosNovos) {
//
//                            TipoTemplateAvrLevantamento registo = DownloadMapping.INSTANCE.map(item);
//                            dadosNovos.add(registo);
//                        }
//
//                        for (ITipoTemplateAvrLevantamento item : finalDadosLevantamento.dadosAlterados) {
//                            dadosAlterados.add(DownloadMapping.INSTANCE.map(item));
//                        }
//
//                        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TEMPLATE_AVALIACAO_RISCOS, 1, 2, obterDescricaoApi(atualizacao) + " " + atualizacao.descricao));
//
//                    }
//
//
//                    Atualizacao atualizacaoRisco = null;
//                    List<TipoTemplateAvrRisco> dadosNovosRiscos = new ArrayList<>();
//                    List<TipoTemplateAvrRisco> dadosAlteradosRiscos = new ArrayList<>();
//
//                    List<TipoTemplatesAVRMedidaRisco> medidasExistentes = new ArrayList<>();
//                    List<TipoTemplatesAVRMedidaRisco> medidasAlteradasExistentes = new ArrayList<>();
//                    List<TipoTemplatesAVRMedidaRisco> medidasRecomendadas = new ArrayList<>();
//                    List<TipoTemplatesAVRMedidaRisco> medidasAlteradasRecomendadas = new ArrayList<>();
//
//                    if(finalDadosRisco != null) {
//
//                        atualizacaoRisco = DownloadMapping.INSTANCE.map(finalDadosRisco);
//
//                        for (ITipoTemplateAvrRisco item : finalDadosRisco.dadosNovos) {
//
//                            TipoTemplateAvrRisco registo = DownloadMapping.INSTANCE.map(item);
//                            dadosNovosRiscos.add(registo);
//
//                            for (int medida : item.medidasExistentes) {
//                                medidasExistentes.add(new TipoTemplatesAVRMedidaRisco(registo.id, Identificadores.Origens.MEDIDAS_RISCO_EXISTENTES, medida));
//                            }
//
//                            for (int medida : item.medidasRecomendadas) {
//                                medidasRecomendadas.add(new TipoTemplatesAVRMedidaRisco(registo.id, Identificadores.Origens.MEDIDAS_RISCO_RECOMENDADAS, medida));
//                            }
//                        }
//
//                        for (ITipoTemplateAvrRisco item : finalDadosRisco.dadosAlterados) {
//
//                            TipoTemplateAvrRisco registo = DownloadMapping.INSTANCE.map(item);
//                            dadosAlteradosRiscos.add(registo);
//
//                            for (int medida : item.medidasExistentes) {
//                                medidasAlteradasExistentes.add(new TipoTemplatesAVRMedidaRisco(registo.id, Identificadores.Origens.MEDIDAS_RISCO_EXISTENTES, medida));
//                            }
//
//                            for (int medida : item.medidasRecomendadas) {
//                                medidasAlteradasRecomendadas.add(new TipoTemplatesAVRMedidaRisco(registo.id, Identificadores.Origens.MEDIDAS_RISCO_RECOMENDADAS, medida));
//                            }
//                        }
//
//                        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TEMPLATE_AVALIACAO_RISCOS, 2, 2, obterDescricaoApi(atualizacao) + " " + atualizacao.descricao));
//                    }
//
//
//                    repositorio.atualizarTipoTemplateAvr(
//                            atualizacao, dadosNovos, dadosAlterados,
//                            atualizacaoRisco, dadosNovosRiscos, dadosAlteradosRiscos,
//                            medidasExistentes, medidasAlteradasExistentes, medidasRecomendadas, medidasAlteradasRecomendadas);
//
//                    listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TEMPLATE_AVALIACAO_RISCOS, 1, 1, obterDescricaoApi(atualizacao) + " Templates"));
//
//                }
//                catch(SQLiteConstraintException throwable){
//                    erro = throwable.getMessage();
//                }
            }
        });

        return tipoRespostas;
    }


    @Override
    protected void onPostExecute(List<Object>[] objects) {

        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TEMPLATE_AVALIACAO_RISCOS, erro));


        AtualizarEquipamentosAsyncTask_ servico = new AtualizarEquipamentosAsyncTask_(listener, vvmshBaseDados, repositorio);
        servico.execute(objects);

    }
}
