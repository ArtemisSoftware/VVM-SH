package com.vvm.sh.ui.anomalias;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.AnomaliaRepositorio;
import com.vvm.sh.ui.anomalias.modelos.Anomalia;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AnomaliasViewModel extends BaseViewModel {

    private final AnomaliaRepositorio anomaliaRepositorio;

    public MutableLiveData<List<Anomalia>> anomalias;

    @Inject
    public AnomaliasViewModel(AnomaliaRepositorio anomaliaRepositorio){

        this.anomaliaRepositorio = anomaliaRepositorio;
        anomalias = new MutableLiveData<>();
    }



    /**
     * Metodo que permite obter as anomalias
     * @param idTarefa o identificador do utilizador
     */
    public void obterAnomalias(int idTarefa){

        showProgressBar(true);

        anomaliaRepositorio.obterAnomalias(idTarefa).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<Anomalia>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Anomalia> registo) {

                                anomalias.setValue(registo);

                                showProgressBar(false);
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
