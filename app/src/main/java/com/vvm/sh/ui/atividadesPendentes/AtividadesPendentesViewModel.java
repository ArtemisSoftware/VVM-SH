package com.vvm.sh.ui.atividadesPendentes;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.ProcessoProdutivoResultado;
import com.vvm.sh.repositorios.AtividadePendenteRepositorio;
import com.vvm.sh.servicos.ResultadoAsyncTask;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AtividadesPendentesViewModel extends BaseViewModel {

    private final AtividadePendenteRepositorio atividadePendenteRepositorio;

    public MutableLiveData<List<AtividadePendenteRegisto>> atividades;
    public MutableLiveData<AtividadePendenteRegisto> atividade;

    public MutableLiveData<List<Tipo>> tiposAnomalias;


    @Inject
    public AtividadesPendentesViewModel(AtividadePendenteRepositorio atividadePendenteRepositorio){

        this.atividadePendenteRepositorio = atividadePendenteRepositorio;
        atividades = new MutableLiveData<>();
        atividade = new MutableLiveData<>();
        tiposAnomalias = new MutableLiveData<>();
    }


    //------------------
    //GRAVAR
    //------------------


    /**
     * Metodo que permite gravar uma atividade
     * @param idTarefa o identificador da tarefa
     * @param atividade os dados da atividade
     */
    public void gravarAtividade(int idTarefa, AtividadePendenteResultado atividade) {

        if(this.atividade.getValue().resultado == null){

            atividadePendenteRepositorio.inserir(atividade).toObservable()
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
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
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
            atividadePendenteRepositorio.atualizar(atividade).toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onNext(Integer aLong) {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
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

        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, atividadePendenteRepositorio.resultadoDao);
        servico.execute(new Resultado(idTarefa, ResultadoId.ATIVIDADE_PENDENTE));


    }



    public void gravarAtividade(ProcessoProdutivoResultado registo) {



    }
    //------------------
    //OBTER
    //------------------



    /**
     * Metodo que permite obter as atividades pendentes
     * @param idTarefa o identificador da tarefa
     */
    public void obterAtividades(int idTarefa){

        showProgressBar(true);

        atividadePendenteRepositorio.obterAtividades(idTarefa).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<AtividadePendenteRegisto>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<AtividadePendenteRegisto> resultado) {

                                atividades.setValue(resultado);
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
     * Metodo que permite obter uma atividade pendente
     * @param id o identificador da atividade
     */
    public void obterAtividade(int id){

        obterTipos();

        atividadePendenteRepositorio.obterAtividadeResultado(id).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<AtividadePendenteRegisto>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(AtividadePendenteRegisto registo) {
                                atividade.setValue(registo);
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



    /**
     * Metodo que permite obter os tipos
     */
    private void obterTipos(){

        showProgressBar(true);

        atividadePendenteRepositorio.obterTiposAnomalias().toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Tipo> registos) {

                                tiposAnomalias.setValue(registos);
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


    public void obterProcessoProdutivo(int anInt) {
    }


}
