package com.vvm.sh.ui.atividadesPendentes.relatorios;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.FormacaoRepositorio;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FormacaoViewModel extends BaseViewModel {

    private final FormacaoRepositorio formacaoRepositorio;

    public MutableLiveData<List<Formando>> formandos;
    public MutableLiveData<Formando> formando;
    public MutableLiveData<AcaoFormacao> acaoFormacao;

    @Inject
    public FormacaoViewModel(FormacaoRepositorio formacaoRepositorio){

        this.formacaoRepositorio = formacaoRepositorio;
        formandos = new MutableLiveData<>();
        formando = new MutableLiveData<>();
        acaoFormacao = new MutableLiveData<>();
    }



    /**
     * Metodo que permite obter os formandos
     * @param idAtividade o identificador da atividade
     */
    public void obterFormandos(int idAtividade){

        showProgressBar(true);
/*
        formacaoRepositorio.obterFormandos(idAtividade).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<Formando>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Formando> resultado) {

                                formandos.setValue(resultado);
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
*/
    }


    public void gravar(Formando formando) {
    }

    public void obterFormando(int idFormando) {
    }

    public void obterAcaoFormacao(int anInt) {
    }

    public void gravar(AcaoFormacao registo) {

        if(acaoFormacao.getValue() == null){
            //inserir
        }
        else{
            //editar
        }
    }
}
