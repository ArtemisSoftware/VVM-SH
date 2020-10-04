package com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.PropostaPlanoAcaoResultado;
import com.vvm.sh.repositorios.PropostaPlanoAcaoRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos.Proposta;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PropostaPlanoAcaoViewModel extends BaseViewModel {

    private final PropostaPlanoAcaoRepositorio propostaPlanoAcaoRepositorio;


    public MutableLiveData<List<Proposta>> condicoes;

    public MutableLiveData<List<Proposta>> medidas;


    @Inject
    public PropostaPlanoAcaoViewModel(PropostaPlanoAcaoRepositorio propostaPlanoAcaoRepositorio){

        this.propostaPlanoAcaoRepositorio = propostaPlanoAcaoRepositorio;
        condicoes = new MutableLiveData<>();
        medidas = new MutableLiveData<>();
    }



    public MutableLiveData<List<Proposta>> observarCondicoesSt(){
        return condicoes;
    }

    public MutableLiveData<List<Proposta>> observarMedidasAvaliacao(){
        return medidas;
    }






    public void selecionarTudo(int idAtividade, boolean selecionado){

        propostaPlanoAcaoRepositorio.selecionarTudo(idAtividade, selecionado)
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

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }


    public void selecionar(int idAtividade, int id, boolean selecionado){

        propostaPlanoAcaoRepositorio.selecionar(idAtividade, id, selecionado)
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

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }



    //-------------------
    //OBTER
    //-------------------


    public void obterPropostas(int idAtividade){

        showProgressBar(true);

        propostaPlanoAcaoRepositorio.obterCondicoesSt(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<Proposta>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Proposta> propostas) {
                                condicoes.setValue(propostas);
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


        propostaPlanoAcaoRepositorio.obterMedidasAvaliacao(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<Proposta>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Proposta> propostas) {
                                medidas.setValue(propostas);
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


}
