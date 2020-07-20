package com.vvm.sh.autenticacao;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.UtilizadorResposta;
import com.vvm.sh.api.modelos.UtilizadorResultado;
import com.vvm.sh.repositorios.AutenticacaoRepositorio;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AutenticacaoViewModel extends BaseViewModel {


    private final AutenticacaoRepositorio autenticacaoRepositorio;


    @Inject
    public AutenticacaoViewModel(AutenticacaoRepositorio autenticacaoRepositorio){

        this.autenticacaoRepositorio = autenticacaoRepositorio;
    }



    public void autenticar(String idUtilizador, String palavraChave) {

        showProgressBar(true);

        autenticacaoRepositorio.obterUtilizadores()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<UtilizadorResposta>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(UtilizadorResposta resposta) {


                                autenticar(resposta, idUtilizador, palavraChave);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                                showProgressBar(false);
                            }
                        }


                );
    }



    private void autenticar(UtilizadorResposta resposta, String idUtilizador, String palavraChave){


        for (UtilizadorResultado registo : resposta.dadosNovos) {


            if(registo.id.equals(idUtilizador) == true & registo.ativo == true){


            }
        }

    }


}
