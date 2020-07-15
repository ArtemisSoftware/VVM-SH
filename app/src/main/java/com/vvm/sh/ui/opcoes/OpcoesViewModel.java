package com.vvm.sh.ui.opcoes;

import android.content.Context;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.VersaoApp;
import com.vvm.sh.repositorios.VersaoAppRepositorio;
import com.vvm.sh.servicos.ServicoDownloadApk;
import com.vvm.sh.servicos.ServicoInstalacaoApk;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OpcoesViewModel extends BaseViewModel {


    private final VersaoAppRepositorio versaoAppRepositorio;
    public MutableLiveData<VersaoApp> versaoApp;


    @Inject
    public OpcoesViewModel(VersaoAppRepositorio versaoAppRepositorio){

        this.versaoAppRepositorio = versaoAppRepositorio;

        versaoApp = new MutableLiveData<>();
    }


    public MutableLiveData<VersaoApp> observarVersaoApp(){
        return versaoApp;
    }


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



    public void downloadApp(Context contexto, Handler handler) {

        ServicoDownloadApk servico = new ServicoDownloadApk(contexto, handler, versaoApp.getValue());
        servico.execute();
    }

    public void instalarApp(Context contexto, Handler handler) {

        ServicoInstalacaoApk servico = new ServicoInstalacaoApk(contexto, handler, versaoApp.getValue());
        servico.execute();
    }
}
