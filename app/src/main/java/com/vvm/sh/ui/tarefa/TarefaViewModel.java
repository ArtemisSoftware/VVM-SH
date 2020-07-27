package com.vvm.sh.ui.tarefa;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.TarefaRepositorio;
import com.vvm.sh.ui.agenda.modelos.Tarefa;
import com.vvm.sh.ui.agenda.modelos.TarefaDia;
import com.vvm.sh.ui.atividadesExecutadas.modelos.AtividadeExecutada;
import com.vvm.sh.ui.cliente.Cliente;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.ui.tarefa.modelos.EmailResultado;
import com.vvm.sh.ui.tarefa.modelos.OpcaoCliente;
import com.vvm.sh.util.Recurso;
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
    public MutableLiveData<Tarefa> tarefa;
    public MutableLiveData<List<AtividadeExecutada>> atividadesExecutadas;
    public MutableLiveData<List<OpcaoCliente>> opcoesCliente;
    public MutableLiveData<List<Tipo>> opcoesEmail;


    /**
     * Variavel que indica se a tarefa está validada
     */
    private boolean tarefaValidada;


    @Inject
    public TarefaViewModel(TarefaRepositorio tarefaRepositorio){

        this.tarefaRepositorio = tarefaRepositorio;
        tarefa = new MutableLiveData<>();
        cliente = new MutableLiveData<>();
        opcoesCliente = new MutableLiveData<>();
        opcoesEmail = new MutableLiveData<>();
        atividadesExecutadas = new MutableLiveData<>();

        tarefaValidada = false;
    }


    //--------------------
    //GRAVAR
    //--------------------


    public void gravarEmail(EmailResultado email) {


        if(tarefaValidada == true){
            //TODO: atualizar email
        }
        else{
            //TODO: inserir email
        }

    }



    //--------------------
    //OBTER
    //--------------------


    /**
     * Metodo que permite obter os dados tarefa
     * @param idTarefa
     */
    public void obterTarefa(int idTarefa){
        obterCliente(idTarefa);
        obterOpcoesCliente(idTarefa);
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
     * Metodo que permite obter os dados do cliente
     * @param idTarefa o identificador da tarefa
     */
    public void obterCliente(int idTarefa) {

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

                                tarefa.setValue(registo.tarefa);
                                cliente.setValue(registo.cliente);
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
     * Metodo que permite obter os dados do cliente
     * @param idTarefa o identificador da tarefa
     */
    public void obterOpcoesCliente(int idTarefa) {


        List<OpcaoCliente> items = new ArrayList<>();

        items.add(new OpcaoCliente(OpcaoCliente.OPCAO_EMAIL, "Email"));
        items.add(new OpcaoCliente(OpcaoCliente.OPCAO_CROSS_SELLING, "Cross Selling"));

        opcoesCliente.setValue(items);

        //TODO: consultar a tabela email e consoante o resultado (existir - desbloquear + nao existir bloquear) mudar a variavel "tarefaValidada"
/*
        showProgressBar(true);

        tarefaRepositorio.obterCliente(idTarefa).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Cliente>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(Cliente registo) {

                                cliente.setValue(registo);
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
*/

    }


    /**
     * Metodo que permite obter as opcoes do email
     * @param idTarefa o identificador da tarefa
     */
    public void obterOpcoesEmail(int idTarefa) {

        List<Tipo> items = new ArrayList<>();

        items.add(TiposConstantes.EMAIL_CLIENTE_NAO_TEM_EMAIL);
        items.add(TiposConstantes.EMAIL_AUTORIZADO);
        items.add(TiposConstantes.EMAIL_NAO_AUTORIZADO);

        opcoesEmail.setValue(items);

        obterEmail(idTarefa);
    }


    /**
     * Metodo que permite obter as opcoes do email
     */
    public void obterEmail(int idTarefa) {

        showProgressBar(true);

        //TODO: estes dados têm que vir da tabela email

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


                                messagemLiveData.setValue(Recurso.successo(registo.cliente));

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



    public static final int TIPOS = 1;
    public static final int REGISTO = 2;


}
