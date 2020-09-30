package com.vvm.sh.ui.planoAccao;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.PlanoAccaoRepositorio;
import com.vvm.sh.ui.planoAccao.modelo.AtividadeRegisto;
import com.vvm.sh.ui.planoAccao.modelo.Plano;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PlanoAccaoViewModel extends BaseViewModel {

    private final PlanoAccaoRepositorio planoAccaoRepositorio;
    public MutableLiveData<List<AtividadeRegisto>> atividades;

    public MutableLiveData<Plano> plano;

    @Inject
    public PlanoAccaoViewModel(PlanoAccaoRepositorio planoAccaoRepositorio){

        this.planoAccaoRepositorio = planoAccaoRepositorio;

        atividades = new MutableLiveData<>();
        plano = new MutableLiveData<>();
    }



    public MutableLiveData<List<AtividadeRegisto>> observarAtividades(){
        return atividades;
    }

    public MutableLiveData<Plano> observarPlano(){
        return plano;
    }

    //---------------------
    //OBTER
    //---------------------


    public void obterAtividades(int idTarefa){


        planoAccaoRepositorio.obterPlano(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Plano>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Plano registo) {
                                plano.setValue(registo);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );


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
