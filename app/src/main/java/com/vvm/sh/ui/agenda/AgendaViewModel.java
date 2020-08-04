package com.vvm.sh.ui.agenda;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.repositorios.AgendaRepositorio;
import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.ui.agenda.modelos.TarefaDia;
import com.vvm.sh.servicos.TrabalhoAsyncTask;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AgendaViewModel extends BaseViewModel {

    private final AgendaRepositorio agendaRepositorio;

    public MutableLiveData<List<Marcacao>> marcacoes;




    @Inject
    public AgendaViewModel(AgendaRepositorio agendaRepositorio){

        this.agendaRepositorio = agendaRepositorio;
        marcacoes = new MutableLiveData<>();
    }


    //---------------------
    //OBTER
    //---------------------




    public void obterMarcacoes(String idUtilizador, String data){

        //TODO: terminar metodo agendaRepositorio.obterTarefas. Parametros ainda não estão a ser usados

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
