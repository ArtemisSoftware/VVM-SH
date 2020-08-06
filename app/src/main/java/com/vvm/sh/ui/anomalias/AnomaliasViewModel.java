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

import io.reactivex.Observer;
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

            anomaliaRepositorio.inserir(anomalia).toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Long>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onNext(Long aLong) {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                }

                                @Override
                                public void onError(Throwable e) {
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }

                                @Override
                                public void onComplete() {

                                }
                            }
                    );
        }
        else {
            anomaliaRepositorio.atualizar(anomalia).toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onNext(Integer integer) {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                                }

                                @Override
                                public void onError(Throwable e) {
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }

                                @Override
                                public void onComplete() {

                                }
                            }

                    );
        }


        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, anomaliaRepositorio.resultadoDao);
        servico.execute(new Resultado(anomalia.idTarefa, ResultadoId.ANOMALIA_CLIENTE));

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
     * Metodo que permite obter as anomalias registadas
     * @param idTarefa o identificador da tarefa
     */
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

        anomaliaRepositorio.obterAnomaliaRegistada(id).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<AnomaliaRegistada>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(AnomaliaRegistada registo) {
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
                                showProgressBar(false);
                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }

                );

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

        anomaliaRepositorio.remover(anomalia.resultado.id).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(Integer integer) {
                                messagemLiveData.setValue(Recurso.successo());
                            }

                            @Override
                            public void onError(Throwable e) {
                                messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                            }

                            @Override
                            public void onComplete() {

                            }
                        }

                );

        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, anomaliaRepositorio.resultadoDao);
        servico.execute(new Resultado(idTarefa, ResultadoId.ANOMALIA_CLIENTE));
    }
}
