package com.vvm.sh.ui.opcoes;

import android.content.Context;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.api.modelos.VersaoApp;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.VersaoAppRepositorio;
import com.vvm.sh.servicos.ServicoDownloadApk;
import com.vvm.sh.servicos.ServicoInstalacaoApk;
import com.vvm.sh.ui.opcoes.modelos.Colecao;
import com.vvm.sh.servicos.AtualizarTipoAsyncTask;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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

        tiposRepositorio.obterResumoTipos().toObservable()
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
     * @param metodo o metodo associado ao tipo
     */
    public void atualizarTipo(String metodo) {

        showProgressBar(true);

        tiposRepositorio.obterTipo(metodo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<TipoResposta>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(TipoResposta tipoResposta) {

                                List<TipoResposta> respostas = new ArrayList<>();
                                respostas.add(tipoResposta);

                                AtualizarTipoAsyncTask servico = new AtualizarTipoAsyncTask(vvmshBaseDados, tiposRepositorio);
                                servico.execute(respostas);

                                showProgressBar(false);
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.TIPO_ATUALIZADO_SUCESSO + metodo));
                            }

                            @Override
                            public void onError(Throwable e) {

                                showProgressBar(false);
                            }
                        }
                );
    }


    /**
     * Metodo que permite recarregar todos os tipos
     */
    public void recarregarTipos(){

        showProgressBar(true);

        List<TipoResposta> respostas = new ArrayList<>();
        List<Observable<TipoResposta>> pedidos = new ArrayList<>();

        for(String metodo : TiposConstantes.MetodosTipos.TIPOS){
            pedidos.add(tiposRepositorio.obterTipo(metodo).toObservable());
        }

        Observable.concat(pedidos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<TipoResposta>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(TipoResposta tipoResposta) {
                                respostas.add(tipoResposta);
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

    }


    //------------------
    //AtualizacaoDao
    //------------------



    /**
     * Metodo que permite obter a atualizacao da app
     */
    public void obterAtualizacao() {

        showProgressBar(true);

        versaoAppRepositorio.obterAtualizacao()
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
                                //TODO: o id do utilizador deve ser passado de outra forma
                                resposta.fixarUtilizador("500005");
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

        ServicoDownloadApk servico = new ServicoDownloadApk(contexto, handler, versaoApp.getValue());
        servico.execute();
    }


    /**
     * Metodo que inicia a instalação de um apk
     * @param contexto
     * @param handler
     */
    public void instalarApp(Context contexto, Handler handler) {

        ServicoInstalacaoApk servico = new ServicoInstalacaoApk(contexto, handler, versaoApp.getValue());
        servico.execute();
    }
}
