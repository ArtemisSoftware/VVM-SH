package com.vvm.sh.ui.informacaoSst;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.ObrigacaoLegalResultado;
import com.vvm.sh.repositorios.InformacaoSstRepositorio;
import com.vvm.sh.ui.informacaoSst.modelos.ObrigacaoLegal;
import com.vvm.sh.ui.informacaoSst.modelos.RelatorioInformacaoSst;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class InformacaoSstViewModel extends BaseViewModel {

    private final InformacaoSstRepositorio informacaoSstRepositorio;

    public MutableLiveData<RelatorioInformacaoSst> relatorio;
    public MutableLiveData<List<ObrigacaoLegal>> obrigacoes;

    public InformacaoSstViewModel(InformacaoSstRepositorio informacaoSstRepositorio) {

        this.informacaoSstRepositorio = informacaoSstRepositorio;
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
                                gravarResultado(informacaoSstRepositorio.resultadoDao, idTarefa, informacaoSstRepositorio.resultadoId);
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
                                gravarResultado(informacaoSstRepositorio.resultadoDao, registo.idTarefa, informacaoSstRepositorio.resultadoId);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );

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
                                gravarResultado(informacaoSstRepositorio.resultadoDao, idTarefa, informacaoSstRepositorio.resultadoId);
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

        //--existeRelatorio(idTarefa);

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



}
