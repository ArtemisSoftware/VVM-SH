package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.repositorios.LevantamentoRepositorio;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LevantamentosViewModel extends BaseViewModel {

    private final LevantamentoRepositorio levantamentoRepositorio;


    public MutableLiveData<List<LevantamentoRiscoResultado>> levantamentos;


    @Inject
    public LevantamentosViewModel(LevantamentoRepositorio levantamentoRepositorio){

        this.levantamentoRepositorio = levantamentoRepositorio;
        levantamentos = new MutableLiveData<>();

    }


    //--------------------
    //OBTER
    //--------------------



    /**
     * Metodo que permite obter os levantamentos
     * @param idAtividade o identificador da atividade
     */
    public void obterLevantamentos(int idAtividade) {

        showProgressBar(true);


        levantamentoRepositorio.obterLevantamentos(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<LevantamentoRiscoResultado>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<LevantamentoRiscoResultado> registos) {
                                levantamentos.setValue(registos);
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
