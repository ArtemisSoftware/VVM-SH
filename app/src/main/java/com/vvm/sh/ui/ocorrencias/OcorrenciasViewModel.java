package com.vvm.sh.ui.ocorrencias;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.OcorrenciaRepositorio;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OcorrenciasViewModel extends BaseViewModel {

    private final OcorrenciaRepositorio ocorrenciaRepositorio;

    public MutableLiveData<List<Ocorrencia>> ocorrencias;

    @Inject
    public OcorrenciasViewModel(OcorrenciaRepositorio ocorrenciaRepositorio){

        this.ocorrenciaRepositorio = ocorrenciaRepositorio;
        ocorrencias = new MutableLiveData<>();
    }



    /**
     * Metodo que permite obter as ocorrencias
     * @param idTarefa o identificador da tarefa
     */
    public void obterAtividades(int idTarefa){

        showProgressBar(true);

        ocorrenciaRepositorio.obterOcorrencias(idTarefa).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<Ocorrencia>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Ocorrencia> resultado) {

                                ocorrencias.setValue(resultado);
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
}
