package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.AvaliacaoAmbientalRepositorio;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.schedulers.Schedulers;

public class AvaliacaoAmbientalViewModel extends BaseViewModel {

    private final AvaliacaoAmbientalRepositorio avaliacaoAmbientalRepositorio;


    public MutableLiveData<RelatorioAmbientalResultado> geral;
    public MutableLiveData<List<AvaliacaoAmbientalResultado>> avaliacoes;


    public MutableLiveData<List<Tipo>> generos;
    public MutableLiveData<List<Tipo>> nebulosidades;

    public MutableLiveData<AvaliacaoAmbientalResultado> avaliacao;
    //--public MutableLiveData<CategoriaProfissionalResultado> categoriasProfissionais;


    @Inject
    public AvaliacaoAmbientalViewModel(AvaliacaoAmbientalRepositorio avaliacaoAmbientalRepositorio){

        this.avaliacaoAmbientalRepositorio = avaliacaoAmbientalRepositorio;
        geral = new MutableLiveData<>();
        avaliacoes = new MutableLiveData<>();
        avaliacao = new MutableLiveData<>();

        generos = new MutableLiveData<>();
        nebulosidades = new MutableLiveData<>();
        //---categoriasProfissionais = new MutableLiveData<>();
    }


    /**
     * Metodo que permite gravar um registo
     * @param idRelatorio o identificador do relatorio
     * @param registo os dados a gravar
     */
    public void gravar(int idRelatorio, RelatorioAmbientalResultado registo) {

        if(idRelatorio == 0){

            avaliacaoAmbientalRepositorio.inserir(registo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Long>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(Long aLong) {
                                    messagemLiveData.setValue(Recurso.successo(aLong, Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                }

                                @Override
                                public void onError(Throwable e) {
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }
                            }
                    );
        }
        else{
            registo.id = idRelatorio;
            avaliacaoAmbientalRepositorio.atualizar(registo)
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
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }
                            }
                    );
        }
    }


    public void gravar(AvaliacaoAmbientalResultado registo, List<Integer> categoriasProfissionais, boolean nivel) {
/*
        if(idAvaliacao.equals(AppIF.SEM_DADOS) == true){
            idAvaliacao = inserirAvaliacao(idRelatorio, idArea, anexoArea, nome,
                    sexo, idTipoIluminacao, emedioLx, idElx, elxArea, elx,
                    homens, mulheres, temperatura, humidadeRelativa);
        }
        else{
            atualizarAvaliacao(idAvaliacao, idArea, anexoArea, nome,
                    sexo, idTipoIluminacao, emedioLx, idElx, elxArea, elx,
                    homens, mulheres, temperatura, humidadeRelativa);
        }


        gravarCategoriasProfissionais_(Integer.parseInt(origem), idAvaliacao, categoriasProfissionais, homens, mulheres);


        if(nivel == true){
            removerMedidas(idAvaliacao);
        }

        abaterAtividadePendente(idAtividade);
        gravarResultado();
*/
    }

    public void gravar(AvaliacaoAmbientalResultado registo, List<Integer> categoriasProfissionais, boolean nivelHumidade, boolean nivelTemperatura) {
    }


    //--------------------
    //OBTER
    //--------------------


    /**
     * Metodo que permite obter a validade do relatorio
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     */
    public void obterValidadeRelatorio(int idAtividade, int tipo) {

        Observable<Object> observables = Observable.zip(
                avaliacaoAmbientalRepositorio.obterIdRelatorio(idAtividade, tipo).toObservable(),
                avaliacaoAmbientalRepositorio.obterValidadeGeral(idAtividade, tipo).toObservable(),
                avaliacaoAmbientalRepositorio.obterValidadeAvaliacoes(idAtividade, tipo).toObservable(),
                avaliacaoAmbientalRepositorio.obterMedidaRecomendada(idAtividade, tipo),
                new Function4<Integer, Boolean, Boolean, String, Object>() {
                    @Override
                    public Object apply(Integer integer, Boolean validadeGeral, Boolean validadeAvalicoes, String medida) throws Exception {
                        return null;
                    }
                }
        );


        observables
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Object o) {

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
     * Metodo que permite obter os dados gerais
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     */
    public void obterGeral(int idAtividade, int tipo) {

        showProgressBar(true);


        avaliacaoAmbientalRepositorio.obterGeral(idAtividade, tipo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<RelatorioAmbientalResultado>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(RelatorioAmbientalResultado resultado) {
                                geral.setValue(resultado);
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
     * Metodo que permite obter as avaliacoes
     * @param id o identificador do relatorio
     */
    public void obterAvalicoes(int id, int tipo) {

        showProgressBar(true);


        avaliacaoAmbientalRepositorio.obterAvaliacoes(id, tipo)
                .map(new Function<List<AvaliacaoAmbiental>, Object>() {
                    @Override
                    public Object apply(List<AvaliacaoAmbiental> resultados) throws Exception {
                        return Observable.fromIterable(resultados);
                    }
                })
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {


                        Observable<Object> observables = Observable.zip(
                                avaliacaoAmbientalRepositorio.obterCategoriasProfissionais(id, tipo),
                                avaliacaoAmbientalRepositorio.obterMedidas(id, tipo),
                                new BiFunction<List<CategoriaProfissionalResultado>, List<MedidaResultado>, Object>() {
                                    @Override
                                    public Object apply(List<CategoriaProfissionalResultado> categoriaProfissionalResultados, List<MedidaResultado> medidaResultados) throws Exception {
                                        return 1;
                                    }
                                }
                        );



                        return observables;
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
     * Metodo que permite obter uma avaliacao
     * @param id o identificador da avaliacao
     */
    public void obterAvalicao(int id) {

        showProgressBar(true);

        avaliacaoAmbientalRepositorio.obterAvaliacao(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<AvaliacaoAmbientalResultado>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(AvaliacaoAmbientalResultado resultado) {
                                avaliacao.setValue(resultado);
                                showProgressBar(false);
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



    public void fixarCategoriasProfissionais(ArrayList<Integer> resultado) {

        //--avaliacaoAmbientalRepositorio.obterTipos_Incluir(resultado);
    }






    /**
     * Metodo que permite obter os registos selecionados
     * @return uma lista de resultados
     */
    public ArrayList<Integer> obterRegistosSelecionados(){

        ArrayList<Integer> resultado = new ArrayList<>();

//        for (Tipo item : categoriasProfissionais.getValue()) {
//            resultado.add(item.id);
//        }

        return resultado;
    }



}
