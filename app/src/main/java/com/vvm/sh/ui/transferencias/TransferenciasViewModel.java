package com.vvm.sh.ui.transferencias;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.servicos.DadosUploadAsyncTask;
import com.vvm.sh.servicos.TrabalhoAsyncTask;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TransferenciasViewModel extends BaseViewModel {

    private final TransferenciasRepositorio transferenciasRepositorio;


    public MutableLiveData<Recurso> uploads;
    public MutableLiveData<Recurso> pendencias;

    @Inject
    public TransferenciasViewModel(TransferenciasRepositorio transferenciasRepositorio){

        this.transferenciasRepositorio = transferenciasRepositorio;
        this.uploads = new MutableLiveData<>();
        this.pendencias = new MutableLiveData<>();
    }

    public MutableLiveData<Recurso> observarPendencias(){
        return pendencias;
    }

    public MutableLiveData<Recurso> observarUpload(){
        return uploads;
    }




    //------------------------
    //UPLOAD
    //------------------------



    public void obterUpload(String idUtilizador, Handler handler){
        Observable<List<Upload>> observable = transferenciasRepositorio.obterUploads(idUtilizador).toObservable();
        obterUpload(observable, idUtilizador, handler);
    }

    public void obterUpload(String idUtilizador, long data, Handler handler){
        Observable<List<Upload>> observable = transferenciasRepositorio.obterUploads(idUtilizador, data).toObservable();
        obterUpload(observable, idUtilizador, handler);
    }




    private void obterUpload(Observable<List<Upload>> observable, String idUtilizador, Handler handler){

        showProgressBar(true);

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Upload>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Upload> resultado) {

                                uploads.setValue(Recurso.successo(resultado));
                                showProgressBar(false);

                                DadosUploadAsyncTask servico = new DadosUploadAsyncTask(vvmshBaseDados, handler, transferenciasRepositorio, idUtilizador);
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

        transferenciasRepositorio.sincronizar((List<Upload>) uploads.getValue().dados).toObservable()
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


    //-----------------------
    //PENDENCIAS
    //-----------------------

    /**
     * Metodo que permite obter as pendencias
     * @param idUtilizador o identificador do utilizador
     */
    public void obterPendencias(String idUtilizador) {

        Observable<List<Pendencia>> observable = transferenciasRepositorio.obterPendencias(idUtilizador).toObservable();
        obterPendencias(observable);
    }

    /**
     * Metodo que permite obter as pendencias
     * @param idUtilizador o identificador do utilizador
     * @param data a data das pendencias
     */
    public void obterPendencias(String idUtilizador, long data) {

        Observable<List<Pendencia>> observable = transferenciasRepositorio.obterPendencias(idUtilizador, data).toObservable();
        obterPendencias(observable);
    }


    /**
     * Metodo que permite obter as pendencias
     * @param observable
     */
    private void obterPendencias(Observable<List<Pendencia>> observable){


        showProgressBar(true);

        observable
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




    //---------------------
    //DOWNLOAD
    //---------------------



    /**
     * Metodo que permite obter o trabalho do dia
     * @param idUtilizador o identificador do utilizador
     */
    public void obterTrabalho(String idUtilizador){

        showProgressBar(true);

        transferenciasRepositorio.obterTrabalho(idUtilizador).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<SessaoResposta[]>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(SessaoResposta[] sessao) {


                                TrabalhoAsyncTask servico = new TrabalhoAsyncTask(vvmshBaseDados, transferenciasRepositorio, idUtilizador);
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





}
