package com.vvm.sh.ui.atividadesPendentes.relatorios;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.repositorios.FormacaoRepositorio;
import com.vvm.sh.servicos.ResultadoAsyncTask;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Module
public class FormacaoViewModel extends BaseViewModel {

    private final FormacaoRepositorio formacaoRepositorio;


    public MutableLiveData<lolo> gogo;

    public MutableLiveData<AcaoFormacao> acaoFormacao;
    public MutableLiveData<List<Formando>> formandos;

    public MutableLiveData<Formando> formando;

    public MutableLiveData<List<Tipo>> generos;
    public MutableLiveData<List<Tipo>> tiposCursos;

    @Inject
    public FormacaoViewModel(FormacaoRepositorio formacaoRepositorio){

        this.formacaoRepositorio = formacaoRepositorio;
        formandos = new MutableLiveData<>();
        formando = new MutableLiveData<>();
        acaoFormacao = new MutableLiveData<>();
        generos = new MutableLiveData<>();
        tiposCursos = new MutableLiveData<>();
        gogo = new MutableLiveData<>();
    }



    //--------------------
    //GRAVAR
    //--------------------


    /**
     * Metodo que permite gravar uma acao de formacao
     * @param idTarefa o identificador da tarefa
     * @param registo os dados da acao de formacao
     */
    public void gravar(int idTarefa, AcaoFormacaoResultado registo) {

        Observable<Integer> removerAtividade = formacaoRepositorio.removerAtividade(registo.idAtividade).toObservable();

        if(acaoFormacao.getValue() == null){

            Observable<Long> inserirAcaoFormacao = formacaoRepositorio.inserirAcaoFormacao(registo).toObservable();

            Observable<Object> observable = Observable.zip(inserirAcaoFormacao, removerAtividade, new BiFunction<Long, Integer, Object>() {
                @Override
                public Object apply(Long aLong, Integer integer) throws Exception {
                    return new Object();
                }
            });


            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(Object o) {
                        messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                    }

                    @Override
                    public void onError(Throwable e) {
                        messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        }
        else{
            //editar

            Observable<Integer> atualizarAcaoFormacao = formacaoRepositorio.atualizarAcaoFormacao(registo).toObservable();

//            Observable<Object> observable = Observable.zip(atualizarAcaoFormacao, removerAtividade, new BiFunction<Integer, Integer, Object>() {
//                @Override
//                public Object apply(Integer aLong, Integer integer) throws Exception {
//                    return new Object();
//                }
//            });


            Observable.zip(atualizarAcaoFormacao, removerAtividade, new BiFunction<Integer, Integer, Object>() {
                @Override
                public Object apply(Integer aLong, Integer integer) throws Exception {
                    return new Object();
                }
            })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(Object o) {
                        messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                    }

                    @Override
                    public void onError(Throwable e) {
                        messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        }


        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, formacaoRepositorio.resultadoDao);
        servico.execute(new Resultado(idTarefa, ResultadoId.ATIVIDADE_PENDENTE));
    }


    /**
     * Metodo que permite gravar um formando
     * @param idTarefa o identificador da tarefa
     * @param registo os dados do formando
     * @param imagem a imagem da assinatura
     */
    public void gravar(int idTarefa, FormandoResultado registo, byte [] imagem) {

        if(formando.getValue() != null){
            registo.selecionado = formando.getValue().resultado.selecionado;


            if(imagem == null & formando.getValue().assinatura != null) {
                imagem = formando.getValue().assinatura;
            }
        }

        byte[] assinatura = imagem;

        if(registo.id == 0) {

            formacaoRepositorio.inserirFormando(registo).toObservable()
                    .flatMap(new Function<Long, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Long aLong) throws Exception {

                            int idFormando = (int) (long) aLong;

                            List<Observable<? extends Number>> observables = new ArrayList<>();
                            observables.add(formacaoRepositorio.removerAssinatura(idFormando).toObservable());

                            if(assinatura != null) {
                                ImagemResultado imagemResultado = new ImagemResultado(idTarefa, idFormando, Identificadores.Imagens.IMAGEM_ASSINATURA_FORMANDO, assinatura);
                                observables.add(formacaoRepositorio.inserirAssinatura(imagemResultado).toObservable());
                            }

                            return Observable.merge(observables);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Object>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onNext(Object o) {
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

            formacaoRepositorio.atualizarFormando(registo).toObservable()
                    .flatMap(new Function<Integer, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Integer aLong) throws Exception {

                            int idFormando = registo.id;


                            List<Observable<? extends Number>> observables = new ArrayList<>();
                            observables.add(formacaoRepositorio.removerAssinatura(idFormando).toObservable());

                            if(assinatura != null) {
                                ImagemResultado imagemResultado = new ImagemResultado(idTarefa, idFormando, Identificadores.Imagens.IMAGEM_ASSINATURA_FORMANDO, assinatura);
                                observables.add(formacaoRepositorio.inserirAssinatura(imagemResultado).toObservable());
                            }

                            return Observable.merge(observables);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Object>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onNext(Object o) {
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


        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, formacaoRepositorio.resultadoDao);
        servico.execute(new Resultado(idTarefa, ResultadoId.ATIVIDADE_PENDENTE));
    }


    /**
     * Metodo que permite gravar um formando
     * @param idTarefa o identificador da tarefa
     * @param registo os dados do formando
     */
    public void gravar(int idTarefa, FormandoResultado registo){


        Observable<Integer> removerAtividade = formacaoRepositorio.removerAtividade(registo.idAtividade).toObservable();

        if(registo.id == 0){

            Observable<Long> inserirFormando = formacaoRepositorio.inserirFormando(registo).toObservable();

            Observable<Object> observable = Observable.zip(inserirFormando, removerAtividade, new BiFunction<Long, Integer, Object>() {
                @Override
                public Object apply(Long aLong, Integer integer) throws Exception {
                    return new Object();
                }
            });

            observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposables.add(d);
                        }

                        @Override
                        public void onNext(Object o) {
                            messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                        }

                        @Override
                        public void onError(Throwable e) {
                            messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        else{
            //editar

            Observable<Integer> atualizarFormando = formacaoRepositorio.atualizarFormando(registo).toObservable();


            Observable.zip(atualizarFormando, removerAtividade, new BiFunction<Integer, Integer, Object>() {
                @Override
                public Object apply(Integer aLong, Integer integer) throws Exception {
                    return new Object();
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Object>() {
                @Override
                public void onSubscribe(Disposable d) {
                    disposables.add(d);
                }

                @Override
                public void onNext(Object o) {
                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                }

                @Override
                public void onError(Throwable e) {
                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                }

                @Override
                public void onComplete() {

                }
            });

        }


    ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, formacaoRepositorio.resultadoDao);
    servico.execute(new Resultado(idTarefa, ResultadoId.ATIVIDADE_PENDENTE));

}



    //--------------------
    //OBTER
    //--------------------


    /**
     * Metodo que permite obter os formandos
     * @param idAtividade o identificador da atividade
     */
    public void obterFormacao(int idAtividade){


//        Observable.zip(formacaoRepositorio.obterAcaoFormacao(idAtividade).toObservable(),
//                formacaoRepositorio.obterFormandos(idAtividade).toObservable(),
//                new BiFunction<AcaoFormacao, List<Formando>, lolo>() {
//                    @Override
//                    public lolo apply(AcaoFormacao acaoFormacao, List<Formando> formandos) throws Exception {
//
//                        lolo bobo = new lolo();
//                        bobo.acaoFormacao = acaoFormacao;
//                        bobo.formandos = formandos;
//                        return bobo;
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//
//                        new Observer<lolo>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(lolo o) {
//                                gogo.setValue(o);
//                                acaoFormacao.setValue(o.acaoFormacao);
//                                formandos.setValue(o.formandos);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        }
//                );


        obterAcaoFormacaoAtividade(idAtividade);

        showProgressBar(true);

        formacaoRepositorio.obterFormandos(idAtividade).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<Formando>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Formando> resultado) {

                                formandos.setValue(resultado);
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
     * Metodo que permite obter a acao de formacao
     * @param idAtividade o identificador da atividade
     */
    public void obterAcaoFormacao(int idAtividade) {

        obterCursos();
        obterAcaoFormacaoAtividade(idAtividade);
    }


    /**
     * Metodo que permite obter os dados de um formando
     * @param idFormando o identificador do formando
     */
    public void obterFormando(int idFormando) {

        obterGeneros();

        showProgressBar(true);


        formacaoRepositorio.obterFormando(idFormando)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<Formando>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Formando resultado) {
                                formando.setValue(resultado);
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
/*
                        new Observer<Formando>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(Formando resultado) {

                                formando.setValue(resultado);
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
*/
                );

    }


    /**
     * Metodo que permite obter a acao de formacao
     * @param idAtividade o identificador da atividade
     */
    private void obterAcaoFormacaoAtividade(int idAtividade) {

        formacaoRepositorio.obterAcaoFormacao(idAtividade).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<AcaoFormacao>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(AcaoFormacao resultado) {
                                acaoFormacao.setValue(resultado);
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
    private void obterCursos(){

        showProgressBar(true);

        formacaoRepositorio.obterCursos().toObservable()
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

                                tiposCursos.setValue(registos);
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
    //MISC
    //---------------------


    /**
     * Metodo que permite obter os generos
     */
    private void obterGeneros(){

        List<Tipo> estado = new ArrayList<>();

        estado.add(TiposConstantes.GENERO_MASCULINO);
        estado.add(TiposConstantes.GENERO_FEMININO);
        generos.setValue(estado);

    }


}
