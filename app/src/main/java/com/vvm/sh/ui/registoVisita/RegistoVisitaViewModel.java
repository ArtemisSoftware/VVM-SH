package com.vvm.sh.ui.registoVisita;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.documentos.registoVisita.RegistoVisita;
import com.vvm.sh.documentos.registoVisita.modelos.DadosRegistoVisita;
import com.vvm.sh.repositorios.RegistoVisitaRepositorio;
import com.vvm.sh.servicos.pdf.EnvioRegistoVisitaAsyncTask;
import com.vvm.sh.servicos.pdf.DocumentoPdfAsyncTask;
import com.vvm.sh.documentos.modelos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.RelatorioRegistoVisita;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegistoVisitaViewModel extends BaseViewModel {

    private final int PRE_VISUALIZAR_PDF = 1;
    private final int ENVIAR_PDF = 2;


    private final RegistoVisitaRepositorio registoVisitaRepositorio;


    public MutableLiveData<RelatorioRegistoVisita> relatorio;
    public MutableLiveData<DadosCliente> registoVisita;
    public MutableLiveData<List<TrabalhoRealizado>> trabalhos;
    public MutableLiveData<Boolean> possuiRelatorio;


    @Inject
    public RegistoVisitaViewModel(RegistoVisitaRepositorio registoVisitaRepositorio){

        this.registoVisitaRepositorio = registoVisitaRepositorio;

        relatorio = new MutableLiveData<>();
        registoVisita = new MutableLiveData<>();
        trabalhos = new MutableLiveData<>();
        possuiRelatorio = new MutableLiveData<>();

    }


    public MutableLiveData<Boolean> observarRelatorio(){
        return possuiRelatorio;
    }



    //--------------------
    //GRAVAR
    //--------------------


    /**
     * Metodo que permite gravar o trabalho realizado
     * @param registo os dados a gravar
     */
    public void gravar(TrabalhoRealizadoResultado registo){

        registoVisitaRepositorio.inserir(registo)
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
                                gravarResultado(registoVisitaRepositorio.resultadoDao, registo.idTarefa, ResultadoId.REGISTO_VISITA);
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
    public void gravar(RegistoVisitaResultado registo){

        if(registoVisita.getValue().registo == null){

            registoVisitaRepositorio.inserir(registo)
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
                                    gravarResultado(registoVisitaRepositorio.resultadoDao, registo.idTarefa, ResultadoId.REGISTO_VISITA);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }
                    );
        }
        else{
            registoVisitaRepositorio.atualizar(registo)
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
                                    gravarResultado(registoVisitaRepositorio.resultadoDao, registo.idTarefa, ResultadoId.REGISTO_VISITA);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }

                    );
        }
    }


    /**
     * Metodo que permite gravar um registo
     * @param idTarefa o identificador da tarefa
     * @param imagem os dados da imagem
     */
    public void gravar(int idTarefa, byte[] imagem) {

        ImagemResultado imagemResultado = new ImagemResultado(idTarefa, idTarefa, Identificadores.Imagens.IMAGEM_ASSINATURA_REGISTO_VISITA, imagem);

        Disposable d = registoVisitaRepositorio.gravarAssinatura(imagemResultado)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(

                        new Consumer<List<? extends Number>>() {
                            @Override
                            public void accept(List<? extends Number> numbers) throws Exception {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                gravarResultado(registoVisitaRepositorio.resultadoDao, idTarefa, ResultadoId.REGISTO_VISITA);
                            }
                        }
                );

        disposables.add(d);
    }



    //---------------------
    //REMOVER
    //---------------------



    /**
     * Metodo que permite remover um trabalho realizado
     * @param idTarefa o identificador da tarefa
     * @param id o identificador do trabalho
     */
    public void remover(int idTarefa, int id) {

        registoVisitaRepositorio.remover(idTarefa, id)
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
                                gravarResultado(registoVisitaRepositorio.resultadoDao, idTarefa, ResultadoId.REGISTO_VISITA);
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
     * Metodo que permite obter o relatorio do registo de visita
     * @param idTarefa o identificador da tarefa
     */
    public void obterRelatorioRegistoVisita(int idTarefa){

        existeRelatorio(idTarefa);

        registoVisitaRepositorio.obterRelatorioRegistoVisita(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<RelatorioRegistoVisita>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(RelatorioRegistoVisita registoVisita) {
                                relatorio.setValue(registoVisita);
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
     * Metodo que permite obter o registo
     * @param idTarefa o identificador da tarefa
     */
    public void obterRegisto(int idTarefa){

        showProgressBar(true);

        registoVisitaRepositorio.obterDadosCliente(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<DadosCliente>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(DadosCliente resultado) {
                                registoVisita.setValue(resultado);
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
     * Metodo que permite obter os trabalhos realizados
     * @param idTarefa o identificador da tarefa
     */
    public void obterTrabalhos(int idTarefa) {

        showProgressBar(true);

        registoVisitaRepositorio.obterTrabalhosRealizados(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<TrabalhoRealizado>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<TrabalhoRealizado> tipos) {
                                trabalhos.setValue(tipos);
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
     * Metodo que indica se existe um relatorio
     * @param idTarefa o identificador da tarefa
     */
    private void existeRelatorio(int idTarefa){

        registoVisitaRepositorio.existeRelatorio(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Boolean aBoolean) {
                                possuiRelatorio.setValue(aBoolean);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }

    //-----------------------
    //Misc
    //------------------------

    /**
     * Metodo que permite pre visualizar o pdf
     * @param contexto
     * @param idTarefa
     * @param idUtilizador
     */
    public void preVisualizarPdf(Context contexto, int idTarefa, String idUtilizador){
        gerarPdf(contexto, idTarefa, idUtilizador, PRE_VISUALIZAR_PDF);
    }


    /**
     * Metodo que permite enviar o pdf
     * @param contexto
     * @param idTarefa
     * @param idUtilizador
     */
    public void enviarPdf(Context contexto, int idTarefa, String idUtilizador){
        gerarPdf(contexto, idTarefa, idUtilizador, ENVIAR_PDF);
    }


    /**
     * Metodo que permite pré-visualizar o pdf
     * @param contexto
     * @param idTarefa
     * @param idUtilizador
     */
    private void gerarPdf(Context contexto, int idTarefa, String idUtilizador, int acao) {

        showProgressBar(true);

        registoVisitaRepositorio.obtePdf(idTarefa, idUtilizador)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<DadosRegistoVisita>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(DadosRegistoVisita registo) {
                                if(acao == PRE_VISUALIZAR_PDF) {
                                    preVisualizarPdf(contexto, idTarefa, registo);
                                }
                                else{
                                    enviarPdf(contexto, idTarefa, registo);
                                }
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
     * Metodo que permite pre visualizar o pdf do registo de visita
     * @param contexto
     * @param idTarefa
     * @param registo
     */
    private void preVisualizarPdf(Context contexto, int idTarefa, DadosRegistoVisita registo){

        Template registoVisitaTemplate = new RegistoVisita(contexto, idTarefa, registo);
        DocumentoPdfAsyncTask servico = new DocumentoPdfAsyncTask(contexto, registoVisitaTemplate);
        servico.execute();
    }



    /**
     * Metodo que permite pre visualizar o pdf do registo de visita
     * @param contexto
     * @param idTarefa
     * @param registo
     */
    private void enviarPdf(Context contexto, int idTarefa, DadosRegistoVisita registo){

        EnvioRegistoVisitaAsyncTask servico = new EnvioRegistoVisitaAsyncTask(contexto, registo.credenciaisEmail, vvmshBaseDados, registoVisitaRepositorio, idTarefa);
        servico.execute(new RegistoVisita(contexto, idTarefa, registo));
    }




}
