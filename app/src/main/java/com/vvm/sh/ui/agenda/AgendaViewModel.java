package com.vvm.sh.ui.agenda;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.AgendaRepositorio;
import com.vvm.sh.ui.agenda.modelos.DataAgendamento;
import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class AgendaViewModel extends BaseViewModel {

    private final AgendaRepositorio agendaRepositorio;

    public MutableLiveData<List<Marcacao>> marcacoes;
    public MutableLiveData<Integer> completude;
    public MutableLiveData<List<DataAgendamento>> datas;


    @Inject
    public AgendaViewModel(AgendaRepositorio agendaRepositorio){

        this.agendaRepositorio = agendaRepositorio;
        marcacoes = new MutableLiveData<>();
        completude = new MutableLiveData<>();
        datas = new MutableLiveData<>();
    }


    public MutableLiveData<List<DataAgendamento>> observarDatas(){
        return datas;
    }


    public MutableLiveData<Integer> observarCompletude(){
        return completude;
    }



    //---------------------
    //OBTER
    //---------------------


    /**
     * Metodo que permite obter as marcacoes
     * @param idUtilizador o identificador do utilizador
     * @param data a data das marcacoes
     */
    public void obterMarcacoes(String idUtilizador, long data){

        marcacoes.setValue(new ArrayList<>());
        completude.setValue(Identificadores.Sincronizacao.SEM_SINCRONIZACAO);

        agendaRepositorio.obterCompletude(idUtilizador, data)
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
                                completude.setValue(integer);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );


        agendaRepositorio.obterMarcacoes(idUtilizador, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Marcacao>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Marcacao> marcacaos) {

                                marcacoes.setValue(marcacaos);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

//        Observable<Agenda> observables = Observable.zip(
//                agendaRepositorio.obterMarcacoes(idUtilizador, data),
//                agendaRepositorio.obterCompletude(idUtilizador, data),
//
//                new BiFunction<List<Marcacao>, Integer, Agenda>() {
//                    @Override
//                    public Agenda apply(List<Marcacao> marcacaos, Integer completude) throws Exception {
//                        return new Agenda(marcacaos, completude);
//                    }
//                });
//
//
//        observables
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//
//                        new Observer<Agenda>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//                                disposables.add(d);
//                            }
//
//                            @Override
//                            public void onNext(Agenda resultado) {
//                                agenda.setValue(resultado);
//                                completude_.setValue(Recurso.successo(resultado.completude));
//                                showProgressBar(false);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                showProgressBar(false);
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                showProgressBar(false);
//                            }
//                        }
//
//                );






//
//        obterCompletude(idUtilizador, data);
//
//        showProgressBar(true);
//
//        agendaRepositorio.obterMarcacoes(idUtilizador, data).toObservable()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//
//                        new Observer<List<Marcacao>>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//                                disposables.add(d);
//                            }
//
//                            @Override
//                            public void onNext(List<Marcacao> registos) {
//
//                                marcacoes.setValue(registos);
//                                showProgressBar(false);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                showProgressBar(false);
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                showProgressBar(false);
//                            }
//                        }
//                );
    }



    private void obterCompletude(String idUtilizador, long data){

        showProgressBar(true);

        agendaRepositorio.obterCompletude(idUtilizador, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(Integer registos) {

                                completude.setValue(registos);
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
     * Metodo que permite obter as datas nas quais existe trabalho
     * @param idUtilizador o identificador do utilizador
     */
    public void obterDatas(String idUtilizador){

        DataAgendamento dataHoje = new DataAgendamento(DatasUtil.converterString(DatasUtil.obterDataAtual(DatasUtil.FORMATO_YYYY_MM_DD), DatasUtil.FORMATO_YYYY_MM_DD));

        showProgressBar(true);

        agendaRepositorio.obterDatas(idUtilizador)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<List<DataAgendamento>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<DataAgendamento> registos) {

                                if(registos.contains(dataHoje) == false)
                                    registos.add(dataHoje);

                                datas.setValue(registos);
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
     * Metodo que permite terminar a sessao
     */
    public void terminarSessao() {

        agendaRepositorio.terminarSessao()
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
                                messagemLiveData.setValue(Recurso.successo());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }




}
