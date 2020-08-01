package com.vvm.sh.ui.atividadesPendentes;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.AtividadePendenteRepositorio;
import com.vvm.sh.servicos.ResultadoAsyncTask;
import com.vvm.sh.ui.agenda.modelos.Resultado;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
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
    public MutableLiveData<AtividadePendenteResultado> atividadeResultado;

    public MutableLiveData<List<Tipo>> tiposAnomalias;


    @Inject
    public AtividadesPendentesViewModel(AtividadePendenteRepositorio atividadePendenteRepositorio){

        this.atividadePendenteRepositorio = atividadePendenteRepositorio;
        atividades = new MutableLiveData<>();
        atividadeResultado = new MutableLiveData<>();
        tiposAnomalias = new MutableLiveData<>();
    }


    //------------------
    //GRAVAR
    //------------------


    public void gravarAtividade(int idTarefa, AtividadePendenteResultado atividade) {

        if(atividadeResultado.getValue() == null){

            atividadePendenteRepositorio.inserir(atividade).toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Long>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Long aLong) {

                                    messagemLiveData.setValue(Recurso.successo());
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
        else{
            atividadePendenteRepositorio.atualizar(atividade).toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Integer aLong) {

                                    messagemLiveData.setValue(Recurso.successo());
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

        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, atividadePendenteRepositorio.resultadoDao);
        servico.execute(new Resultado(idTarefa, ResultadoId.ATIVIDADE_PENDENTE));


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

                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }

                );

    }



    public void obterAtividade(int id){

        obterTipos();

        atividadePendenteRepositorio.obterAtividadeResultado(id).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<AtividadePendenteResultado>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(AtividadePendenteResultado registo) {
                                atividadeResultado.setValue(registo);
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

                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }

                );

    }





}
