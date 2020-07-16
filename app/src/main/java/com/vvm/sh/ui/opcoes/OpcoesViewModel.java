package com.vvm.sh.ui.opcoes;

import android.content.Context;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.api.modelos.VersaoApp;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.VersaoAppRepositorio;
import com.vvm.sh.servicos.ServicoDownloadApk;
import com.vvm.sh.servicos.ServicoInstalacaoApk;
import com.vvm.sh.ui.contaUtilizador.Colecao;
import com.vvm.sh.ui.opcoes.servicos.AtualizarTipoAsyncTask;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OpcoesViewModel extends BaseViewModel {


    private final VersaoAppRepositorio versaoAppRepositorio;
    private final TiposRepositorio tiposRepositorio;
    public MutableLiveData<VersaoApp> versaoApp;
    public MutableLiveData<List<Colecao>> tipos;


    @Inject
    VvmshBaseDados vvmshBaseDados;


    @Inject
    public OpcoesViewModel(VersaoAppRepositorio versaoAppRepositorio, TiposRepositorio tiposRepositorio){

        this.versaoAppRepositorio = versaoAppRepositorio;
        this.tiposRepositorio = tiposRepositorio;

        versaoApp = new MutableLiveData<>();
        tipos = new MutableLiveData<>();
    }


    public MutableLiveData<VersaoApp> observarVersaoApp(){
        return versaoApp;
    }


    public void obterTipos(){

        List<Colecao> t1 = new ArrayList<>();
        t1.add(new Colecao("GetCrossSellingProdutos", 2, "2019-04-12"));
        t1.add(new Colecao("Cross-Selling Dimensao", 4, "2019-06-22"));



        tipos.setValue(t1);
    }


    public void obterTipo(String metodo) {

        showProgressBar(true);

        tiposRepositorio.obterTipo(metodo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<TipoResposta>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(TipoResposta tipoResposta) {

                                tipoResposta.tipo = metodo;
                                AtualizarTipoAsyncTask servico = new AtualizarTipoAsyncTask(vvmshBaseDados, tiposRepositorio);
                                servico.execute(tipoResposta);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }


                );
    }

    //---------------------
    //Tipos
    //---------------------




    //------------------
    //AtualizacaoDao
    //------------------



    /**
     * Metodo que permite obter a atualizacao da app
     */
    public void obterAtualizacao() {

        showProgressBar(true);

        versaoAppRepositorio.obterAtualizacao()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<VersaoApp>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(VersaoApp resposta) {
                                resposta.fixarUtilizador("500005");
                                versaoApp.setValue(resposta);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                                showProgressBar(false);
                            }
                        }


                );
    }


    /**
     * Metodo que inicia o download de um apk
     * @param contexto
     * @param handler
     */
    public void downloadApp(Context contexto, Handler handler) {

        ServicoDownloadApk servico = new ServicoDownloadApk(contexto, handler, versaoApp.getValue());
        servico.execute();
    }


    /**
     * Metodo que inicia a instalação de um apk
     * @param contexto
     * @param handler
     */
    public void instalarApp(Context contexto, Handler handler) {

        ServicoInstalacaoApk servico = new ServicoInstalacaoApk(contexto, handler, versaoApp.getValue());
        servico.execute();
    }
}