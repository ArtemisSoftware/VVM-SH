package com.vvm.sh.ui.transferencias;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vvm.sh.api.modelos.Codigo;
import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.servicos.DadosUploadAsyncTask;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TransferenciasViewModel extends BaseViewModel {

    private final TransferenciasRepositorio transferenciasRepositorio;
    private final TiposRepositorio tiposRepositorio;

    public MutableLiveData<Recurso> uploads;
    public MutableLiveData<Recurso> pendencias;

    @Inject
    public TransferenciasViewModel(TransferenciasRepositorio transferenciasRepositorio, TiposRepositorio tiposRepositorio){

        this.transferenciasRepositorio = transferenciasRepositorio;
        this.tiposRepositorio = tiposRepositorio;
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


                                //TrabalhoAsyncTask servico = new TrabalhoAsyncTask(vvmshBaseDados, transferenciasRepositorio, idUtilizador);
                                //servico.execute(sessao[0]);

                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                                Gson gson = new GsonBuilder().create();
                                Codigo codigo = gson.fromJson(e.getMessage(), Codigo.class);

                                messagemLiveData.setValue(Recurso.erro(codigo, "Download"));
                            }

                            @Override
                            public void onComplete() {

                            }
                        }

                );

    }



    //------------------------
    //TIPOS
    //------------------------


    public void obterTipos(){


        tiposRepositorio.obterAtualizacoes().toObservable()

                .map(new Function<List<Atualizacao>, List<Atualizacao>>() {
                    @Override
                    public List<Atualizacao> apply(List<Atualizacao> atualizacoes) throws Exception {

                        List<String> tipos = new LinkedList(Arrays.asList(TiposConstantes.MetodosTipos.TIPOS));

                        for (Atualizacao atualizacao: atualizacoes) {

                            if(tipos.contains(atualizacao.descricao) == true){
                                tipos.remove(atualizacao.descricao);
                            }
                        }

                        for (String tipo: tipos) {
                            atualizacoes.add(new Atualizacao(tipo, ""));
                        }


                        return atualizacoes;
                    }
                })
                .flatMap(new Function<List<Atualizacao>, Observable<Object>>() {
                    @Override
                    public Observable<Object> apply(List<Atualizacao> atualizacoes) throws Exception {

                        List<Observable<? extends TipoResposta>> observables = new ArrayList<>();

                        for(Atualizacao atualizacao : atualizacoes){
                            observables.add(tiposRepositorio.obterTipo(atualizacao.descricao, atualizacao.seloTemporal).toObservable());
                        }

                        return Observable.zip(observables, new Function<Object[], Object>() {
                            @Override
                            public Object apply(Object[] objects) throws Exception {
                                return objects;
                            }
                        });

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Object o) {

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
