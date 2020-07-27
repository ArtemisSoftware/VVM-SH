package com.vvm.sh.ui.atividadesPendentes;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.AtividadePendenteRepositorio;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteResultado;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AtividadesPendentesViewModel extends BaseViewModel {

    private final AtividadePendenteRepositorio atividadePendenteRepositorio;

    public MutableLiveData<List<AtividadePendente>> atividades;

    @Inject
    public AtividadesPendentesViewModel(AtividadePendenteRepositorio atividadePendenteRepositorio){

        this.atividadePendenteRepositorio = atividadePendenteRepositorio;
        atividades = new MutableLiveData<>();
    }



    /**
     * Metodo que permite obter as atividades pendentes
     * @param idTarefa o identificador da tarefa
     */
    public void obterAtividades(int idTarefa){

        showProgressBar(true);

        atividadePendenteRepositorio.obterAtividades(idTarefa).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<AtividadePendente>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<AtividadePendente> resultado) {

                                atividades.setValue(resultado);
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





    public void obterAtividadesNaoExecutada(int idAtividade) {
    }

    public void obterAtividadesExecutada(int anInt) {
    }

    public void gravarAtividade(AtividadePendenteResultado atividade) {
    }
}
