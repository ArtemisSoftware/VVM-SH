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
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

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

    public MutableLiveData<TarefaDia> tarefaDia;
    public MutableLiveData<List<AtividadeExecutada>> atividadesExecutadas;
    public MutableLiveData<List<OpcaoCliente>> opcoesCliente;
    public MutableLiveData<List<Tipo>> opcoesEmail;

    public MutableLiveData<SinistralidadeResultado> sinistralidade;
    public MutableLiveData<List<ExtintorRegisto>> extintores;


    @Inject
    public TarefaViewModel(TarefaRepositorio tarefaRepositorio){

        this.tarefaRepositorio = tarefaRepositorio;
        tarefaDia = new MutableLiveData<>();
        opcoesCliente = new MutableLiveData<>();
        opcoesEmail = new MutableLiveData<>();
        atividadesExecutadas = new MutableLiveData<>();
        sinistralidade = new MutableLiveData<>();
        extintores = new MutableLiveData<>();

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

            tarefaRepositorio.inserir(email).toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Long>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onNext(Long aLong) {
                                    messagemLiveData.setValue(Recurso.successo());
                                }

                                @Override
                                public void onError(Throwable e) {
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }

                                @Override
                                public void onComplete() {

                                }
                            }
                    );
        }
        else{

            tarefaRepositorio.atualizar(email).toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onNext(Integer integer) {
                                    messagemLiveData.setValue(Recurso.successo());
                                }

                                @Override
                                public void onError(Throwable e) {
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }

                                @Override
                                public void onComplete() {

                                }
                            }
                    );
        }

        gravarResultado(tarefaRepositorio.resultadoDao, email.idTarefa, ResultadoId.EMAIL);

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

        gravarResultado(tarefaRepositorio.resultadoDao, sinistralidade.idTarefa, ResultadoId.SINISTRALIDADE);
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
            tarefaRepositorio.atualizar(resultado)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {

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

        gravarResultado(tarefaRepositorio.resultadoDao, registo.parqueExtintor.idTarefa, ResultadoId.PARQUE_EXTINTOR);
    }


    /**
     * Metodo que permite validar os extintores
     * @param idTarefa o identificador da tarefa
     */
    public void validarExtintores(int idTarefa) {

        /*
        Observable<Object> observables = Observable.zip(
                tarefaRepositorio.inserirValicao(idTarefa),
                tarefaRepositorio.atualizarValidacao(idTarefa),
                new BiFunction<Integer, Integer, Object>() {
                    @Override
                    public Object apply(Integer integer, Integer integer2) throws Exception {
                        return null;
                    }
                });

        observables
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Object o) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_VALIDADOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

        gravarResultado(tarefaRepositorio.resultadoDao, idTarefa, ResultadoId.PARQUE_EXTINTOR);
        */
    }




    //--------------------
    //OBTER
    //--------------------


    /**
     * Metodo que permite obter os dados tarefa
     * @param idTarefa
     */
    public void obterTarefa(int idTarefa){

        showProgressBar(true);

        tarefaRepositorio.obterTarefa(idTarefa).toObservable()
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
                                obterOpcoesCliente();
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



    /**
     * Metodo que permite obter as atividades executadas
     * @param idTarefa o identificador da tarefa
     */
    public void obterAtividadesExecutadas(int idTarefa){

        showProgressBar(true);

        tarefaRepositorio.obterAtividadesExecutadas(idTarefa).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<AtividadeExecutada>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<AtividadeExecutada> registos) {

                                atividadesExecutadas.setValue(registos);
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


    public void obterExtintores(int idTarefa) {

        //TODO: fazer zip disto?

        showProgressBar(true);

        tarefaRepositorio.obterExtintores(idTarefa).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<ExtintorRegisto>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<ExtintorRegisto> registos) {
                                extintores.setValue(registos);
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


        tarefaRepositorio.obterEstatisticaExtintores(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integer) {

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


    //--------------------
    //Misc
    //--------------------


    /**
     * Metodo que permite obter os dados do cliente
     */
    private void obterOpcoesCliente() {

        List<OpcaoCliente> items = new ArrayList<>();
        items.add(OpcaoCliente.informacao());
        items.add(OpcaoCliente.email());
        items.add(OpcaoCliente.crossSelling());
        items.add(OpcaoCliente.sinistralidade());

        opcoesCliente.setValue(items);
    }


    /**
     * Metodo que permite obter as opcoes do email
     */
    private void obterOpcoesEmail() {

        List<Tipo> items = new ArrayList<>();

        items.add(TiposConstantes.Email.EMAIL_CLIENTE_NAO_TEM_EMAIL);
        items.add(TiposConstantes.Email.EMAIL_AUTORIZADO);
        items.add(TiposConstantes.Email.EMAIL_NAO_AUTORIZADO);

        opcoesEmail.setValue(items);

    }


}
