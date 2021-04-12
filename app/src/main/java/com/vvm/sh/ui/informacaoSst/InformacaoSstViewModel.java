package com.vvm.sh.ui.informacaoSst;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.InformacaoSstResultado;
import com.vvm.sh.baseDados.entidades.ObrigacaoLegalResultado;
import com.vvm.sh.documentos.DadosTemplate;
import com.vvm.sh.documentos.OnDocumentoListener;
import com.vvm.sh.documentos.informacaoSst.InformacaoSst;
import com.vvm.sh.documentos.informacaoSst.modelos.DadosInformacaoSst;
import com.vvm.sh.repositorios.InformacaoSstRepositorio;
import com.vvm.sh.repositorios.RedeRepositorio;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.servicos.pdf.DocumentoPdfAsyncTask;
import com.vvm.sh.ui.informacaoSst.modelos.ObrigacaoLegal;
import com.vvm.sh.ui.informacaoSst.modelos.RelatorioInformacaoSst;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class InformacaoSstViewModel extends BaseViewModel implements OnDocumentoListener.OnVisualizar {

    private final InformacaoSstRepositorio informacaoSstRepositorio;
    private final RedeRepositorio redeRepositorio;

    public MutableLiveData<RelatorioInformacaoSst> relatorio;
    public MutableLiveData<List<ObrigacaoLegal>> obrigacoes;

    @Inject
    public InformacaoSstViewModel(InformacaoSstRepositorio informacaoSstRepositorio, RedeRepositorio redeRepositorio) {

        this.informacaoSstRepositorio = informacaoSstRepositorio;
        this.redeRepositorio = redeRepositorio;
        relatorio = new MutableLiveData<>();
        obrigacoes = new MutableLiveData<>();
    }

    //--------------------
    //GRAVAR
    //--------------------


    /**
     * Metodo que permite gravar uma obrigacao
     * @param registo os dados a gravar
     */
    public void gravar(int idTarefa, ObrigacaoLegalResultado registo, boolean selecionado){

        if(selecionado == true){
            inserir(idTarefa, registo);
        }
        else{
            remover(idTarefa, registo.id);
        }
    }


    /**
     * Metodo que permite gravar um registo
     * @param idTarefa o identificador da tarefa
     * @param imagem os dados da imagem
     */
    public void gravar(int idTarefa, byte[] imagem) {

        ImagemResultado imagemResultado = new ImagemResultado(idTarefa, idTarefa, Identificadores.Imagens.IMAGEM_ASSINATURA_INFORMACAO_SST, imagem);

        Disposable d = informacaoSstRepositorio.gravarAssinatura(imagemResultado)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(

                        new Consumer<List<? extends Number>>() {
                            @Override
                            public void accept(List<? extends Number> numbers) throws Exception {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                            }
                        }
                );

        disposables.add(d);
    }



    /**
     * Metodo que permite inserir uma obrigacao
     * @param registo os dados a gravar
     */
    private void inserir(int idTarefa, ObrigacaoLegalResultado registo){

        informacaoSstRepositorio.inserir(registo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Long>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Long aLong) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );

    }




    /**
     * Metodo que permite gravar um registo
     * @param registo os dados do registo
     */
    public void gravar(int idRelatorio, InformacaoSstResultado registo){

        if(idRelatorio == 0){

            informacaoSstRepositorio.inserir(registo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Long>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Long aLong) {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }
                    );
        }
        else{
            informacaoSstRepositorio.atualizar(registo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Integer integer) {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }

                    );
        }
    }



    //---------------------
    //REMOVER
    //---------------------


    /**
     * Metodo que permite remover uma obrigacao legal
     * @param idTarefa o identificador da tarefa
     * @param id o identificador do trabalho
     */
    private void remover(int idTarefa, int id) {

        informacaoSstRepositorio.remover(idTarefa, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Object o) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }



    //--------------------
    //OBTER
    //--------------------


    /**
     * Metodo que permite obter o relatorio
     * @param idTarefa o identificador da tarefa
     */
    public void obterRelatorio(int idTarefa){

        showProgressBar(true);

        informacaoSstRepositorio.obterRelatorioInformacaoSst(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<RelatorioInformacaoSst>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(RelatorioInformacaoSst relatorioInformacaoSst) {
                                relatorio.setValue(relatorioInformacaoSst);
                                showProgressBar(false);
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
     * Metodo que permite obter as obrigacoes legais
     * @param idTarefa o identificador da tarefa
     */
    public void obterObrigacoesLegais(int idTarefa) {

        showProgressBar(true);

        informacaoSstRepositorio.obterObrigacoesLegais(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<ObrigacaoLegal>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<ObrigacaoLegal> registos) {
                                obrigacoes.setValue(registos);
                                showProgressBar(false);
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




    //------------
    //Pdf
    //------------

    public void executarPdf(Context contexto, int idTarefa, String idUtilizador, OnDocumentoListener.AcaoDocumento acao) {
        gerarPdf(contexto, idTarefa, -1, idUtilizador, this, acao);
    }

    @Override
    public void executarPdf(Context contexto, int idTarefa, int idAtividade, String idUtilizador, OnDocumentoListener.AcaoDocumento acao) {
        gerarPdf(contexto, idTarefa, idAtividade, idUtilizador, this, acao);
    }

    @Override
    public Maybe<DadosTemplate> obterPdf(int idTarefa, int idAtividade, String idUtilizador) {
        return informacaoSstRepositorio.obtePdf(idTarefa, idUtilizador);
    }

    @Override
    public Single<Codigo> uploadRelatorio(int idTarefa, String caminhoPdf) {
        return redeRepositorio.uploadInformacaoSst(idTarefa, caminhoPdf);
    }

    @Override
    public Single<Integer> sincronizar(int idTarefa, int idAtividade) {
        return informacaoSstRepositorio.sincronizar(idTarefa);
    }

    @Override
    public void concluirPdf(int idTarefa, int idAtividade) {
        gravarResultado(informacaoSstRepositorio.resultadoDao, idTarefa, informacaoSstRepositorio.resultadoId);
    }


    //--


}
