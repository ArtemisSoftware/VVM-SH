package com.vvm.sh.ui.opcoes;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavelListagem;
import com.vvm.sh.api.modelos.pedido.ITipoChecklist;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.VersaoApp;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.VersaoAppRepositorio;
import com.vvm.sh.servicos.tipos.CarregarTipoAsyncTask;
import com.vvm.sh.servicos.tipos.CarregarTipoChecklistAsyncTask;
import com.vvm.sh.servicos.tipos.CarregarTipoTemplatesAvrAsyncTask;
import com.vvm.sh.servicos.instalacaoApp.DownloadApkAsyncTask;
import com.vvm.sh.servicos.instalacaoApp.InstalarApkAsyncTask;
import com.vvm.sh.servicos.tipos.RecarregarTipoAsyncTask;
import com.vvm.sh.servicos.tipos.CarregarTipoAtividadesPlaneaveisAsyncTask;
import com.vvm.sh.ui.opcoes.modelos.ResumoChecklist;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.ui.opcoes.modelos.TemplateAvr;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class OpcoesViewModel extends BaseViewModel {


    private final VersaoAppRepositorio versaoAppRepositorio;
    private final TiposRepositorio tiposRepositorio;
    private final CarregamentoTiposRepositorio carregamentoTiposRepositorio;

    public MutableLiveData<VersaoApp> versaoApp;
    public MutableLiveData<List<ResumoTipo>> tipos;
    public MutableLiveData<List<ResumoChecklist>> tiposChecklist;

    @Inject
    public OpcoesViewModel(VersaoAppRepositorio versaoAppRepositorio, CarregamentoTiposRepositorio carregamentoTiposRepositorio, TiposRepositorio tiposRepositorio){

        this.versaoAppRepositorio = versaoAppRepositorio;
        this.tiposRepositorio = tiposRepositorio;
        this.carregamentoTiposRepositorio = carregamentoTiposRepositorio;

        versaoApp = new MutableLiveData<>();
        tipos = new MutableLiveData<>();
        tiposChecklist = new MutableLiveData<>();
    }


    public MutableLiveData<VersaoApp> observarVersaoApp(){
        return versaoApp;
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


            case Identificadores.Atualizacoes.TEMPLATE:

                obterResumoTemplate();
                break;

            case Identificadores.Atualizacoes.CHECKLIST:

                obterResumoChecklist();
                break;


            case Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS:

                obterResumoAtividadesPlaneaveis();
                break;

            default:
                break;

        }
    }







    //---------------------
    //OBTER
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




    //---------------------
    //OBTER - checklist
    //---------------------


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
    //RECARREGAR - registo
    //---------------------



    /**
     * Metodo que permite obter o resumo
     * @param contexto
     * @param idTipo
     * @param descricao
     * @param handlerNotificacoesUI
     */
    public void recarregar(Activity contexto, int idTipo, ResumoTipo descricao, Handler handlerNotificacoesUI) {

        switch(idTipo){

            case Identificadores.Atualizacoes.TIPO:

                recarregarTipo(contexto, descricao, handlerNotificacoesUI);
                break;


            case Identificadores.Atualizacoes.TEMPLATE:


                break;


            case Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS:

                break;

            default:
                break;

        }
    }


    public void recarregar(Activity contexto, ResumoChecklist descricao, Handler handlerNotificacoesUI) {
        //TODO: completar
    }


    /**
     * Metodo que permite atualizar um tipo
     * @param descricao a descricao associada ao tipo
     */
    private void recarregarTipo(Activity contexto, ResumoTipo descricao, Handler handlerNotificacoesUI) {

        showProgressBar(true);

        try {
            tiposRepositorio.obterTipo(descricao)
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
                                    RecarregarTipoAsyncTask servico = new RecarregarTipoAsyncTask(contexto, vvmshBaseDados, handlerNotificacoesUI, carregamentoTiposRepositorio);
                                    servico.execute(iTipoListagems);

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


    //---------------------
    //RECARREGAR
    //---------------------


    /**
     * Metodo que permite recarregar todos os registos
     * @param tipo o identificador
     */
    public void recarregarRegistos(int tipo, Activity activity, Handler handlerNotificacoesUI){

        switch(tipo){

            case Identificadores.Atualizacoes.TIPO:

                recarregarTipos(activity, handlerNotificacoesUI);
                break;

            case Identificadores.Atualizacoes.TEMPLATE:

                recarregarTemplates(activity, handlerNotificacoesUI);
                break;

            case Identificadores.Atualizacoes.CHECKLIST:

                recarregarChecklist(activity, handlerNotificacoesUI);
                break;

            case Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS:

                recarregarAtividadesPlaneaveis(activity, handlerNotificacoesUI);
                break;

            default:
                break;

        }
    }









    /**
     * Metodo que permite recarregar todos os tipos
     */
    private void recarregarTipos(Activity activity, Handler handlerNotificacoesUI){

        showProgressBar(true);

        List<ITipoListagem> respostas = new ArrayList<>();

        try {

            tiposRepositorio.obterTipos()
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

                                    showProgressBar(false);

                                    CarregarTipoAsyncTask servico = new CarregarTipoAsyncTask(activity, vvmshBaseDados, handlerNotificacoesUI, carregamentoTiposRepositorio);
                                    servico.execute(iTipoListagems);
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


    /**
     * Metodo que permite recarregar todos os templates
     */
    private void recarregarTemplates(Activity atividade, Handler handlerNotificacoesUI){

        showProgressBar(true);

        try {
            tiposRepositorio.obterTemplateAvr()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(


                            new SingleObserver<TemplateAvr>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(TemplateAvr templateAvr) {

                                    CarregarTipoTemplatesAvrAsyncTask servico = new CarregarTipoTemplatesAvrAsyncTask(atividade, vvmshBaseDados, handlerNotificacoesUI, tiposRepositorio);
                                    servico.execute(templateAvr);
                                    showProgressBar(false);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    showProgressBar(false);
                                }
                            }
                    );
        } catch (TipoInexistenteException e) {
            showProgressBar(false);
            messagemLiveData.setValue(Recurso.erro(e.getMessage()));
        }
    }


    /**
     * Metodo que permite recarregar todas as checklists
     */
    private void recarregarChecklist(Activity atividade, Handler handlerNotificacoesUI){

        showProgressBar(true);
        List<ITipoChecklist> respostasChecklist = new ArrayList<>();


        tiposRepositorio.obterIdChecklists().toObservable()
                .flatMap(new Function<List<Integer>, ObservableSource<ITipoChecklist>>() {
                    @Override
                    public ObservableSource<ITipoChecklist> apply(List<Integer> ids) throws Exception {
                        return tiposRepositorio.obterChecklists(ids);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<ITipoChecklist>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(ITipoChecklist o) {
                                respostasChecklist.add(o);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                formatarErro(e);
                            }

                            @Override
                            public void onComplete() {
                                CarregarTipoChecklistAsyncTask servico = new CarregarTipoChecklistAsyncTask(atividade, vvmshBaseDados, handlerNotificacoesUI, tiposRepositorio);
                                servico.execute(respostasChecklist);

                                showProgressBar(false);
                            }
                        }

                );
    }



    /**
     * Metodo que permite recarregar todas as atividades planeaveis
     */
    private void recarregarAtividadesPlaneaveis(Activity atividade, Handler handlerNotificacoesUI){

        showProgressBar(true);

        List<ITipoListagem> respostas = new ArrayList<>();

        try {
            tiposRepositorio.obterAtividadesPlaneaveis()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<ITipoAtividadePlaneavelListagem>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(ITipoAtividadePlaneavelListagem iTipoAtividadePlaneavelListagem) {
                                    CarregarTipoAtividadesPlaneaveisAsyncTask servico = new CarregarTipoAtividadesPlaneaveisAsyncTask(atividade, vvmshBaseDados, handlerNotificacoesUI, tiposRepositorio);
                                    servico.execute(iTipoAtividadePlaneavelListagem);

                                    showProgressBar(false);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    showProgressBar(false);
                                }
                            }
                    );
        } catch (TipoInexistenteException e) {
            showProgressBar(false);
            messagemLiveData.setValue(Recurso.erro(e.getMessage()));
        }


    }




    //------------------
    //AtualizacaoDao
    //------------------


    /**
     * Metodo que permite obter a atualizacao da app
     * @param idUtilizador o identificador do utilizador
     */
    public void obterAtualizacao(String idUtilizador) {

        showProgressBar(true);

        versaoAppRepositorio.obterAtualizacao()
                .map(new Function<VersaoApp, VersaoApp>() {
                    @Override
                    public VersaoApp apply(VersaoApp versaoApp) throws Exception {

                        versaoApp.fixarUtilizador(idUtilizador);
                        return versaoApp;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<VersaoApp>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(VersaoApp resposta) {
                                versaoApp.setValue(resposta);
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
     * Metodo que inicia o download de um apk
     * @param contexto
     * @param handler
     */
    public void downloadApp(Context contexto, Handler handler) {

        DownloadApkAsyncTask servico = new DownloadApkAsyncTask(contexto, handler);
        servico.execute(versaoApp.getValue());
    }


    /**
     * Metodo que inicia a instalação de um apk
     * @param contexto
     * @param handler
     */
    public void instalarApp(Context contexto, Handler handler) {

        InstalarApkAsyncTask servico = new InstalarApkAsyncTask(contexto, handler);
        servico.execute(versaoApp.getValue());
    }
}
