package com.vvm.sh.ui.anomalias;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.AnomaliaRepositorio;
import com.vvm.sh.ui.anomalias.modelos.Anomalia;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
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
    public MutableLiveData<List<AnomaliaResultado>> anomaliasResultados;

    public MutableLiveData<List<Tipo>> tiposAnomalias;
    public MutableLiveData<AnomaliaResultado> anomaliaResultado;

    @Inject
    public AnomaliasViewModel(AnomaliaRepositorio anomaliaRepositorio){

        this.anomaliaRepositorio = anomaliaRepositorio;
        anomalias = new MutableLiveData<>();
        tiposAnomalias = new MutableLiveData<>();
        anomaliaResultado = new MutableLiveData<>();
        anomaliasResultados = new MutableLiveData<>();
    }

    //--------------------
    //OBTER
    //--------------------

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
                                disposables.add(d);
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
                                showProgressBar(false);
                            }
                        }

                );
    }



    public void obterAnomalia(int id){

        obterTipos();

        if(id != 0){

        }
    }



    /**
     * Metodo que permite obter os tipos
     */
    private void obterTipos(){

        showProgressBar(true);

        anomaliaRepositorio.obterTiposAnomalias().toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Tipo> registos) {

                                tiposAnomalias.setValue(registos);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }

                );

    }


    public void gravar(AnomaliaResultado anomalia) {
    }
}
