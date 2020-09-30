package com.vvm.sh.ui.opcoes;

import android.content.Context;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.pedido.ITipoChecklist;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.VersaoApp;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.VersaoAppRepositorio;
import com.vvm.sh.servicos.CarregarTipoChecklistAsyncTask;
import com.vvm.sh.servicos.DownloadApkAsyncTask;
import com.vvm.sh.servicos.InstalarApkAsyncTask;
import com.vvm.sh.servicos.AtualizarTipoAsyncTask;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.ui.opcoes.modelos.TemplateAvr;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class OpcoesViewModel extends BaseViewModel {


    private final VersaoAppRepositorio versaoAppRepositorio;
    private final TiposRepositorio tiposRepositorio;
    public MutableLiveData<VersaoApp> versaoApp;
    public MutableLiveData<List<ResumoTipo>> tipos;


    @Inject
    public OpcoesViewModel(VersaoAppRepositorio versaoAppRepositorio, TiposRepositorio tiposRepositorio){

        this.versaoAppRepositorio = versaoAppRepositorio;
        this.tiposRepositorio = tiposRepositorio;

        versaoApp = new MutableLiveData<>();
        tipos = new MutableLiveData<>();
    }


    public MutableLiveData<VersaoApp> observarVersaoApp(){
        return versaoApp;
    }


    //---------------------
    //Tipos
    //---------------------


    /**
     * Metodo que permite obter um resumo dos tipos existentes
     */
    public void obterTipos(){

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
     * Metodo que permite atualizar um tipo
     * @param descricao a descricao associada ao tipo
     */
    public void atualizarTipo(String descricao) {

        showProgressBar(true);

        List<ITipoListagem> respostas = new ArrayList<>();


        try {
            tiposRepositorio.obterTipo(descricao)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Observer<ITipoListagem>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onNext(ITipoListagem iTipoListagem) {
                                    respostas.add(iTipoListagem);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    showProgressBar(false);
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }

                                @Override
                                public void onComplete() {
                                    AtualizarTipoAsyncTask servico = new AtualizarTipoAsyncTask(vvmshBaseDados, tiposRepositorio);
                                    servico.execute(respostas);

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
     * Metodo que permite recarregar todos os tipos
     */
    public void recarregarTipos(){

        showProgressBar(true);

        List<ITipoListagem> respostas = new ArrayList<>();


        try {
            tiposRepositorio.obterTipos()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Observer<ITipoListagem>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onNext(ITipoListagem iTipoListagem) {
                                    respostas.add(iTipoListagem);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    showProgressBar(false);
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }

                                @Override
                                public void onComplete() {
                                    AtualizarTipoAsyncTask servico = new AtualizarTipoAsyncTask(vvmshBaseDados, tiposRepositorio);
                                    servico.execute(respostas);

                                    showProgressBar(false);
                                }
                            }

                    );
        } catch (TipoInexistenteException e) {
            showProgressBar(false);
            messagemLiveData.setValue(Recurso.erro(e.getMessage()));
        }


        //TODO: na primeira chamada isto pode dar problemas. rever
/*
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

                            }

                            @Override
                            public void onNext(ITipoChecklist o) {
                                respostasChecklist.add(o);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                CarregarTipoChecklistAsyncTask servico = new CarregarTipoChecklistAsyncTask(vvmshBaseDados, tiposRepositorio);
                                servico.execute(respostasChecklist);

                                showProgressBar(false);
                            }
                        }

                );

*/

        /*templates*/
        /*
        try {
        tiposRepositorio.obterTemplateAvr()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new SingleObserver<TemplateAvr>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(TemplateAvr templateAvr) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
        } catch (TipoInexistenteException e) {
            showProgressBar(false);
            messagemLiveData.setValue(Recurso.erro(e.getMessage()));
        }
        */
    }


    //------------------
    //AtualizacaoDao
    //------------------



    /**
     * Metodo que permite obter a atualizacao da api
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
