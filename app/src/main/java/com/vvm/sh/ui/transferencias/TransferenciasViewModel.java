package com.vvm.sh.ui.transferencias;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.himanshurawat.hasher.HashType;
import com.himanshurawat.hasher.Hasher;
import com.vvm.sh.api.BlocoDados;
import com.vvm.sh.api.BlocoImagens;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.servicos.CarregarTipoAsyncTask;
import com.vvm.sh.servicos.DadosUploadAsyncTask;
import com.vvm.sh.servicos.RecarregarTarefaAsyncTask;
import com.vvm.sh.servicos.RecarregarTrabalhoAsyncTask;
import com.vvm.sh.servicos.TrabalhoAsyncTask;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.excepcoes.RespostaWsInvalidaException;
import com.vvm.sh.util.mapeamento.UploadMapping;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

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

                                uploads.setValue(Recurso.successo(resultado));
                                showProgressBar(false);

                                if(resultado.size() != 0) {
                                    DadosUploadAsyncTask servico = new DadosUploadAsyncTask(vvmshBaseDados, handler, transferenciasRepositorio, idUtilizador);
                                    servico.execute(resultado);
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


    /**
     * Metodo que permite realizar o upload dos dados
     * @param dadosUpload os dados a enviar
     */
    public void upload(DadosUpload dadosUpload) {

        showProgressBar(true);

        Gson gson = new Gson();
        BlocoDados registoDados = UploadMapping.INSTANCE.map(dadosUpload);
        String dados = gson.toJson(registoDados);
        String messageDigest = Hasher.Companion.hash(dados, HashType.MD5);


        transferenciasRepositorio.submeterDados(dados, dadosUpload.idUtilizador, dadosUpload.idUpload, messageDigest)
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

        transferenciasRepositorio.sincronizar((List<Upload>) uploads.getValue().dados)
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
    public void obterTrabalho(String idUtilizador, Handler handler){

        showProgressBar(true);

        transferenciasRepositorio.obterTrabalho(idUtilizador).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<ISessao>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(ISessao sessao) {

                                TrabalhoAsyncTask servico = new TrabalhoAsyncTask(vvmshBaseDados, transferenciasRepositorio, handler, idUtilizador);
                                servico.execute(sessao);

                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                                showProgressBar(false);

                                if (e instanceof RespostaWsInvalidaException){

                                    showProgressBar(false);

                                    Gson gson = new GsonBuilder().create();
                                    Codigo codigo = gson.fromJson(e.getMessage(), Codigo.class);

                                    messagemLiveData.setValue(Recurso.erro(codigo, "Download"));
                                }
                            }

                            @Override
                            public void onComplete() {

                            }
                        }

                );

    }



    /**
     * Metodo que permite obter o trabalho de um dia especifico
     * @param idUtilizador o identificador do utilizador
     * @param data a data do trabalho
     */
    public void obterTrabalho(String idUtilizador, String data, Handler handler){

        showProgressBar(true);

        transferenciasRepositorio.obterTrabalho(idUtilizador, data).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<ISessao>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(ISessao sessao) {

                                RecarregarTrabalhoAsyncTask servico = new RecarregarTrabalhoAsyncTask(vvmshBaseDados, transferenciasRepositorio, handler, idUtilizador, DatasUtil.converterDataLong(data, DatasUtil.FORMATO_YYYY_MM_DD));
                                servico.execute(sessao);

                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                                showProgressBar(false);

                                if (e instanceof RespostaWsInvalidaException){

                                    Gson gson = new GsonBuilder().create();
                                    Codigo codigo = gson.fromJson(e.getMessage(), Codigo.class);

                                    messagemLiveData.setValue(Recurso.erro(codigo, Sintaxe.Palavras.DOWNLOAD));
                                }
                            }

                            @Override
                            public void onComplete() {

                            }
                        }

                );

    }



    /**
     * Metodo que permite recarregar uma tarefa
     * @param tarefa os dados da tarefa a recarregar
     */
    public void recarregarTarefa(Tarefa tarefa, Handler handler){

        showProgressBar(true);

        transferenciasRepositorio.obterTrabalho(tarefa.idUtilizador).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<ISessao>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(ISessao sessao) {

                                RecarregarTarefaAsyncTask servico = new RecarregarTarefaAsyncTask(vvmshBaseDados, transferenciasRepositorio, handler, tarefa);
                                servico.execute(sessao);

                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                                showProgressBar(false);

                                if (e instanceof RespostaWsInvalidaException){

                                    showProgressBar(false);

                                    Gson gson = new GsonBuilder().create();
                                    Codigo codigo = gson.fromJson(e.getMessage(), Codigo.class);

                                    messagemLiveData.setValue(Recurso.erro(codigo, Sintaxe.Palavras.DOWNLOAD));
                                }
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


    public void obterTipos(Handler handlerUI){


        tiposRepositorio.obterAtualizacoes()
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<List<Atualizacao>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Atualizacao> atualizacoes) {
                                carregarTipos(atualizacoes, handlerUI);
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



    private void carregarTipos(List<Atualizacao> atualizacoes, Handler handlerUI){

        showProgressBar(true);

        List<ITipoListagem> respostas = new ArrayList<>();
        List<Observable<ITipoListagem>> pedidos = new ArrayList<>();

        for(Atualizacao atualizacao : atualizacoes){
            pedidos.add(tiposRepositorio.obterTipo(atualizacao.descricao, atualizacao.seloTemporal).toObservable());
        }

        Observable.concat(pedidos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<ITipoListagem>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(ITipoListagem iTipoListagem) {
                                respostas.add(iTipoListagem);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                //messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                            }

                            @Override
                            public void onComplete() {

                                CarregarTipoAsyncTask servico = new CarregarTipoAsyncTask(vvmshBaseDados, handlerUI, tiposRepositorio);
                                servico.execute(respostas);
                                showProgressBar(false);
                            }
                        }

                );

    }





    /**
     * Metodo que permite recarregar todos os tipos
     */
    private void recarregarTipos(){

        showProgressBar(true);

        List<ITipoListagem> respostas = new ArrayList<>();
        List<Observable<ITipoListagem>> pedidos = new ArrayList<>();

        for(String metodo : TiposConstantes.MetodosTipos.TIPOS){
            pedidos.add(tiposRepositorio.obterTipo(metodo).toObservable());
        }

        Observable.concat(pedidos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<ITipoListagem>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(ITipoListagem iTipoListagem) {
                                respostas.add(iTipoListagem);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                            }

                            @Override
                            public void onComplete() {


                                showProgressBar(false);
                            }
                        }

                );

    }
}
