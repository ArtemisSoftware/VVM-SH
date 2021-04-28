package com.vvm.sh.ui.transferencias;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.repositorios.DownloadTrabalhoRepositorio;
import com.vvm.sh.repositorios.RedeRepositorio;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.servicos.DadosUploadAsyncTask;
import com.vvm.sh.servicos.tipos.AtualizarTipoAsyncTask;
import com.vvm.sh.servicos.tipos.atualizacao.AtualizarTipoAsyncTask__v2;
import com.vvm.sh.servicos.trabalho.AtualizarTrabalhoAsyncTask;
import com.vvm.sh.servicos.trabalho.RecarregarTarefaAsyncTask;
import com.vvm.sh.servicos.trabalho.RecarregarTarefaAsyncTask__v2;
import com.vvm.sh.servicos.trabalho.RecarregarTrabalhoAsyncTask;
import com.vvm.sh.servicos.trabalho.RecarregarTrabalhoAsyncTask__v2;
import com.vvm.sh.servicos.upload.DadosUploadAsyncTask__v2;
import com.vvm.sh.servicos.upload.DadosUploadSAAsyncTask;
import com.vvm.sh.servicos.upload.DadosUploadSHAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.ui.transferencias.modelos.AtualizacaoTipos;
import com.vvm.sh.ui.transferencias.modelos.DadosPendencia;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Sessao;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TransferenciasViewModel extends BaseViewModel {

    private final TransferenciasRepositorio transferenciasRepositorio;
    private final UploadRepositorio uploadRepositorio;
    private final TiposRepositorio tiposRepositorio;
    private final CarregamentoTiposRepositorio carregamentoTiposRepositorio;
    private final RedeRepositorio redeRepositorio;
    private final DownloadTrabalhoRepositorio downloadTrabalhoRepositorio;

    public MutableLiveData<List<Pendencia>> pendencias;
    public MutableLiveData<List<Upload>> uploads;


    public MutableLiveData<Boolean> aguardar;
    public MutableLiveData<Boolean> contagemUpload;
    public MutableLiveData<Boolean> sincronizado;


    @Inject
    public TransferenciasViewModel(TransferenciasRepositorio transferenciasRepositorio,
                                   TiposRepositorio tiposRepositorio, UploadRepositorio uploadRepositorio,
                                   CarregamentoTiposRepositorio carregamentoTiposRepositorio,
                                   RedeRepositorio redeRepositorio, DownloadTrabalhoRepositorio downloadTrabalhoRepositorio){

        this.transferenciasRepositorio = transferenciasRepositorio;
        this.uploadRepositorio = uploadRepositorio;
        this.tiposRepositorio = tiposRepositorio;
        this.carregamentoTiposRepositorio = carregamentoTiposRepositorio;
        this.redeRepositorio = redeRepositorio;
        this.downloadTrabalhoRepositorio = downloadTrabalhoRepositorio;

        this.uploads = new MutableLiveData<>();
        this.pendencias = new MutableLiveData<>();
        this.aguardar = new MutableLiveData<>();
        this.contagemUpload = new MutableLiveData<>();
        this.sincronizado = new MutableLiveData<>();
        aguardar.postValue(true);
        contagemUpload.postValue(false);
        sincronizado.postValue(false);
    }

    public MutableLiveData<List<Pendencia>> observarPendencias(){
        return pendencias;
    }

    public MutableLiveData<List<Upload>> observarUpload(){
        return uploads;
    }

    public MutableLiveData<Boolean> observarContagemUpload(){
        return contagemUpload;
    }


    public MutableLiveData<Boolean> observarSincronizado(){
        return sincronizado;
    }

    /**
     * Metodo que permite atualizar os tipos
     */
    public void atualizarDados(OnTransferenciaListener listener) {

        tiposRepositorio.obterAtualizacoes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<AtualizacaoTipos>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(AtualizacaoTipos atualizacoes) {
                                atualizarTipos(listener, atualizacoes);
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



    /**
     * Metodo que permite eliminar todas as atualizacoes e recarregar todos os dados
     * @param listener
     */
    public void recarregarDados(OnTransferenciaListener listener) {

        tiposRepositorio.eliminarAtualizacoes(Identificadores.Atualizacoes.TIPO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<AtualizacaoTipos>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(AtualizacaoTipos atualizacaoTipos) {
                                atualizarTipos(listener, atualizacaoTipos);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }
                );

    }



    /**
     * Metoodo que permite carregar os tipos
     * @param atualizacoes as atualizações dos tipos
     */
    private void atualizarTipos(OnTransferenciaListener listener, AtualizacaoTipos atualizacoes){

        redeRepositorio.obterDados(atualizacoes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Object>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Object> iTipoListagems) {

                                AtualizarTipoAsyncTask__v2 servico = new AtualizarTipoAsyncTask__v2(listener, vvmshBaseDados, carregamentoTiposRepositorio);
                                servico.execute(iTipoListagems);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                formatarErro(e);
                                listener.erroTransferencia();
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
    public void obterPendencias(OnTransferenciaListener listener, String idUtilizador, boolean upload) {
        obterPendencias(listener, null, idUtilizador, transferenciasRepositorio.obterPendencias(idUtilizador), 0, upload, false);
    }

    public void obterPendencias(OnTransferenciaListener listener, String idUtilizador, long data, boolean upload) {
        obterPendencias(listener, null, idUtilizador, transferenciasRepositorio.obterPendencias(idUtilizador, data), data, upload, false);
    }

    public void obterPendencias_(OnTransferenciaListener.OnUploadListener listener, String idUtilizador, boolean upload) {
        obterPendencias(null, listener, idUtilizador, transferenciasRepositorio.obterPendencias(idUtilizador), 0, upload, false);
    }

    public void obterPendencias_(OnTransferenciaListener.OnUploadListener listener, String idUtilizador, long data, boolean upload) {
        obterPendencias(null, listener, idUtilizador, transferenciasRepositorio.obterPendencias(idUtilizador, data), data, upload, false);
    }


    public void obterPendencias_(OnTransferenciaListener.OnUploadListener listener, String idUtilizador, long data, boolean upload, boolean reupload) {
        obterPendencias(null, listener, idUtilizador, transferenciasRepositorio.obterPendencias(idUtilizador, data), data, upload, reupload);
    }



    /**
     * Metodo que permite obter as pendencias
     * @param maybe
     */
    private void obterPendencias(OnTransferenciaListener listener, OnTransferenciaListener.OnUploadListener listenerUpload, String idUtilizador, Maybe<DadosPendencia> maybe, long data, boolean upload, boolean reupload){

        maybe
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<DadosPendencia>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(DadosPendencia dadosPendencia) {

                                pendencias.setValue(dadosPendencia.pendencias);

                                if (dadosPendencia.pendencias.size() == 0) {

                                    if(dadosPendencia.dadosUpload == false){
                                        aguardar.setValue(false);
                                        if (reupload == true) {
                                            obterUpload(listenerUpload, idUtilizador, data);
                                        }
                                        else {
                                            sincronizado.setValue(true);
                                        }
                                    }
                                    else {
                                        if (upload == false) {
                                            atualizarDados(listener);
                                        }
                                        else {
                                            if (reupload == true) {
                                                obterUpload(listenerUpload, idUtilizador, data);
                                            } else {
                                                obterUpload(listenerUpload, idUtilizador);
                                            }

                                        }
                                        aguardar.setValue(false);
                                    }
                                }
                                else{
                                    aguardar.setValue(false);
                                }
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



    public void obterUploads(String idUtilizador){

        transferenciasRepositorio.obterUploads__(idUtilizador)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Upload>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(@NonNull List<Upload> resultado) {
                                uploads.setValue(resultado);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }


    public void obterUploads(String idUtilizador, long data){

        transferenciasRepositorio.obterUploads__(idUtilizador, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Upload>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(@NonNull List<Upload> resultado) {
                                uploads.setValue(resultado);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }







    /**
     * Metodo que permite obter os dados para upload
     */
    public void obterUpload(OnTransferenciaListener.OnUploadListener listener, String idUtilizador){
        Observable<List<Upload>> observable = transferenciasRepositorio.obterUploads(idUtilizador).toObservable();
        obterUpload(listener, observable, idUtilizador, false);
    }

    /**
     * Metodo que permite obter os dados para upload de uma data
     * @param idUtilizador o identificador do utilizador
     * @param data a data dos dados
     */
    public void obterUpload(OnTransferenciaListener.OnUploadListener listener, String idUtilizador, long data){
        Observable<List<Upload>> observable = transferenciasRepositorio.obterUploads(idUtilizador, data).toObservable();
        obterUpload(listener, observable, idUtilizador, true);
    }



    //---------------------------
    //Download
    //---------------------------



    /**
     * Metodo que permite obter o trabalho do dia
     * @param idUtilizador o identificador do utilizador
     */
    public void obterTrabalho(Context contexto, OnTransferenciaListener listener, String idUtilizador){

        redeRepositorio.obterTrabalho(idUtilizador)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Sessao>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Sessao sessao) {

                                aguardar.setValue(false);

                                AtualizarTrabalhoAsyncTask servico = new AtualizarTrabalhoAsyncTask(listener, vvmshBaseDados, downloadTrabalhoRepositorio, idUtilizador);
                                servico.execute(sessao);

                                PreferenciasUtil.fixarContagemMaquina(contexto, sessao.iContagemTipoMaquina);
                            }

                            @Override
                            public void onError(Throwable e) {
                                aguardar.setValue(false);
                                formatarErro(e);
                            }
                        }
                );
    }




    /**
     * Metodo que permite obter o trabalho de um dia especifico
     * @param idUtilizador o identificador do utilizador
     * @param data a data do trabalho
     */
    public void recarregarTrabalho(Context contexto, OnTransferenciaListener listener, String idUtilizador, String data, int api){

        redeRepositorio.obterTrabalho(idUtilizador, data, api)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Sessao>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Sessao sessao) {

                                aguardar.setValue(false);

                                RecarregarTrabalhoAsyncTask__v2 servico = new RecarregarTrabalhoAsyncTask__v2(listener, vvmshBaseDados, downloadTrabalhoRepositorio, idUtilizador, DatasUtil.converterDataLong(data, DatasUtil.FORMATO_YYYY_MM_DD), api);
                                servico.execute(sessao);

                                PreferenciasUtil.fixarContagemMaquina(contexto, sessao.iContagemTipoMaquina);
                            }

                            @Override
                            public void onError(Throwable e) {
                                aguardar.setValue(false);
                                formatarErro(e);
                            }
                        }
                );
    }



    /**
     * Metodo que permite recarregar uma tarefa
     * @param tarefa os dados da tarefa a recarregar
     */
    public void recarregarTarefa(Context contexto, OnTransferenciaListener listener, Tarefa tarefa){

        redeRepositorio.obterTrabalho(tarefa.idUtilizador)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Sessao>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Sessao sessao) {

                                aguardar.setValue(false);

                                RecarregarTarefaAsyncTask__v2 servico = new RecarregarTarefaAsyncTask__v2(listener, vvmshBaseDados, downloadTrabalhoRepositorio, tarefa);
                                servico.execute(sessao);

                                PreferenciasUtil.fixarContagemMaquina(contexto, sessao.iContagemTipoMaquina);

                            }

                            @Override
                            public void onError(Throwable e) {
                                aguardar.setValue(false);
                                formatarErro(e);
                            }
                        }
                );
    }







    //------------------------
    //UPLOAD
    //------------------------





    /**
     * Metodo que permite obter os dados para upload
     * @param observable
     * @param idUtilizador o identificador do utilizador
     * @param reupload true caso seja um reupload ou false caso contrario
     */
    private void obterUpload(OnTransferenciaListener.OnUploadListener listener, Observable<List<Upload>> observable, String idUtilizador, boolean reupload){

        observable
                .map(new Function<List<Upload>, List<Upload>>() {
                    @Override
                    public List<Upload> apply(List<Upload> uploads) throws Exception {

                        List<Upload> registos = new ArrayList<>();

                        for (Upload item : uploads) {
                            item.filtrarResultados(reupload);
                            registos.add(item);
                        }

                        return registos;
                    }
                })
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

                                //--uploads.setValue(resultado);

                                if(resultado.size() != 0) {
                                    DadosUploadAsyncTask__v2 servico = new DadosUploadAsyncTask__v2(listener, vvmshBaseDados, uploadRepositorio, idUtilizador);
                                    servico.execute(resultado);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                formatarErro(e);
                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }
                );
    }



    public void upload(DadosUpload dadosUploadSA, DadosUpload dadosUploadSH) {

        uploads.setValue(new ArrayList<>());

        if(dadosUploadSA.dados.size() > 0) {
            redeRepositorio.uploadSA(dadosUploadSA)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Codigo>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Codigo codigo) {
                                    contagemUpload.setValue(true);

                                    List<Upload> registos = uploads.getValue();
                                    registos.addAll(dadosUploadSA.obterUploadsSincronizados());
                                    uploads.setValue(registos);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    formatarErro(e);
                                }
                            }
                    );
        }

        if(dadosUploadSH.dados.size() > 0) {
            redeRepositorio.uploadSH(dadosUploadSH)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Codigo>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Codigo codigo) {
                                    contagemUpload.setValue(true);

                                    List<Upload> registos = uploads.getValue();
                                    registos.addAll(dadosUploadSH.obterUploadsSincronizados());
                                    uploads.postValue(registos);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    formatarErro(e);
                                }
                            }
                    );
        }

    }































































    //-----------------------
    //PENDENCIAS
    //-----------------------



    /**
     * Metodo que permite obter as pendencias
     * @param idUtilizador o identificador do utilizador
     */
    public void obterPendencias(Activity activity, Handler handlerNotificacoesUI, String idUtilizador, boolean upload) {
        obterPendencias(activity, handlerNotificacoesUI, transferenciasRepositorio.obterPendencias(idUtilizador), upload);
    }


    /**
     * Metodo que permite obter as pendencias
     * @param idUtilizador o identificador do utilizador
     * @param data a data das pendencias
     */
    public void obterPendencias(Activity activity, Handler handlerNotificacoesUI, String idUtilizador, long data, boolean upload) {
        obterPendencias(activity, handlerNotificacoesUI, transferenciasRepositorio.obterPendencias(idUtilizador, data), upload);
    }


    /**
     * Metodo que permite obter as pendencias
     * @param maybe
     */
    private void obterPendencias(Activity activity, Handler handlerNotificacoesUI, Maybe<DadosPendencia> maybe, boolean upload){

        showProgressBar(true);

        maybe
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<DadosPendencia>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(DadosPendencia dadosPendencia) {

                                showProgressBar(false);

                                pendencias.setValue(dadosPendencia.pendencias);

                                if(upload == false) {

                                    if (dadosPendencia.pendencias.size() > 0) {

                                    }
                                    else if (dadosPendencia.dadosUpload == true) {
                                        messagemLiveData.setValue(Recurso.erro(Sintaxe.Frases.EXISTEM_DADOS_UPLOAD));
                                    }
                                    else {
                                        atualizarTipos(activity, handlerNotificacoesUI, false);
                                    }
                                }
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
     * Metodo que permite recarregar uma tarefa
     * @param tarefa os dados da tarefa a recarregar
     */
    public void recarregarTarefa(Context contexto, Tarefa tarefa, Handler handler){

        showProgressBar(true);

        redeRepositorio.obterTrabalho(tarefa.idUtilizador)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Sessao>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Sessao sessao) {
                                RecarregarTarefaAsyncTask servico = new RecarregarTarefaAsyncTask(vvmshBaseDados, transferenciasRepositorio, handler, tarefa);
                                servico.execute(sessao);

                                PreferenciasUtil.fixarContagemMaquina(contexto, sessao.iContagemTipoMaquina);

                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                formatarErro(e);
                            }
                        }
                );
    }







    /**
     * Metodo que permite obter o trabalho de um dia especifico
     * @param idUtilizador o identificador do utilizador
     * @param data a data do trabalho
     */
    public void recarregarTrabalho(Context contexto, String idUtilizador, String data, Handler handler){

        showProgressBar(true);

        transferenciasRepositorio.obterTrabalho(idUtilizador, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Sessao>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Sessao sessao) {
                                RecarregarTrabalhoAsyncTask servico = new RecarregarTrabalhoAsyncTask(vvmshBaseDados, transferenciasRepositorio, handler, idUtilizador, DatasUtil.converterDataLong(data, DatasUtil.FORMATO_YYYY_MM_DD));
                                servico.execute(sessao);

                                PreferenciasUtil.fixarContagemMaquina(contexto, sessao.iContagemTipoMaquina);

                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                formatarErro(e);
                            }
                        }
                );
    }







    //------------------------
    //UPLOAD
    //------------------------


    /**
     * Metodo que permite obter os dados para upload
     * @param idUtilizador o identificador do utilizador
     * @param handler
     */
    public void obterUpload(String idUtilizador, Handler handler){
        Observable<List<Upload>> observable = transferenciasRepositorio.obterUploads(idUtilizador).toObservable();
        obterUpload(observable, idUtilizador, handler, false);
    }


    /**
     * Metodo que permite obter os dados para upload de uma data
     * @param idUtilizador o identificador do utilizador
     * @param data a data dos dados
     * @param handler
     */
    public void obterUpload(String idUtilizador, long data, Handler handler){
        Observable<List<Upload>> observable = transferenciasRepositorio.obterUploads(idUtilizador, data).toObservable();
        obterUpload(observable, idUtilizador, handler, true);
    }


    /**
     * Metodo que permite obter os dados para upload
     * @param observable
     * @param idUtilizador o identificador do utilizador
     * @param handler
     * @param reupload true caso seja um reupload ou false caso contrario
     */
    private void obterUpload(Observable<List<Upload>> observable, String idUtilizador, Handler handler, boolean reupload){

        showProgressBar(true);

        observable
                .map(new Function<List<Upload>, List<Upload>>() {
                    @Override
                    public List<Upload> apply(List<Upload> uploads) throws Exception {

                        List<Upload> registos = new ArrayList<>();

                        for (Upload item : uploads) {
                            item.filtrarResultados(reupload);
                            registos.add(item);
                        }

                        return registos;
                    }
                })
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

                                uploads.setValue(resultado);
                                showProgressBar(false);

                                if(resultado.size() != 0) {
                                    gerarDadosUpload(resultado, handler, idUtilizador);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                formatarErro(e);
                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }
                );
    }

    private void gerarDadosUpload(List<Upload> resultado, Handler handler, String idUtilizador) {

        DadosUploadAsyncTask servico;

        if(AppConfig.APP_MODO == AppConfig.APP_SA){
            servico = new DadosUploadSAAsyncTask(vvmshBaseDados, handler, uploadRepositorio, idUtilizador);
        }
        else{
            servico = new DadosUploadSHAsyncTask(vvmshBaseDados, handler, uploadRepositorio, idUtilizador);
        }

        servico.execute(resultado);
    }



    public void upload(DadosUpload dadosUpload) {

        showProgressBar(true);

        transferenciasRepositorio.upload(dadosUpload, uploads.getValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Codigo>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(Codigo codigo) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_ENVIADOS_SUCESSO));
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                formatarErro(e);
                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }

                );

//        if(AppConfig.sa == true){
//            uploadSA(dadosUpload);
//        }
//        else{
//
//
//            showProgressBar(true);
//
//            transferenciasRepositorio.submeterSH(dadosUpload)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                            new Observer<Codigo>() {
//                                @Override
//                                public void onSubscribe(Disposable d) {
//                                    disposables.add(d);
//                                }
//
//                                @Override
//                                public void onNext(Codigo codigo) {
//                                    sincronizar();
//                                    showProgressBar(false);
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    showProgressBar(false);
//                                    formatarErro(e);
//                                }
//
//                                @Override
//                                public void onComplete() {
//                                    showProgressBar(false);
//                                }
//                            }
//                    );
//        }

    }





    //------------------------
    //TIPOS
    //------------------------

    public void atualizarTipos(Activity activity, Handler handlerUI) {

        tiposRepositorio.eliminarAtualizacoes(Identificadores.Atualizacoes.TIPO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<AtualizacaoTipos>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(AtualizacaoTipos atualizacaoTipos) {
                                atualizarTipos(activity, atualizacaoTipos, handlerUI);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }
                );

    }

    /**
     * Metodo que permite atualizar os tipos
     * @param handlerUI um handler para as mensagens
     */
    public void atualizarTipos(Activity activity, Handler handlerUI, boolean primeiraUtilizacao) {

        showProgressBar(true);

        tiposRepositorio.obterAtualizacoes(primeiraUtilizacao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<AtualizacaoTipos>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(AtualizacaoTipos atualizacoes) {
                                atualizarTipos(activity, atualizacoes, handlerUI);
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


    /**
     * Metoodo que permite carregar os tipos
     * @param atualizacoes as atualizações dos tipos
     * @param handlerUI um handler para as mensagens
     */
    private void atualizarTipos(Activity activity, AtualizacaoTipos atualizacoes, Handler handlerUI){

        tiposRepositorio.obterTipos(atualizacoes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Object>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Object> registos) {

                                showProgressBar(false);

                                AtualizarTipoAsyncTask servico = new AtualizarTipoAsyncTask(activity, vvmshBaseDados, handlerUI, carregamentoTiposRepositorio);
                                servico.execute(registos);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                formatarErro(e);
                            }
                        }

                );
    }



























}
