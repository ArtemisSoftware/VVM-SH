package com.vvm.sh.ui.transferencias.atualizacaoApp;

import android.content.Context;
import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.pedido.IVersaoApp;
import com.vvm.sh.repositorios.VersaoAppRepositorio;
import com.vvm.sh.servicos.instalacaoApp.DownloadApkAsyncTask;
import com.vvm.sh.servicos.instalacaoApp.InstalarApkAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AtualizacaoAppViewModel extends BaseViewModel {

    private final VersaoAppRepositorio versaoAppRepositorio;


    private MutableLiveData<IVersaoApp> _versaoApp;
    public LiveData<IVersaoApp> versaoApp = _versaoApp;

    @Inject
    public AtualizacaoAppViewModel(VersaoAppRepositorio versaoAppRepositorio){

        this.versaoAppRepositorio = versaoAppRepositorio;

        _versaoApp = new MutableLiveData<>();
    }


    /**
     * Metodo que permite obter a atualizacao da app
     * @param idUtilizador o identificador do utilizador
     */
    public void obterAtualizacao(String idUtilizador) {

        showProgressBar(true);

        versaoAppRepositorio.obterAtualizacao(idUtilizador)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<IVersaoApp>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(IVersaoApp resposta) {
                                _versaoApp.setValue(resposta);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                                showProgressBar(false);
                                formatarErro(e);
                            }
                        }
                );
    }


    /**
     * Metodo que inicia o download de um apk
     * @param contexto
     */
    public void downloadApp(OnTransferenciaListener listener, Context contexto) {

        DownloadApkAsyncTask servico = new DownloadApkAsyncTask(listener, contexto);
        servico.execute(_versaoApp.getValue());
    }


    /**
     * Metodo que inicia a instalação de um apk
     * @param contexto
     */
    public void instalarApp(OnTransferenciaListener listener, Context contexto) {

        InstalarApkAsyncTask servico = new InstalarApkAsyncTask(listener, contexto);
        servico.execute(_versaoApp.getValue());
    }


}
