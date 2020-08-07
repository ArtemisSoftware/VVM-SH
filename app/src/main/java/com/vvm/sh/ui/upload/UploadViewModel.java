package com.vvm.sh.ui.upload;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.servicos.DadosUploadAsyncTask;
import com.vvm.sh.ui.upload.modelos.Pendencia;
import com.vvm.sh.ui.upload.modelos.Upload;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UploadViewModel extends BaseViewModel {

    private final UploadRepositorio uploadRepositorio;


    public MutableLiveData<List<Upload>> uploads;
    public MutableLiveData<Recurso> pendencias;

    @Inject
    public UploadViewModel(UploadRepositorio uploadRepositorio){

        this.uploadRepositorio = uploadRepositorio;
        this.uploads = new MutableLiveData<>();
        this.pendencias = new MutableLiveData<>();
    }

    public MutableLiveData<Recurso> observarPendencias(){
        return pendencias;
    }






    public void obterUpload(String idUtilizador, Handler handler){

        //TODO: os dados necessitam do idutilizador + data

        showProgressBar(true);

        uploadRepositorio.obterUploads(idUtilizador).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Upload>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Upload> resultado) {

                                uploads.setValue(resultado);
                                showProgressBar(false);

                                DadosUploadAsyncTask servico = new DadosUploadAsyncTask(vvmshBaseDados, handler, uploadRepositorio, idUtilizador);
                                servico.execute(resultado);
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


    public void sincronizar(){

        uploadRepositorio.sincronizar(uploads.getValue()).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integers) {

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






    public void obterPendencias(String idUtilizador, Handler handler){

        //TODO: os dados necessitam do idutilizador + data

        showProgressBar(true);

        uploadRepositorio.obterPendencias(idUtilizador).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Pendencia>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Pendencia> registos) {
                                pendencias.setValue(Recurso.successo(registos));
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
