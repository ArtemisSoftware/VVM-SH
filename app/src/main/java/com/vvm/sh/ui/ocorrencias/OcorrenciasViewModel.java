package com.vvm.sh.ui.ocorrencias;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.OcorrenciaRepositorio;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
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
    public MutableLiveData<List<Tipo>> ocorrenciasGeral;
    public MutableLiveData<List<Tipo>> ocorrenciasRegistos;

    @Inject
    public OcorrenciasViewModel(OcorrenciaRepositorio ocorrenciaRepositorio){

        this.ocorrenciaRepositorio = ocorrenciaRepositorio;
        ocorrencias = new MutableLiveData<>();
        ocorrenciasGeral = new MutableLiveData<>();
        ocorrenciasRegistos = new MutableLiveData<>();
    }



    /**
     * Metodo que permite obter as ocorrencias associadas a uma tarefa
     * @param idTarefa o identificador da tarefa
     */
    public void obterOcorrencias(int idTarefa){

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



    public void obterOcorrencias(){

        showProgressBar(true);

        ocorrenciaRepositorio.obterOcorrencias().toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Tipo> resultado) {

                                ocorrenciasGeral.setValue(resultado);
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


    public void obterRegistosOcorrencias(int id) {

        showProgressBar(true);

        ocorrenciaRepositorio.obterRegistoOcorrencias(id).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Tipo> resultado) {

                                ocorrenciasRegistos.setValue(resultado);
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
