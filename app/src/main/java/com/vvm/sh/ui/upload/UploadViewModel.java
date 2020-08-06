package com.vvm.sh.ui.upload;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.servicos.DadosUploadAsyncTask;
import com.vvm.sh.ui.upload.modelos.Upload;
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


    @Inject
    public UploadViewModel(UploadRepositorio uploadRepositorio){

        this.uploadRepositorio = uploadRepositorio;
        this.uploads = new MutableLiveData<>();
    }


    public void obterUpload(String idUtilizador, Handler handler){

        //TODO: os dados necessitam do idutilizador + data

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

                                DadosUploadAsyncTask servico = new DadosUploadAsyncTask(vvmshBaseDados, handler, uploadRepositorio, idUtilizador);
                                servico.execute(resultado);
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


}
