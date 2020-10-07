package com.vvm.sh.ui.anomalias;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.AnomaliaRepositorio;
import com.vvm.sh.servicos.ResultadoAsyncTask;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaRegistada;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AnomaliasViewModel extends BaseViewModel {

    private final AnomaliaRepositorio anomaliaRepositorio;

    public MutableLiveData<List<Anomalia>> anomalias;
    public MutableLiveData<List<AnomaliaRegistada>> anomaliasResultados;

    public MutableLiveData<List<Tipo>> tiposAnomalias;
    public MutableLiveData<AnomaliaRegistada> anomaliaRegistada;

    public MutableLiveData<List<Tipo>> estados;


    @Inject
    public AnomaliasViewModel(AnomaliaRepositorio anomaliaRepositorio){

        this.anomaliaRepositorio = anomaliaRepositorio;
        anomalias = new MutableLiveData<>();
        tiposAnomalias = new MutableLiveData<>();
        anomaliaRegistada = new MutableLiveData<>();
        anomaliasResultados = new MutableLiveData<>();
        estados = new MutableLiveData<>();
    }


    //--------------------
    //GRAVAR
    //--------------------


    /**
     * Metodo que permite gravar uma anomalia
     * @param anomalia os dados da anomalia
     */
    public void gravar(AnomaliaResultado anomalia) {

        if (anomalia.id == 0) {

            anomaliaRepositorio.inserir(anomalia)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Long>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Long aLong) {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                    gravarResultado(anomaliaRepositorio.resultadoDao, anomalia.idTarefa, ResultadoId.ANOMALIA_CLIENTE);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }
                    );
        }
        else {
            anomaliaRepositorio.atualizar(anomalia)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Integer integer) {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                                    gravarResultado(anomaliaRepositorio.resultadoDao, anomalia.idTarefa, ResultadoId.ANOMALIA_CLIENTE);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }

                    );
        }
    }



    //--------------------
    //REMOVER
    //--------------------


    /**
     * Metodo que permite remover uma anomalias
     * @param idTarefa o identificador da tarefa
     * @param anomalia os dados da anomalia
     */
    public void remover(int idTarefa, AnomaliaRegistada anomalia) {

        anomaliaRepositorio.remover(anomalia.resultado.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Integer integer) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_REMOVIDOS_SUCESSO));

                                gravarResultado(anomaliaRepositorio.resultadoDao, idTarefa, ResultadoId.ANOMALIA_CLIENTE);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }



    //--------------------
    //OBTER
    //--------------------

    /**
     * Metodo que permite obter as anomalias
     * @param idTarefa o identificador da tarefa
     */
    public void obterAnomalias(int idTarefa){

        obterOpcoesRegistos();
        obterAnomaliasExistentes(idTarefa);
    }


    /**
     * Metodo que permite obter as anomalias existentes
     * @param idTarefa o identificador da tarefa
     */
    public void obterAnomaliasExistentes(int idTarefa) {

        showProgressBar(true);

        anomaliaRepositorio.obterAnomalias(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Anomalia>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Anomalia> registo) {
                                anomalias.setValue(registo);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }

                );
    }


    /**
     * Metodo que permite obter as anomalias registadas
     * @param idTarefa o identificador da tarefa
     */
    public void obterAnomaliasRegistadas(int idTarefa) {

        showProgressBar(true);

        anomaliaRepositorio.obterAnomaliasRegistadas(idTarefa)
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
                                showProgressBar(false);
                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }

                );
    }


    /**
     * Metodo que permite obter uma anomalia registada
     * @param id o identificador da anomalia
     */
    public void obterAnomalia(int id){

        obterTipos();

        anomaliaRepositorio.obterAnomaliaRegistada(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<AnomaliaRegistada>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(AnomaliaRegistada registo) {
                                anomaliaRegistada.setValue(registo);
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



    //--------------------
    //MISC
    //--------------------


    /**
     * Metodo que permite obter as opcoes dos registos
     */
    private void obterOpcoesRegistos() {
        List<Tipo> estado = new ArrayList<>();

        estado.add(TiposConstantes.OpcoesRegistos.CONSULTAR);
        estado.add(TiposConstantes.OpcoesRegistos.NOVOS_REGISTOS);
        estados.setValue(estado);
    }



    /**
     * Metodo que permite obter os tipos
     */
    private void obterTipos(){

        showProgressBar(true);

        anomaliaRepositorio.obterTiposAnomalias()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Tipo> registos) {
                                tiposAnomalias.setValue(registos);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }

                );

    }


}
