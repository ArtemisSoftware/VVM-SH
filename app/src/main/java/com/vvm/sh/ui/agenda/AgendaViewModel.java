package com.vvm.sh.ui.agenda;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.AgendaRepositorio;
import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class AgendaViewModel extends BaseViewModel {

    private final AgendaRepositorio agendaRepositorio;

    public MutableLiveData<Agenda> agenda;

    public MutableLiveData<List<Marcacao>> marcacoes;
    public MutableLiveData<Integer> completude;
    public MutableLiveData<Recurso> datas;
    public MutableLiveData<Recurso> completude_;

    @Inject
    public AgendaViewModel(AgendaRepositorio agendaRepositorio){

        this.agendaRepositorio = agendaRepositorio;
        agenda = new MutableLiveData<>();
        marcacoes = new MutableLiveData<>();
        completude = new MutableLiveData<>();
        completude_ = new MutableLiveData<>();
        datas = new MutableLiveData<>();
    }


    public MutableLiveData<Recurso> observarDatas(){
        return datas;
    }


    public MutableLiveData<Recurso> observarCompletude(){
        return completude_;
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

        agenda.setValue(new Agenda());

        Observable<Agenda> observables = Observable.zip(
                agendaRepositorio.obterMarcacoes(idUtilizador, data).toObservable(),
                agendaRepositorio.obterCompletude(idUtilizador, data).toObservable(),

                new BiFunction<List<Marcacao>, Integer, Agenda>() {
                    @Override
                    public Agenda apply(List<Marcacao> marcacaos, Integer completude) throws Exception {
                        return new Agenda(marcacaos, completude);
                    }
                });


        observables
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Agenda>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(Agenda resultado) {
                                agenda.setValue(resultado);
                                completude_.setValue(Recurso.successo(resultado.completude));
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


    /**
     * Metodo que permite obter as datas nas quais existe trabalho
     * @param idUtilizador o identificador do utilizador
     */
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


                                Date dataHoje = DatasUtil.converterString(DatasUtil.obterDataAtual(DatasUtil.FORMATO_YYYY_MM_DD), DatasUtil.FORMATO_YYYY_MM_DD);

                                if(registos.contains(dataHoje) == false)
                                    registos.add(dataHoje);

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



    public class Agenda{

        public List<Marcacao> marcacaos;
        public int completude;

        public Agenda() {
            this.marcacaos = new ArrayList<>();
            this.completude = Identificadores.Sincronizacao.SEM_SINCRONIZACAO;
        }

        public Agenda(List<Marcacao> marcacaos, int completude) {
            this.marcacaos = marcacaos;
            this.completude = completude;
        }

        /**
         * Metodo que permite obter a completude da agenda
         * @return
         */
        public boolean obterCompletude(){
            if(completude == Identificadores.Sincronizacao.TRANCADO){
                return false;
            }
            else{
                return true;
            }
        }
    }


}
