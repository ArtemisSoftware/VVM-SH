package com.vvm.sh.ui.opcoes;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.VersaoApp;
import com.vvm.sh.repositorios.VersaoAppRepositorio;
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



    public void obterAtualizacao() {



        versaoAppRepositorio.obterAtualizacao()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<VersaoApp>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(VersaoApp versaoApp) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }


                );

    }


}
