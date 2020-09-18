package com.vvm.sh.ui.quadroPessoal;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.ColaboradorResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.QuadroPessoalRepositorio;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class QuadroPessoalViewModel extends BaseViewModel {


    private final QuadroPessoalRepositorio quadroPessoalRepositorio;

    public MutableLiveData<List<ColaboradorRegisto>> colaboradores;
    public MutableLiveData<ColaboradorRegisto> colaborador;
    public MutableLiveData<List<Morada>> moradas;
    public MutableLiveData<List<Tipo>> generos;

    @Inject
    public QuadroPessoalViewModel(QuadroPessoalRepositorio quadroPessoalRepositorio){

        this.quadroPessoalRepositorio = quadroPessoalRepositorio;
        colaboradores = new MutableLiveData<>();
        colaborador = new MutableLiveData<>();
        moradas = new MutableLiveData<>();
        generos = new MutableLiveData<>();
    }


    //--------------------
    //GRAVAR
    //--------------------

    public void gravar(int idResultado, ColaboradorResultado resultado) {


        if(idResultado == 0) {

            quadroPessoalRepositorio.inserir(resultado)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Long>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Long aLong) {

                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                                    gravarResultado(quadroPessoalRepositorio.resultadoDao, resultado.idTarefa, ResultadoId.QUADRO_PESSOAL);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }

                    );
        }
        else{

            resultado.id = idResultado;

            quadroPessoalRepositorio.atualizar(resultado)
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
                                    gravarResultado(quadroPessoalRepositorio.resultadoDao, resultado.idTarefa, ResultadoId.QUADRO_PESSOAL);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }

                    );
        }
    }




    //--------------------
    //OBTER
    //--------------------


    /**
     * Metodo que permite obter o quadro pessoal
     * @param idTarefa o identificador da tarefa
     */
    public void obterQuadroPessoal(int idTarefa){

        showProgressBar(true);

        quadroPessoalRepositorio.obterQuadroPessoal(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<ColaboradorRegisto>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<ColaboradorRegisto> resultados) {
                                colaboradores.setValue(resultados);
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


    public void obterColaborador(int idTarefa){


        quadroPessoalRepositorio.obterMoradas(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<List<Morada>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<Morada> registos) {
                                obterGeneros(generos);
                                moradas.setValue(registos);
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
     * Metodo que permite obter moradas
     * @param idTarefa o identificador da tarefa
     * @param id o identificador do colaborador
     */
    public void obterColaborador(int idTarefa, int id) {

        Maybe.zip(quadroPessoalRepositorio.obterColaborador(id), quadroPessoalRepositorio.obterMoradas(idTarefa),
                new BiFunction<ColaboradorRegisto, List<Morada>, QuadroPessoal>() {
            @Override
            public QuadroPessoal apply(ColaboradorRegisto colaboradorRegisto, List<Morada> moradas) throws Exception {

                QuadroPessoal quadroPessoal = new QuadroPessoal();
                quadroPessoal.moradas = moradas;
                quadroPessoal.registo = colaboradorRegisto;

                return quadroPessoal;
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(

                new MaybeObserver<QuadroPessoal>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(QuadroPessoal quadroPessoal) {

                        obterGeneros(generos);
                        colaborador.setValue(quadroPessoal.registo);
                        moradas.setValue(quadroPessoal.moradas);
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



    private class QuadroPessoal{

        List<Morada> moradas;
        ColaboradorRegisto registo;

    }
}
