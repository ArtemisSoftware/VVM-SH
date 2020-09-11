package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.AvaliacaoAmbientalRepositorio;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AvaliacaoAmbientalViewModel extends BaseViewModel {

    private final AvaliacaoAmbientalRepositorio avaliacaoAmbientalRepositorio;


    public MutableLiveData<RelatorioAmbientalResultado> geral;

    public MutableLiveData<List<Tipo>> generos;

    @Inject
    public AvaliacaoAmbientalViewModel(AvaliacaoAmbientalRepositorio avaliacaoAmbientalRepositorio){

        this.avaliacaoAmbientalRepositorio = avaliacaoAmbientalRepositorio;
        geral = new MutableLiveData<>();
        generos = new MutableLiveData<>();

    }

    //--------------------
    //OBTER
    //--------------------


    /**
     * Metodo que permite obter os dados gerais
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     */
    public void obterGeral(int idAtividade, int tipo) {

        showProgressBar(true);


        avaliacaoAmbientalRepositorio.obterGeral(idAtividade, tipo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<RelatorioAmbientalResultado>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(RelatorioAmbientalResultado resultado) {
                                geral.setValue(resultado);
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


}
