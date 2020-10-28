package com.vvm.sh.servicos.tipos;

import android.app.Activity;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrLevantamento;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRisco;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.ui.opcoes.modelos.TemplateAvr;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.MensagensUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CarregarTipoTemplatesAvrAsyncTask extends AsyncTask<TemplateAvr, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;
    private TiposRepositorio repositorio;
    private MensagensUtil dialogo;


    /**
     * Permite enviar mensagens para fora do servico
     */
    private AtualizacaoUI atualizacaoUI;

    public CarregarTipoTemplatesAvrAsyncTask(Activity activity, VvmshBaseDados vvmshBaseDados, Handler handlerUI, TiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        atualizacaoUI = new AtualizacaoUI(handlerUI);
        dialogo = new MensagensUtil(activity);
    }

    @Override
    protected void onPreExecute() {
        dialogo.progresso(true, Sintaxe.Frases.CARREGAR_TEMPLATES);
    }


    @Override
    protected Void doInBackground(TemplateAvr... templateAvrs) {


        if(templateAvrs[0] == null)
            return null;

        TemplateAvr resposta = templateAvrs[0];


        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    List<TipoTemplateAvrLevantamento> dadosNovos = new ArrayList<>();
                    List<TipoTemplateAvrLevantamento> dadosAlterados = new ArrayList<>();

                    Atualizacao atualizacao = DownloadMapping.INSTANCE.map(resposta.levantamentos);

                    for (ITipoTemplateAvrLevantamento item : resposta.levantamentos.dadosNovos) {

                        TipoTemplateAvrLevantamento registo = DownloadMapping.INSTANCE.map(item);
                        dadosNovos.add(registo);
                    }

                    for (ITipoTemplateAvrLevantamento item : resposta.levantamentos.dadosAlterados) {
                        dadosAlterados.add(DownloadMapping.INSTANCE.map(item));
                    }


                    List<TipoTemplateAvrRisco> dadosNovosRiscos = new ArrayList<>();
                    List<TipoTemplateAvrRisco> dadosAlteradosRiscos = new ArrayList<>();

                    List<TipoTemplatesAVRMedidaRisco> medidasExistentes = new ArrayList<>();
                    List<TipoTemplatesAVRMedidaRisco> medidasAlteradasExistentes = new ArrayList<>();
                    List<TipoTemplatesAVRMedidaRisco> medidasRecomendadas = new ArrayList<>();
                    List<TipoTemplatesAVRMedidaRisco> medidasAlteradasRecomendadas = new ArrayList<>();

                    Atualizacao atualizacaoRisco = DownloadMapping.INSTANCE.map(resposta.riscos);

                    for (ITipoTemplateAvrRisco item : resposta.riscos.dadosNovos) {

                        TipoTemplateAvrRisco registo = DownloadMapping.INSTANCE.map(item);
                        dadosNovosRiscos.add(registo);

                        for(int medida : item.medidasExistentes){
                            medidasExistentes.add(new TipoTemplatesAVRMedidaRisco(registo.id, Identificadores.Origens.MEDIDAS_RISCO_EXISTENTES, medida));
                        }

                        for(int medida : item.medidasRecomendadas){
                            medidasRecomendadas.add(new TipoTemplatesAVRMedidaRisco(registo.id, Identificadores.Origens.MEDIDAS_RISCO_RECOMENDADAS, medida));
                        }
                    }

                    for (ITipoTemplateAvrRisco item : resposta.riscos.dadosAlterados) {

                        TipoTemplateAvrRisco registo = DownloadMapping.INSTANCE.map(item);
                        dadosAlteradosRiscos.add(registo);

                        for(int medida : item.medidasExistentes){
                            medidasAlteradasExistentes.add(new TipoTemplatesAVRMedidaRisco(registo.id, Identificadores.Origens.MEDIDAS_RISCO_EXISTENTES, medida));
                        }

                        for(int medida : item.medidasRecomendadas){
                            medidasAlteradasRecomendadas.add(new TipoTemplatesAVRMedidaRisco(registo.id, Identificadores.Origens.MEDIDAS_RISCO_RECOMENDADAS, medida));
                        }
                    }



                    repositorio.carregarTipoTemplateAvr(atualizacao, dadosNovos, dadosAlterados,
                                                        atualizacaoRisco, dadosNovosRiscos, dadosAlteradosRiscos,
                                                        medidasExistentes, medidasAlteradasExistentes, medidasRecomendadas, medidasAlteradasRecomendadas);



                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialogo.terminarProgresso();
    }
}
