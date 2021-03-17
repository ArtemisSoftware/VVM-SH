package com.vvm.sh.servicos.tipos.atualizacao;

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
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public class AtualizarTipoAsyncTask_ extends AsyncTask<List<Object>, Void, List<Object>[]> {

    protected String erro;
    protected VvmshBaseDados vvmshBaseDados;
    protected CarregamentoTiposRepositorio repositorio;
    protected OnTransferenciaListener listener;


    public AtualizarTipoAsyncTask_(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio){

        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.listener = listener;
        this.erro = null;
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

                        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TIPOS, ++index, dados.size(), obterDescricaoApi(atualizacao) + " " + atualizacao.descricao));
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

        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TIPOS, erro));


        if(AppConfig.APP_MODO == AppConfig.APP_SA){
            listener.terminarTransferencia();
        }
        else{//listener.terminarTransferencia();
            AtualizarTipoAtividadesPlaneaveisAsyncTask_ servico = new AtualizarTipoAtividadesPlaneaveisAsyncTask_(listener, vvmshBaseDados, repositorio);
            servico.execute(objects);
        }




//        if(AppConfig.APP_MODO == AppConfig.APP_SA){
//            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TIPOS_CONCLUIDO, "Concluido", 2, 2);
//        }
//        else {
//            AtualizarTipoAtividadesPlaneaveisAsyncTask servico = new AtualizarTipoAtividadesPlaneaveisAsyncTask(vvmshBaseDados, handlerUI, repositorio);
//            servico.execute(objects);
//        }
    }


    protected String obterDescricaoApi(Atualizacao atualizacao){

        String resultado = "";

        switch (atualizacao.api){

            case Identificadores.App.APP_SA:

                resultado = Identificadores.App.SA;
                break;

            case Identificadores.App.APP_ST:

                resultado = Identificadores.App.ST;
                break;

            default:
                break;

        }

        return resultado;

    }

}
