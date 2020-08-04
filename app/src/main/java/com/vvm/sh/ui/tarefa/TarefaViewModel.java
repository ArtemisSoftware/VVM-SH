package com.vvm.sh.ui.tarefa;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.TarefaRepositorio;
import com.vvm.sh.servicos.ResultadoAsyncTask;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.ui.agenda.modelos.TarefaDia;
import com.vvm.sh.ui.atividadesExecutadas.modelos.AtividadeExecutada;
import com.vvm.sh.ui.cliente.Cliente;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.ui.tarefa.modelos.OpcaoCliente;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TarefaViewModel extends BaseViewModel {

    private final TarefaRepositorio tarefaRepositorio;

    public MutableLiveData<Cliente> cliente;
    public MutableLiveData<TarefaDia> tarefaDia;
    public MutableLiveData<Tarefa> tarefa;
    public MutableLiveData<EmailResultado> email;
    public MutableLiveData<List<AtividadeExecutada>> atividadesExecutadas;
    public MutableLiveData<List<OpcaoCliente>> opcoesCliente;
    public MutableLiveData<List<Tipo>> opcoesEmail;


    /**
     * Variavel que indica se a tarefa está validada
     */
    public MutableLiveData<Boolean> tarefaValidada;


    @Inject
    public TarefaViewModel(TarefaRepositorio tarefaRepositorio){

        this.tarefaRepositorio = tarefaRepositorio;
        tarefa = new MutableLiveData<>();
        tarefaDia = new MutableLiveData<>();
        cliente = new MutableLiveData<>();
        email = new MutableLiveData<>();
        opcoesCliente = new MutableLiveData<>();
        opcoesEmail = new MutableLiveData<>();
        atividadesExecutadas = new MutableLiveData<>();

        tarefaValidada = new MutableLiveData<>();
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


        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, tarefaRepositorio.resultadoDao);
        servico.execute(new Resultado(email.idTarefa, ResultadoId.EMAIL));
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
                                tarefa.setValue(registo.tarefa);
                                cliente.setValue(registo.cliente);
                                email.setValue(registo.email);
                                obterOpcoesCliente(registo.email);
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



    //--------------------
    //Misc
    //--------------------


    /**
     * Metodo que permite obter os dados do cliente
     * @param email os dados do email
     */
    private void obterOpcoesCliente(EmailResultado email) {

        List<OpcaoCliente> items = new ArrayList<>();

//        items.add(new OpcaoCliente(Identificadores.OpcoesCliente .OPCAO_INFORMACAO, "Informação"));
        items.add(OpcaoCliente.email());
        items.add(OpcaoCliente.crossSelling());

        opcoesCliente.setValue(items);

        if(email == null){
            tarefaValidada.setValue(false);
        }
        else{
            tarefaValidada.setValue(true);
        }
    }


    /**
     * Metodo que permite obter as opcoes do email
     */
    private void obterOpcoesEmail() {

        List<Tipo> items = new ArrayList<>();

        items.add(TiposConstantes.EMAIL_CLIENTE_NAO_TEM_EMAIL);
        items.add(TiposConstantes.EMAIL_AUTORIZADO);
        items.add(TiposConstantes.EMAIL_NAO_AUTORIZADO);

        opcoesEmail.setValue(items);

    }




}
