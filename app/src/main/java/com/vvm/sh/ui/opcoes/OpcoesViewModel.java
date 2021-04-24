package com.vvm.sh.ui.opcoes;

import android.app.Activity;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.pedido.ITipoChecklist;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.repositorios.RedeRepositorio;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.servicos.tipos.recarregar.RecarregarTipoAsyncTask;
import com.vvm.sh.servicos.tipos.recarregar.RecarregarTipoAtividadesPlaneaveisAsyncTask;
import com.vvm.sh.servicos.tipos.recarregar.RecarregarTipoChecklistAsyncTask;
import com.vvm.sh.servicos.tipos.recarregar.RecarregarTipoTemplateAvrAsyncTask;
import com.vvm.sh.ui.opcoes.modelos.ResumoChecklist;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class OpcoesViewModel extends BaseViewModel {


    private final TiposRepositorio tiposRepositorio;
    private final CarregamentoTiposRepositorio carregamentoTiposRepositorio;
    private final RedeRepositorio redeRepositorio;

    public MutableLiveData<List<ResumoTipo>> tipos;
    public MutableLiveData<List<ResumoChecklist>> tiposChecklist;
    public MutableLiveData<List<TipoNovo>> tiposEquipamentos;

    @Inject
    public OpcoesViewModel(CarregamentoTiposRepositorio carregamentoTiposRepositorio, TiposRepositorio tiposRepositorio, RedeRepositorio redeRepositorio){

        this.tiposRepositorio = tiposRepositorio;
        this.carregamentoTiposRepositorio = carregamentoTiposRepositorio;
        this.redeRepositorio = redeRepositorio;

        tipos = new MutableLiveData<>();
        tiposChecklist = new MutableLiveData<>();
        tiposEquipamentos = new MutableLiveData<>();
    }


    /**
     * Metodo que permite obter o resumo
     * @param tipo o identificador do tipo
     */
    public void obterResumo(int tipo) {

        switch(tipo){

            case Identificadores.Atualizacoes.TIPO:

                obterResumoTipo();
                break;

            case Identificadores.Atualizacoes.CHECKLIST:

                obterResumoChecklist();
                break;

            case Identificadores.Atualizacoes.TEMPLATE:

                obterResumoTemplate();
                break;

            case Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS:

                obterResumoAtividadesPlaneaveis();
                break;

            default:
                break;

        }
    }



    /**
     * Metodo que permite obter o resumo
     * @param contexto
     * @param idTipo
     * @param descricao
     * @param handlerNotificacoesUI
     */
    public void recarregar(Activity contexto, int idTipo, ResumoTipo descricao, Handler handlerNotificacoesUI, OnTransferenciaListener listener) {

        switch(idTipo){

            case Identificadores.Atualizacoes.TIPO:

                //--recarregarTipos(contexto, descricao);
                break;

            case Identificadores.Atualizacoes.CHECKLIST:

                //--recarregarItemChecklist(contexto, descricao);
                break;


            case Identificadores.Atualizacoes.TEMPLATE:


                //TODO: recarregar template
                break;


            case Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS:

                //TODO: recarregar atividades planeaveis
                break;

            default:
                break;

        }
    }



    /**
     * Metodo que permite recarregar todos os registos
     * @param tipo o identificador
     */
    public void recarregarRegistos(int tipo, Activity activity, Handler handlerNotificacoesUI){

        switch(tipo){

            case Identificadores.Atualizacoes.TIPO:

                recarregarTipos(activity);
                break;

            case Identificadores.Atualizacoes.CHECKLIST:

                recarregarChecklist(activity);
                break;

            case Identificadores.Atualizacoes.TEMPLATE:

                recarregarTemplates(activity);
                break;



            case Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS:

                recarregarAtividadesPlaneaveis(activity);
                break;

            default:
                break;

        }
    }



    //---------------------
    //Resumos
    //---------------------



    /**
     * Metodo que permite obter o resumo dos registos existentes
     */
    private void obterResumoTipo(){

        showProgressBar(true);

        tiposRepositorio.obterResumoTipos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<ResumoTipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<ResumoTipo> registos) {

                                tipos.setValue(registos);
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
     * Metodo que permite obter o resumo dos registos existentes
     */
    private void obterResumoChecklist(){

        showProgressBar(true);

        tiposRepositorio.obterResumoChecklist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<ResumoChecklist>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<ResumoChecklist> registos) {
                                tiposChecklist.setValue(registos);
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
     * Metodo que permite obter o resumo dos templates existentes
     */
    private void obterResumoTemplate(){

        showProgressBar(true);

        tiposRepositorio.obterResumoTemplate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<ResumoTipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<ResumoTipo> registos) {
                                tipos.setValue(registos);
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
     * Metodo que permite obter o resumo dos registos existentes
     */
    private void obterResumoAtividadesPlaneaveis(){

        showProgressBar(true);

        tiposRepositorio.obterResumoAtividadesPlaneaveis()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<ResumoTipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<ResumoTipo> registos) {
                                tipos.setValue(registos);
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
    //Recarregar - item
    //---------------------



    /**
     * Metodo que permite atualizar um tipo
     */
    public void recarregarItemTipo(Activity contexto, ResumoTipo resumo) {

        showProgressBar(true);

        try {
            redeRepositorio.obterTipo(resumo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<List<ITipoListagem>>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(List<ITipoListagem> iTipoListagems) {

                                    List<Object> respostas = new ArrayList<>();
                                    for (ITipoListagem item : iTipoListagems) {
                                        respostas.add(item);
                                    }


                                    RecarregarTipoAsyncTask servico = new RecarregarTipoAsyncTask(contexto, vvmshBaseDados, carregamentoTiposRepositorio);
                                    servico.execute(respostas);
                                    showProgressBar(false);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    showProgressBar(false);
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }
                            }
                    );
        } catch (TipoInexistenteException e) {
            showProgressBar(false);
            messagemLiveData.setValue(Recurso.erro(e.getMessage()));
        }


    }


    public void recarregarItemChecklist(Activity atividade, ResumoChecklist resumo) {
        showProgressBar(true);

        redeRepositorio.obterChecklist(resumo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new SingleObserver<ITipoChecklist>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(ITipoChecklist iTipoChecklist) {

                                List<Object> respostas = new ArrayList<>();
                                respostas.add(iTipoChecklist);

                                RecarregarTipoChecklistAsyncTask servico = new RecarregarTipoChecklistAsyncTask(atividade, vvmshBaseDados, carregamentoTiposRepositorio);
                                servico.execute(respostas);

                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                            }
                        }
                );

    }



    //---------------------
    //Recarregar - registos
    //---------------------


    /**
     * Metodo que permite recarregar todos os tipos
     */
    private void recarregarTipos(Activity atividade){

        showProgressBar(true);

            redeRepositorio.obterTipos()
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
                                    RecarregarTipoAsyncTask servico = new RecarregarTipoAsyncTask(atividade, vvmshBaseDados, carregamentoTiposRepositorio);
                                    servico.execute(registos);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    showProgressBar(false);
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }
                            }

//                            new SingleObserver<List<ITipoListagem>>() {
//                                @Override
//                                public void onSubscribe(Disposable d) {
//                                    disposables.add(d);
//                                }
//
//                                @Override
//                                public void onSuccess(List<ITipoListagem> iTipoListagems) {
//
//                                    showProgressBar(false);
//
//                                    CarregarTipoAsyncTask servico = new CarregarTipoAsyncTask(activity, vvmshBaseDados, handlerNotificacoesUI, carregamentoTiposRepositorio);
//                                    servico.execute(iTipoListagems);
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    showProgressBar(false);
//                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
//                                }
//                            }
                    );

    }


    /**
     * Metodo que permite recarregar todas as checklists
     */
    private void recarregarChecklist(Activity atividade){

        showProgressBar(true);


        tiposRepositorio.eliminarChecklists()
                .flatMap(new Function<Integer, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Integer integers) throws Exception {
                        return redeRepositorio.obterChecklists();
                    }
                })
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

                                RecarregarTipoChecklistAsyncTask servico = new RecarregarTipoChecklistAsyncTask(atividade, vvmshBaseDados, carregamentoTiposRepositorio);
                                servico.execute((List<Object>) o);

                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                formatarErro(e);
                            }
                        }
                );



        //------------------------------------

//
//        List<ITipoChecklist> respostasChecklist = new ArrayList<>();

//        tiposRepositorio.obterIdChecklists().toObservable()
//                .flatMap(new Function<List<Integer>, ObservableSource<ITipoChecklist>>() {
//                    @Override
//                    public ObservableSource<ITipoChecklist> apply(List<Integer> ids) throws Exception {
//                        return tiposRepositorio.obterChecklists(ids);
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//
//                        new Observer<ITipoChecklist>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//                                disposables.add(d);
//                            }
//
//                            @Override
//                            public void onNext(ITipoChecklist o) {
//                                respostasChecklist.add(o);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                showProgressBar(false);
//                                formatarErro(e);
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                CarregarTipoChecklistAsyncTask servico = new CarregarTipoChecklistAsyncTask(atividade, vvmshBaseDados, handlerNotificacoesUI, tiposRepositorio);
//                                servico.execute(respostasChecklist);
//
//                                showProgressBar(false);
//                            }
//                        }
//
//                );
    }



    /**
     * Metodo que permite recarregar todos os templates
     */
    private void recarregarTemplates(Activity atividade){

        showProgressBar(true);

        redeRepositorio.obterTemplateAvr()
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

                                RecarregarTipoTemplateAvrAsyncTask servico = new RecarregarTipoTemplateAvrAsyncTask(atividade, vvmshBaseDados, carregamentoTiposRepositorio);
                                servico.execute(registos);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }
                );

    }



    /**
     * Metodo que permite recarregar todas as atividades planeaveis
     */
    private void recarregarAtividadesPlaneaveis(Activity atividade){

        showProgressBar(true);

        redeRepositorio.obterAtividadesPlaneaveis()
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

                                RecarregarTipoAtividadesPlaneaveisAsyncTask servico = new RecarregarTipoAtividadesPlaneaveisAsyncTask(atividade, vvmshBaseDados, carregamentoTiposRepositorio);
                                servico.execute(registos);

                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }
                );
    }


    public void obterEquipamentos() {
    }
}
