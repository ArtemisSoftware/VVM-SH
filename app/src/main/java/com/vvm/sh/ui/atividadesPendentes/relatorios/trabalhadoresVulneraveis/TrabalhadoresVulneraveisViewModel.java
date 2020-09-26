package com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.TrabalhadoresVulneraveisRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.modelos.TrabalhadorVulneravel;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TrabalhadoresVulneraveisViewModel extends BaseViewModel {

    private final TrabalhadoresVulneraveisRepositorio trabalhadoresVulneraveisRepositorio;


    public MutableLiveData<List<TrabalhadorVulneravel>> vulnerabilidades;

    @Inject
    public TrabalhadoresVulneraveisViewModel(TrabalhadoresVulneraveisRepositorio trabalhadoresVulneraveisRepositorio){

        this.trabalhadoresVulneraveisRepositorio = trabalhadoresVulneraveisRepositorio;
        vulnerabilidades = new MutableLiveData<>();
    }



    public void validarVulnerabilidades(int idAtividade){

        showProgressBar(true);

        trabalhadoresVulneraveisRepositorio.validarVulnerabilidades(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onComplete() {

                                obterVulnerabilidades(idAtividade);
                                showProgressBar(false);

                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }
                );
    }

    //----------------
    //OBTER
    //----------------


    public void obterVulnerabilidades(int idAtividade){


        showProgressBar(true);

        trabalhadoresVulneraveisRepositorio.obterVulnerabilidades(idAtividade)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                    new Observer<List<TrabalhadorVulneravel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<TrabalhadorVulneravel> registos) {
                            vulnerabilidades.setValue(registos);
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
