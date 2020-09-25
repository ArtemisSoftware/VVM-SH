package com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.TrabalhadoresVulneraveisRepositorio;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TrabalhadoresVulneraveisViewModel extends BaseViewModel {

    private final TrabalhadoresVulneraveisRepositorio trabalhadoresVulneraveisRepositorio;


    //public MutableLiveData<lolo> gogo;

    @Inject
    public TrabalhadoresVulneraveisViewModel(TrabalhadoresVulneraveisRepositorio trabalhadoresVulneraveisRepositorio){

        this.trabalhadoresVulneraveisRepositorio = trabalhadoresVulneraveisRepositorio;
        //formandos = new MutableLiveData<>();
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

                                showProgressBar(false);

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );

    }

    //----------------
    //OBTER
    //----------------

}
