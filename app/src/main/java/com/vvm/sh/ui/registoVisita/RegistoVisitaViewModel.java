package com.vvm.sh.ui.registoVisita;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.repositorios.RegistoVisitaRepositorio;
import com.vvm.sh.ui.registoVisita.modelos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.RegistoVisita;
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

    private final RegistoVisitaRepositorio registoVisitaRepositorio;


    public MutableLiveData<RegistoVisita> relatorio;
    public MutableLiveData<DadosCliente> registoVisita;
    public MutableLiveData<List<TrabalhoRealizado>> trabalhos;


    @Inject
    public RegistoVisitaViewModel(RegistoVisitaRepositorio registoVisitaRepositorio){

        this.registoVisitaRepositorio = registoVisitaRepositorio;

        relatorio = new MutableLiveData<>();
        registoVisita = new MutableLiveData<>();
        trabalhos = new MutableLiveData<>();

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


    //--------------------
    //OBTER
    //--------------------


    /**
     * Metodo que permite obter a validade do registo de visita
     * @param idTarefa o identificador da tarefa
     */
    public void obterValidadeRegistoVisita(int idTarefa){

        showProgressBar(true);

        registoVisitaRepositorio.obterValidadeRegistoVisita(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<RegistoVisita>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(RegistoVisita registoVisita) {
                                relatorio.setValue(registoVisita);
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



}
