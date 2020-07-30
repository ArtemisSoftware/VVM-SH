package com.vvm.sh.ui.anomalias;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.AnomaliaRepositorio;
import com.vvm.sh.ui.anomalias.modelos.Anomalia;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaRegistada;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AnomaliasViewModel extends BaseViewModel {

    private final AnomaliaRepositorio anomaliaRepositorio;

    public MutableLiveData<List<Anomalia>> anomalias;
    public MutableLiveData<List<AnomaliaRegistada>> anomaliasResultados;

    public MutableLiveData<List<Tipo>> tiposAnomalias;
    public MutableLiveData<AnomaliaResultado> anomaliaResultado;

    public MutableLiveData<List<Tipo>> estados;


    @Inject
    public AnomaliasViewModel(AnomaliaRepositorio anomaliaRepositorio){

        this.anomaliaRepositorio = anomaliaRepositorio;
        anomalias = new MutableLiveData<>();
        tiposAnomalias = new MutableLiveData<>();
        anomaliaResultado = new MutableLiveData<>();
        anomaliasResultados = new MutableLiveData<>();
        estados = new MutableLiveData<>();
    }

    //--------------------
    //OBTER
    //--------------------

    /**
     * Metodo que permite obter as anomalias
     * @param idTarefa o identificador do utilizador
     */
    public void obterAnomalias(int idTarefa){

        obterOpcoesRegistos();
        obterAnomaliasExistentes(idTarefa);
    }

    public void obterAnomaliasExistentes(int idTarefa) {

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


    public void obterAnomaliasRegistadas(int idTarefa) {

        showProgressBar(true);

        anomaliaRepositorio.obterAnomaliasRegistadas(idTarefa).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<AnomaliaRegistada>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<AnomaliaRegistada> registo) {

                                anomaliasResultados.setValue(registo);
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






    /**
     * Metodo que permite obter as opcoes dos registos
     */
    private void obterOpcoesRegistos() {
        List<Tipo> estado = new ArrayList<>();

        estado.add(TiposConstantes.CONSULTAR);
        estado.add(TiposConstantes.NOVOS_REGISTOS);
        estados.setValue(estado);
    }


    public void obterAnomalia(int id){

        obterTipos();

        if(id != 0){

            anomaliaRepositorio.obterAnomaliaResultado(id).toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<AnomaliaResultado>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(AnomaliaResultado registo) {
                                    anomaliaResultado.setValue(registo);
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

        if (anomalia.id == 0) {

            anomaliaRepositorio.inserir(anomalia).toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Long>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Long aLong) {

                                    messagemLiveData.setValue(Recurso.successo());
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            }

                    );

        } else {
            anomaliaRepositorio.atualizar(anomalia).toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Integer integer) {
                                    messagemLiveData.setValue(Recurso.successo());
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

    public void remover(int idAnomalia) {

        anomaliaRepositorio.remover(idAnomalia).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integer) {

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
