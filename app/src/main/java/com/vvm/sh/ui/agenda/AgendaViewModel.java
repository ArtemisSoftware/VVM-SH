package com.vvm.sh.ui.agenda;

import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.repositorios.AgendaRepositorio;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AgendaViewModel extends BaseViewModel {

    private final AgendaRepositorio agendaRepositorio;

    @Inject
    public AgendaViewModel(AgendaRepositorio agendaRepositorio){

        this.agendaRepositorio = agendaRepositorio;

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
