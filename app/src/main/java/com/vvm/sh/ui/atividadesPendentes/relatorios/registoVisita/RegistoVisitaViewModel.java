package com.vvm.sh.ui.atividadesPendentes.relatorios.registoVisita;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.repositorios.RegistoVisitaRepositorio;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
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

public class RegistoVisitaViewModel extends BaseViewModel {

    private final RegistoVisitaRepositorio registoVisitaRepositorio;


    public MutableLiveData<RegistoVisitaResultado> registoVisita;
    public MutableLiveData<List<Tipo>> trabalhos;


    @Inject
    public RegistoVisitaViewModel(RegistoVisitaRepositorio registoVisitaRepositorio){

        this.registoVisitaRepositorio = registoVisitaRepositorio;

        registoVisita = new MutableLiveData<>();
        trabalhos = new MutableLiveData<>();

    }


    //--------------------
    //GRAVAR
    //--------------------


    /**
     * Metodo que permite gravar o trabalho realizado
     * @param registo os dados a gravar
     */
    public void gravar(TrabalhoRealizadoResultado registo){

        registoVisitaRepositorio.inserir(registo)
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
                               //TODO: adicionar aos resultados
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );
    }

    /**
     * Metodo que permite gravar um registo
     * @param registo os dados do registo
     */
    public void gravar(RegistoVisitaResultado registo){

        if(registoVisita.getValue() == null){

            registoVisitaRepositorio.inserir(registo)
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
                                    //TODO: adicionar aos resultados
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }
                    );
        }
        else{
            registoVisitaRepositorio.atualizar(registo)
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
                                    //TODO: adicionar aos resultados
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }

                    );
        }


    }


    //--------------------
    //OBTER
    //--------------------

    /**
     * Metodo que permite obter o registo
     * @param idTarefa o identificador da tarefa
     */
    public void obterRegisto(int idTarefa){

        showProgressBar(true);

        registoVisitaRepositorio.obterRegisto(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<RegistoVisitaResultado>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(RegistoVisitaResultado registoVisitaResultado) {
                                registoVisita.setValue(registoVisitaResultado);
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
     * Metodo que permite obter os trabalhos realizados
     * @param idTarefa o identificador da tarefa
     */
    public void obterTrabalhos(int idTarefa) {

        showProgressBar(true);

        registoVisitaRepositorio.obterTrabalhosRealizados(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Tipo> tipos) {
                                trabalhos.setValue(tipos);
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




    //---------------------
    //REMOVER
    //---------------------





    /**
     * Metodo que permite remover um trabalho realizado
     * @param idTarefa o identificador da tarefa
     * @param id o identificador do trabalho
     */
    public void remover(int idTarefa, int id) {

        registoVisitaRepositorio.remover(idTarefa, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Object o) {
                                //TODO: remover a atividade pendente
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }


}
