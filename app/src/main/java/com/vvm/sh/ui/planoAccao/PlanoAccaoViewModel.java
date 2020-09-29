package com.vvm.sh.ui.planoAccao;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.PlanoAccaoRepositorio;
import com.vvm.sh.ui.planoAccao.modelo.AtividadeRegisto;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PlanoAccaoViewModel {

    private final PlanoAccaoRepositorio planoAccaoRepositorio;
    public MutableLiveData<List<AtividadeRegisto>> atividades;

    @Inject
    public PlanoAccaoViewModel(PlanoAccaoRepositorio planoAccaoRepositorio){

        this.planoAccaoRepositorio = planoAccaoRepositorio;

        atividades = new MutableLiveData<>();
    }



    //---------------------
    //OBTER
    //---------------------


    public void obterAtividades(int idTarefa){


        planoAccaoRepositorio.obterAtividades(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<AtividadeRegisto>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<AtividadeRegisto> registos) {
                                atividades.setValue(registos);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }

                );

    }

}
