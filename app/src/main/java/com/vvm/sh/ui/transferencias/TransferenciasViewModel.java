package com.vvm.sh.ui.transferencias;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.himanshurawat.hasher.HashType;
import com.himanshurawat.hasher.Hasher;
import com.vvm.sh.api.BlocoImagens;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.servicos.tipos.AtualizarTipoAsyncTask;
import com.vvm.sh.servicos.trabalho.RecarregarTarefaAsyncTask;
import com.vvm.sh.servicos.trabalho.RecarregarTrabalhoAsyncTask;
import com.vvm.sh.servicos.trabalho.CarregarTrabalhoAsyncTask;
import com.vvm.sh.servicos.upload.DadosUploadSHAsyncTask;
import com.vvm.sh.ui.transferencias.modelos.DadosPendencia;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Sessao;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.excepcoes.RespostaWsInvalidaException;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.metodos.TiposUtil;
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
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TransferenciasViewModel extends BaseViewModel {

    private final TransferenciasRepositorio transferenciasRepositorio;
    private final UploadRepositorio uploadRepositorio;
    private final TiposRepositorio tiposRepositorio;
    private final CarregamentoTiposRepositorio carregamentoTiposRepositorio;

    public MutableLiveData<List<Pendencia>> pendencias;
    public MutableLiveData<List<Upload>> uploads;

    @Inject
    public TransferenciasViewModel(TransferenciasRepositorio transferenciasRepositorio, TiposRepositorio tiposRepositorio, UploadRepositorio uploadRepositorio, CarregamentoTiposRepositorio carregamentoTiposRepositorio){

        this.transferenciasRepositorio = transferenciasRepositorio;
        this.uploadRepositorio = uploadRepositorio;
        this.tiposRepositorio = tiposRepositorio;
        this.carregamentoTiposRepositorio = carregamentoTiposRepositorio;

        this.uploads = new MutableLiveData<>();
        this.pendencias = new MutableLiveData<>();
    }

    public MutableLiveData<List<Pendencia>> observarPendencias(){
        return pendencias;
    }

    public MutableLiveData<List<Upload>> observarUpload(){
        return uploads;
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

                                    if (dadosPendencia.dadosUpload == true & dadosPendencia.pendencias.size() == 0) {
                                        messagemLiveData.setValue(Recurso.erro(Sintaxe.Frases.EXISTEM_DADOS_UPLOAD));
                                    } else {
                                        atualizarTipos(activity, handlerNotificacoesUI);
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
     * Metodo que permite obter o trabalho do dia
     * @param idUtilizador o identificador do utilizador
     */
    public void obterTrabalho(Context contexto, String idUtilizador, Handler handler){

        showProgressBar(true);

        transferenciasRepositorio.obterTrabalho(idUtilizador)
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
                                CarregarTrabalhoAsyncTask servico = new CarregarTrabalhoAsyncTask(vvmshBaseDados, transferenciasRepositorio, handler, idUtilizador);
                                servico.execute(sessao.iSessao);

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
                                servico.execute(sessao.iSessao);

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
     * Metodo que permite recarregar uma tarefa
     * @param tarefa os dados da tarefa a recarregar
     */
    public void recarregarTarefa(Context contexto, Tarefa tarefa, Handler handler){

        showProgressBar(true);

        transferenciasRepositorio.obterTrabalho(tarefa.idUtilizador)
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
                                servico.execute(sessao.iSessao);

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

        if(AppConfig.sa){

        }
        else{
            DadosUploadSHAsyncTask servico = new DadosUploadSHAsyncTask(vvmshBaseDados, handler, uploadRepositorio, idUtilizador);
            servico.execute(resultado);
        }

//        DadosUploadAsyncTask servico = new DadosUploadAsyncTask(vvmshBaseDados, handler, uploadRepositorio, idUtilizador);
//        servico.execute(resultado);
    }


    /**
     * Metodo que permite realizar o upload dos dados
     * @param dadosUpload os dados a enviar
     */
    public void upload(DadosUpload dadosUpload) {

        showProgressBar(true);

        dadosUpload.formatarDados();

        transferenciasRepositorio.submeterDados(dadosUpload.obterDados(), dadosUpload.idUtilizador, dadosUpload.idUpload, dadosUpload.messageDigest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Codigo>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Codigo codigo) {
                                uploadImagens(dadosUpload);
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
     * Metodo que permite realizar o upload das imagens
     * @param dadosUpload os dados a enviar
     */
    private void uploadImagens(DadosUpload dadosUpload){

        if(dadosUpload.idBloco == 0){

            sincronizar();
            return;
        }

        Gson gson = new Gson();
        List<Observable<Codigo>> observables = new ArrayList<>();


        for(int index = 0; index < dadosUpload.idBloco; ++index){

            BlocoImagens registoImagem = new BlocoImagens();
            registoImagem.dadosImagens = dadosUpload.imagens.get(index);
            String imagens = gson.toJson(registoImagem);
            String messageDigest = Hasher.Companion.hash(imagens, HashType.MD5);

            observables.add(transferenciasRepositorio.submeterImagens(imagens, dadosUpload.idUtilizador, dadosUpload.idUpload, dadosUpload.idBloco + "", messageDigest).toObservable());
        }

        Observable<Codigo> observable = Observable.zip(observables, new Function<Object[], Codigo>() {
            @Override
            public Codigo apply(Object[] codigos) throws Exception {

                boolean valido = true;

                for (Object item : codigos) {

                    if(((Codigo) item).codigo != Identificadores.CodigosWs.ID_100){
                        valido = false;
                        break;
                    }
                }

                if(valido == true){
                    return new Codigo(Identificadores.CodigosWs.ID_100, Identificadores.CodigosWs.MSG_100);
                }
                else{
                    Codigo codigo = new Codigo(Identificadores.CodigosWs.ID_600, Identificadores.CodigosWs.MSG_600);
                    throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));
                }
            }
        });


        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Codigo>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Codigo o) {
                                sincronizar();
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


    /**
     * Metodo que permite sincronizar os dados
     */
    private void sincronizar(){

        transferenciasRepositorio.sincronizar(uploads.getValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Integer integer) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_ENVIADOS_SUCESSO));
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
    //TIPOS
    //------------------------


    /**
     * Metodo que permite atualizar os tipos
     * @param handlerUI um handler para as mensagens
     */
    public void atualizarTipos(Activity activity, Handler handlerUI) {

        tiposRepositorio.obterAtualizacoes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<List<TiposUtil.MetodoApi>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<TiposUtil.MetodoApi> atualizacoes) {
                                atualizarTipos(activity, atualizacoes, handlerUI);
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


    /**
     * Metoodo que permite carregar os tipos
     * @param atualizacoes as atualizações dos tipos
     * @param handlerUI um handler para as mensagens
     */
    private void atualizarTipos(Activity activity, List<TiposUtil.MetodoApi> atualizacoes, Handler handlerUI){

        showProgressBar(true);

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
