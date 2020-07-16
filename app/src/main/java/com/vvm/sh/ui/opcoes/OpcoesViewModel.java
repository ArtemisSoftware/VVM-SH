package com.vvm.sh.ui.opcoes;

import android.content.Context;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.VersaoApp;
import com.vvm.sh.repositorios.VersaoAppRepositorio;
import com.vvm.sh.servicos.ServicoDownloadApk;
import com.vvm.sh.servicos.ServicoInstalacaoApk;
import com.vvm.sh.ui.contaUtilizador.Colecao;
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
    public MutableLiveData<VersaoApp> versaoApp;
    public MutableLiveData<List<Colecao>> tipos;


    @Inject
    public OpcoesViewModel(VersaoAppRepositorio versaoAppRepositorio){

        this.versaoAppRepositorio = versaoAppRepositorio;

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



    //---------------------
    //Tipos
    //---------------------




    //------------------
    //Atualizacao
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
