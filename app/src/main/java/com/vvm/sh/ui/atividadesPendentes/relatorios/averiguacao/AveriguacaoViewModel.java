package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.RelatorioAveriguacaoResultado;
import com.vvm.sh.repositorios.AveriguacaoRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.Averiguacao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.AveriguacaoRegisto;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AveriguacaoViewModel extends BaseViewModel {

    private final AveriguacaoRepositorio averiguacaoRepositorio;


    public MutableLiveData<List<Averiguacao>> relatorio;
    public MutableLiveData<List<AveriguacaoRegisto>> averiguacoes;
    public MutableLiveData<AveriguacaoRegisto> averiguacao;

    public AveriguacaoViewModel(AveriguacaoRepositorio averiguacaoRepositorio) {
        this.averiguacaoRepositorio = averiguacaoRepositorio;

        relatorio = new MutableLiveData<>();
        averiguacoes = new MutableLiveData<>();
        averiguacao = new MutableLiveData<>();
    }



    public void gravar(RelatorioAveriguacaoResultado registo) {

        if(averiguacao == null){

            averiguacaoRepositorio.inserir(registo)
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

            averiguacaoRepositorio.atualizar(registo)
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

    }



    //-------------------
    //OBTER
    //-------------------


    public void obterRelatorio(int idTarefa, int tipo){

        showProgressBar(true);

        averiguacaoRepositorio.obterRelatorio(idTarefa, tipo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<Averiguacao>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Averiguacao> registos) {
                                relatorio.setValue(registos);
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


    public void obterRegistos(int idRelatorio){

        showProgressBar(true);

        averiguacaoRepositorio.obterRegistos(idRelatorio)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<AveriguacaoRegisto>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<AveriguacaoRegisto> registos) {
                                averiguacoes.setValue(registos);
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

    public void obterRegisto(int id) {

        averiguacaoRepositorio.obterRegisto(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<AveriguacaoRegisto>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(AveriguacaoRegisto registo) {
                                averiguacao.setValue(registo);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );


    }

}
