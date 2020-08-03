package com.vvm.sh.ui.atividadesPendentes.relatorios;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.repositorios.FormacaoRepositorio;
import com.vvm.sh.servicos.ResultadoAsyncTask;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Module;
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

    public MutableLiveData<List<Formando>> formandos;
    public MutableLiveData<Formando> formando;

    public MutableLiveData<AcaoFormacao> acaoFormacao;
    public MutableLiveData<Boolean> completudeFormacao;
    public MutableLiveData<List<Tipo>> generos;

    @Inject
    public FormacaoViewModel(FormacaoRepositorio formacaoRepositorio){

        this.formacaoRepositorio = formacaoRepositorio;
        formandos = new MutableLiveData<>();
        formando = new MutableLiveData<>();
        acaoFormacao = new MutableLiveData<>();
        generos = new MutableLiveData<>();
        completudeFormacao = new MutableLiveData<>();
    }



    //--------------------
    //GRAVAR
    //--------------------

    


    public void gravar(int idTarefa, AcaoFormacaoResultado registo) {

        Observable<Integer> atividade = formacaoRepositorio.removerAtividade(registo.idAtividade).toObservable();

        if(acaoFormacao.getValue() == null){
            //inserir

            Observable<Long> accao = formacaoRepositorio.inserirAcaoFormacao(registo).toObservable();


            Observable.zip(accao, atividade, new BiFunction<Long, Integer, Object>() {
                @Override
                public Object apply(Long aLong, Integer integer) throws Exception {
                    return new Object();
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Object>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Object o) {
                    messagemLiveData.setValue(Recurso.successo());
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
        else{
            //editar

            Observable<Integer> accao = formacaoRepositorio.atualizarAcaoFormacao(registo).toObservable();


            Observable.zip(accao, atividade, new BiFunction<Integer, Integer, Object>() {
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

                }

                @Override
                public void onNext(Object o) {
                    messagemLiveData.setValue(Recurso.successo());
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });

        }


        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, formacaoRepositorio.resultadoDao);
        servico.execute(new Resultado(idTarefa, ResultadoId.ATIVIDADE_PENDENTE));
    }



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


                            List<Observable<? extends Number>> ddd = new ArrayList<>();
                            ddd.add(formacaoRepositorio.removerAssinatura(idFormando).toObservable());

                            if(assinatura != null) {
                                ImagemResultado imagemResultado = new ImagemResultado(idTarefa, idFormando, Identificadores.Imagens.IMAGEM_ASSINATURA_FORMANDO, assinatura);
                                ddd.add(formacaoRepositorio.inserirAssinatura(imagemResultado).toObservable());
                            }

                            return Observable.merge(ddd);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Object>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Object o) {
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

            formacaoRepositorio.atualizarFormando(registo).toObservable()
                    .flatMap(new Function<Integer, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Integer aLong) throws Exception {

                            int idFormando = aLong;


                            List<Observable<? extends Number>> ddd = new ArrayList<>();
                            ddd.add(formacaoRepositorio.removerAssinatura(idFormando).toObservable());

                            if(assinatura != null) {
                                ImagemResultado imagemResultado = new ImagemResultado(idTarefa, idFormando, Identificadores.Imagens.IMAGEM_ASSINATURA_FORMANDO, assinatura);
                                ddd.add(formacaoRepositorio.inserirAssinatura(imagemResultado).toObservable());
                            }

                            return Observable.merge(ddd);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<Object>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Object o) {
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


        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, formacaoRepositorio.resultadoDao);
        servico.execute(new Resultado(idTarefa, ResultadoId.ATIVIDADE_PENDENTE));
    }


    public void gravar(int idTarefa, FormandoResultado registo){


        Observable<Integer> atividade = formacaoRepositorio.removerAtividade(registo.idAtividade).toObservable();

        if(registo.id == 0){
            //inserir


            Observable<Long> inserir = formacaoRepositorio.inserirFormando(registo).toObservable();






            Observable.zip(inserir, atividade, new BiFunction<Long, Integer, Object>() {
                @Override
                public Object apply(Long aLong, Integer integer) throws Exception {
                    return new Object();
                }
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Object o) {
                            messagemLiveData.setValue(Recurso.successo());
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        else{
            //editar

            Observable<Integer> atualizar = formacaoRepositorio.atualizarFormando(registo).toObservable();


            Observable.zip(atualizar, atividade, new BiFunction<Integer, Integer, Object>() {
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

                        }

                        @Override
                        public void onNext(Object o) {
                            messagemLiveData.setValue(Recurso.successo());
                        }

                        @Override
                        public void onError(Throwable e) {

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

        completudeFormacao.setValue(false);

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

                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }

                );



        formacaoRepositorio.obterValidadeFormacao(idAtividade).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(Integer resultado) {

                                completudeFormacao.setValue(resultado > 0);
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


    public void obterAcaoFormacao(int idAtividade) {

        obterCursos();

        obterAcaoFormacaoAtividade(idAtividade);
    }



    public void obterFormando(int idFormando) {

        obterGeneros();

        showProgressBar(true);


        formacaoRepositorio.obterFormando(idFormando).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

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

                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }

                );

    }









    private void obterAcaoFormacaoAtividade(int idAtividade) {

        formacaoRepositorio.obterAcaoFormacao(idAtividade).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<AcaoFormacao>() {
                            @Override
                            public void onSubscribe(Disposable d) {

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

                                generos.setValue(registos);
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





    private void obterGeneros(){

        List<Tipo> estado = new ArrayList<>();

        estado.add(TiposConstantes.GENERO_MASCULINO);
        estado.add(TiposConstantes.GENERO_FEMININO);
        generos.setValue(estado);

    }








}
