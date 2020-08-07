package com.vvm.sh.ui.agenda;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.repositorios.AgendaRepositorio;
import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.servicos.TrabalhoAsyncTask;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AgendaViewModel extends BaseViewModel {

    private final AgendaRepositorio agendaRepositorio;

    public MutableLiveData<List<Marcacao>> marcacoes;
    public MutableLiveData<Integer> completude;
    public MutableLiveData<Recurso> datas;


    @Inject
    public AgendaViewModel(AgendaRepositorio agendaRepositorio){

        this.agendaRepositorio = agendaRepositorio;
        marcacoes = new MutableLiveData<>();
        completude = new MutableLiveData<>();
        datas = new MutableLiveData<>();
    }


    public MutableLiveData<Recurso> observarDatas(){
        return datas;
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

        obterCompletude(idUtilizador, data);

        showProgressBar(true);

        agendaRepositorio.obterMarcacoes(idUtilizador, data).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Marcacao>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Marcacao> registos) {

                                marcacoes.setValue(registos);
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



    private void obterCompletude(String idUtilizador, long data){

        showProgressBar(true);

        agendaRepositorio.obterCompletude(idUtilizador, data).toObservable()
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



    public void obterDatas(String idUtilizador){

        showProgressBar(true);

        agendaRepositorio.obterDatas(idUtilizador).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Date>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Date> registos) {

                                datas.setValue(Recurso.successo(registos));
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
     * Metodo que permite obter o trabalho do dia
     * @param idUtilizador o identificador do utilizador
     */
    public void obterTrabalho(String idUtilizador){

        showProgressBar(true);

        agendaRepositorio.obterTrabalho(idUtilizador).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<SessaoResposta[]>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(SessaoResposta[] sessao) {

                                TrabalhoAsyncTask servico = new TrabalhoAsyncTask(vvmshBaseDados, agendaRepositorio, idUtilizador);
                                servico.execute(sessao[0]);

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



    //----------------------
    //
    //----------------------




}
