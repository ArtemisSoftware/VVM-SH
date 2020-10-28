package com.vvm.sh.servicos.tipos;

import android.app.Activity;
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
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.MensagensUtil;

import java.util.ArrayList;
import java.util.List;

public class CarregarTipoAtividadesPlaneaveisAsyncTask extends AsyncTask<ITipoAtividadePlaneavelListagem, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;
    private TiposRepositorio repositorio;
    private MensagensUtil dialogo;


    /**
     * Permite enviar mensagens para fora do servico
     */
    private AtualizacaoUI atualizacaoUI;

    public CarregarTipoAtividadesPlaneaveisAsyncTask(Activity activity, VvmshBaseDados vvmshBaseDados, Handler handler, TiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        atualizacaoUI = new AtualizacaoUI(handler);
        dialogo = new MensagensUtil(activity);
    }

    @Override
    protected void onPreExecute() {
        dialogo.progresso(true, Sintaxe.Frases.CARREGAR_ATIVIDADES_PLANEAVEIS);
    }



    @Override
    protected Void doInBackground(ITipoAtividadePlaneavelListagem... tipoRespostas) {

        if(tipoRespostas[0] == null)
            return null;

        ITipoAtividadePlaneavelListagem resposta = tipoRespostas[0];

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    List<TipoAtividadePlaneavel> dadosNovos = new ArrayList<>();
                    List<TipoAtividadePlaneavel> dadosAlterados = new ArrayList<>();

                    Atualizacao atualizacao = DownloadMapping.INSTANCE.map(resposta);

                    for (ITipoAtividadePlaneavel item : resposta.dadosNovos) {
                        dadosNovos.add(DownloadMapping.INSTANCE.map(item, resposta));
                    }

                    for (ITipoAtividadePlaneavel item : resposta.dadosAlterados) {
                        dadosNovos.add(DownloadMapping.INSTANCE.map(item, resposta));
                    }

                    repositorio.carregarAtividadesPlaneaveis(atualizacao, dadosNovos, dadosAlterados);


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
