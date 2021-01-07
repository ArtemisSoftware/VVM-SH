package com.vvm.sh.ui.tarefa;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.ParqueExtintorResultado;
import com.vvm.sh.baseDados.entidades.SinistralidadeResultado;
import com.vvm.sh.repositorios.TarefaRepositorio;
import com.vvm.sh.ui.parqueExtintores.modelos.ExtintorRegisto;
import com.vvm.sh.ui.tarefa.modelos.TarefaDia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.ui.tarefa.modelos.OpcaoCliente;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class TarefaViewModel extends BaseViewModel {

    private final TarefaRepositorio tarefaRepositorio;
    private final int idApi;

    public MutableLiveData<TarefaDia> tarefaDia;
    public MutableLiveData<List<AtividadeExecutada>> atividadesExecutadas;
    public MutableLiveData<List<OpcaoCliente>> opcoesCliente;
    public MutableLiveData<List<Tipo>> opcoesEmail;

    public MutableLiveData<SinistralidadeResultado> sinistralidade;
    public MutableLiveData<List<ExtintorRegisto>> extintores;
    public MutableLiveData<Integer> estatistica;


    @Inject
    public TarefaViewModel(int idApi, TarefaRepositorio tarefaRepositorio){
        this.idApi = idApi;
        this.tarefaRepositorio = tarefaRepositorio;
        tarefaDia = new MutableLiveData<>();
        opcoesCliente = new MutableLiveData<>();
        opcoesEmail = new MutableLiveData<>();
        atividadesExecutadas = new MutableLiveData<>();
        sinistralidade = new MutableLiveData<>();
        extintores = new MutableLiveData<>();
        estatistica = new MutableLiveData<>();
    }


    //--------------------
    //GRAVAR
    //--------------------


    /**
     * Metodo que permite gravar um email
     * @param email os dados do email
     */
    public void gravarEmail(EmailResultado email) {

        if(tarefaDia.getValue().email == null){

            tarefaRepositorio.inserir(email)
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
                                    messagemLiveData.setValue(Recurso.successo());
                                    gravarResultado(tarefaRepositorio.resultadoDao, email.idTarefa, ResultadoId.EMAIL);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }
                            }
                    );
        }
        else{

            tarefaRepositorio.atualizar(email)
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
                                    messagemLiveData.setValue(Recurso.successo());
                                    gravarResultado(tarefaRepositorio.resultadoDao, email.idTarefa, ResultadoId.EMAIL);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }
                            }
                    );
        }
    }


    /**
     * Metodo que permite gravar uma sinistralidade
     * @param sinistralidade os dados a gravar
     */
    public void gravarSinistralidade(SinistralidadeResultado sinistralidade) {

        showProgressBar(true);

        if(this.sinistralidade.getValue() == null){
            tarefaRepositorio.inserir(sinistralidade)
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
                                    gravarResultado(tarefaRepositorio.resultadoDao, sinistralidade.idTarefa, ResultadoId.SINISTRALIDADE);
                                    showProgressBar(false);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                    showProgressBar(false);
                                }
                            }
                    );
        }
        else{
            tarefaRepositorio.atualizar(sinistralidade)
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
                                    gravarResultado(tarefaRepositorio.resultadoDao, sinistralidade.idTarefa, ResultadoId.SINISTRALIDADE);
                                    showProgressBar(false);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                    showProgressBar(false);
                                }
                            }
                    );
        }

    }




    /**
     * Metodo que permite gravar um extintor
     * @param registo os dados do extintor
     * @param data a nova data de validade do extintor
     */
    public void gravarExtintor(ExtintorRegisto registo, Date data) {

        ParqueExtintorResultado resultado = new ParqueExtintorResultado(registo.parqueExtintor.id, data);

        if(registo.resultado == null){

            tarefaRepositorio.inserir(resultado)
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
                                    gravarResultado(tarefaRepositorio.resultadoDao, registo.parqueExtintor.idTarefa, ResultadoId.PARQUE_EXTINTOR);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }

                    );
        }
        else{
            tarefaRepositorio.atualizar(resultado)
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
                                    gravarResultado(tarefaRepositorio.resultadoDao, registo.parqueExtintor.idTarefa, ResultadoId.PARQUE_EXTINTOR);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }

                    );
        }

    }


    /**
     * Metodo que permite validar os extintores
     * @param idTarefa o identificador da tarefa
     */
    public void validarExtintores(int idTarefa) {

        showProgressBar(true);

        Completable.concatArray(tarefaRepositorio.atualizarValidacao(idTarefa), tarefaRepositorio.inserirValicao(idTarefa))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onComplete() {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_VALIDADOS_SUCESSO));
                                gravarResultado(tarefaRepositorio.resultadoDao, idTarefa, ResultadoId.PARQUE_EXTINTOR);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }

                );

    }


    public void gravarAnomalia(int idTarefa) {

        showProgressBar(true);

        tarefaRepositorio.inserirAnomalia(idTarefa, Identificadores.ID_ANOMALIA_FALTA_TEMPO, Sintaxe.Frases.ANOMALIA_FALTA_TEMPO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onComplete() {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_VALIDADOS_SUCESSO));
                                gravarResultado(tarefaRepositorio.resultadoDao, idTarefa, ResultadoId.ATIVIDADE_PENDENTE);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }
                );


    }


    //--------------------
    //OBTER - tarefa
    //--------------------


    /**
     * Metodo que permite obter os dados tarefa
     * @param idTarefa o identificador da tarefa
     */
    public void obterTarefa(int idTarefa){

        showProgressBar(true);

        tarefaRepositorio.obterTarefa(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<TarefaDia>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(TarefaDia registo) {

                                tarefaDia.setValue(registo);
                                obterOpcoesCliente(registo);
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
     * Metodo que permite obter as opcoes do email
     * @param idTarefa o identificador da tarefa
     */
    public void obterEmail(int idTarefa) {

        obterOpcoesEmail();
        obterTarefa(idTarefa);
    }


    //--------------------
    //OBTER - atividades executadas
    //--------------------

    /**
     * Metodo que permite obter as atividades executadas
     * @param idTarefa o identificador da tarefa
     */
    public void obterAtividadesExecutadas(int idTarefa){

        showProgressBar(true);

        tarefaRepositorio.obterAtividadesExecutadas(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<AtividadeExecutada>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<AtividadeExecutada> registos) {
                                atividadesExecutadas.setValue(registos);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                formatarErro(e);
                                showProgressBar(false);
                            }
                        }
                );
    }


    //--------------------
    //OBTER - sinistralidade
    //--------------------

    /**
     * Metodo que permite obter a sinistralidade
     * @param idTarefa o identificador da tarefa
     */
    public void obterSinistralidade(int idTarefa) {

        showProgressBar(true);

        tarefaRepositorio.obterSinistralidade(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<SinistralidadeResultado>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(SinistralidadeResultado sinistralidadeResultado) {
                                sinistralidade.setValue(sinistralidadeResultado);
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


    //--------------------
    //OBTER - extintores
    //--------------------

    /**
     * Metodo que permite obter os extintores
     * @param idTarefa o identificador da tarefa
     */
    public void obterExtintores(int idTarefa) {

        estatistica.setValue(0);

        showProgressBar(true);

        Observable.zip(tarefaRepositorio.obterExtintores(idTarefa).toObservable(), tarefaRepositorio.obterEstatisticaExtintores(idTarefa),
                new BiFunction<List<ExtintorRegisto>, Integer, ParqueExtintor>() {
                    @Override
                    public ParqueExtintor apply(List<ExtintorRegisto> registos, Integer estatistica) throws Exception {

                        ParqueExtintor registo = new ParqueExtintor();
                        registo.estatistica = estatistica;
                        registo.registos = registos;
                        return registo;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<ParqueExtintor>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(ParqueExtintor resultado) {
                                extintores.setValue(resultado.registos);
                                estatistica.setValue(resultado.estatistica);
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


    //--------------------
    //Misc
    //--------------------


    /**
     * Metodo que permite obter as opcoes do cliente
     */
    private void obterOpcoesCliente(TarefaDia registo) {

        List<OpcaoCliente> items = new ArrayList<>();
        items.add(OpcaoCliente.informacao());

        if(registo.estadoBaixas == false) {
            items.add(OpcaoCliente.semTempo());
        }

        items.add(OpcaoCliente.email());
        items.add(OpcaoCliente.crossSelling());

        if(idApi == Identificadores.App.APP_ST) {
            items.add(OpcaoCliente.sinistralidade());


            if(registo.existeAnomalias == true){
                tarefaRepositorio.removerExtintores(registo.tarefa.idTarefa);
            }
            else if(registo.extintores == true){
                items.add(OpcaoCliente.parqueExtintores());
            }

            items.add(OpcaoCliente.quadroPessoal());
            items.add(OpcaoCliente.registoVisita());
            items.add(OpcaoCliente.informacaoSst());
            items.add(OpcaoCliente.planoAcao());
        }

        opcoesCliente.setValue(items);
    }


    /**
     * Metodo que permite obter as opcoes do email
     */
    private void obterOpcoesEmail() {

        List<Tipo> items = new ArrayList<>();

        items.add(TiposConstantes.Email.EMAIL_AUTORIZADO);
        items.add(TiposConstantes.Email.EMAIL_NAO_AUTORIZADO);
        items.add(TiposConstantes.Email.EMAIL_CLIENTE_NAO_TEM_EMAIL);

        opcoesEmail.setValue(items);
    }




    private class ParqueExtintor{

        int estatistica;
        List<ExtintorRegisto> registos;

    }

}
