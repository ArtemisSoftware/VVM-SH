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
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
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


    private int pagina;

    @Inject
    public QuadroPessoalViewModel(QuadroPessoalRepositorio quadroPessoalRepositorio){

        this.quadroPessoalRepositorio = quadroPessoalRepositorio;
        colaboradores = new MutableLiveData<>();
        colaborador = new MutableLiveData<>();
        moradas = new MutableLiveData<>();
        generos = new MutableLiveData<>();

        pagina = 1;
    }



    public MutableLiveData<List<ColaboradorRegisto>> observarColaboradores(){
        return colaboradores;
    }



    //--------------------
    //GRAVAR
    //--------------------

    /**
     * Metodo que permite gravar um colaborador
     * @param idResultado
     * @param resultado
     */
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

                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
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
                                    disposables.add(d);
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


    public void obterProximaPagina(int idTarefa){


        if(performingQuery == true){
            return;
        }

        performingQuery = true;

        ++pagina;
        obterQuadroPessoal(idTarefa);
//        if(!isQueryExhausted && !isPerformingQuery){
//            pageNumber++;
//            executeSearch();
//        }
    }



    /**
     * Metodo que permite obter o quadro pessoal
     * @param idTarefa o identificador da tarefa
     */
    public void obterQuadroPessoal(int idTarefa){

        quadroPessoalRepositorio.obterQuadroPessoal(idTarefa, pagina)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<ColaboradorRegisto>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                                messagemLiveData.setValue(Recurso.loading(""));
                            }

                            @Override
                            public void onNext(List<ColaboradorRegisto> resultados) {

                                /*
                                if(colaboradores.getValue() == null){
                                    colaboradores.setValue(resultados);
                                }
                                else {
                                    List<ColaboradorRegisto> registos = colaboradores.getValue();

                                    registos.addAll(resultados);
                                    colaboradores.setValue(registos);
                                }*/


                                colaboradores.setValue(resultados);
                                performingQuery = false;
                            }

                            @Override
                            public void onError(Throwable e) {
                                performingQuery = false;
                            }

                            @Override
                            public void onComplete() {
                                performingQuery = false;
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
    public void obterColaborador(int idTarefa, int id, int origem) {

        Maybe.zip(quadroPessoalRepositorio.obterColaborador(id, origem), quadroPessoalRepositorio.obterMoradas(idTarefa),
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



    public void remover(int idTarefa, int id){

        quadroPessoalRepositorio.remover(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Integer integer) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_REMOVIDOS_SUCESSO));
                                gravarResultado(quadroPessoalRepositorio.resultadoDao, idTarefa, ResultadoId.QUADRO_PESSOAL);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );

    }


    //----------------------
    //MISC
    //----------------------


    /**
     * Metodo que permite pesquisar nomes
     * @param idTarefa o identificador da tarefa
     * @param nome o nome do colaborador
     */
    public void pesquisarQuadroPessoal(int idTarefa, String nome){

        pagina = 1;

        if(nome.equals("") == true){
            colaboradores.setValue(new ArrayList<>());
            obterQuadroPessoal(idTarefa);
        }
        else {

            showProgressBar(true);

            quadroPessoalRepositorio.pesquisarQuadroPessoal(idTarefa, nome)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<List<ColaboradorRegisto>>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(List<ColaboradorRegisto> registos) {

                                    colaboradores.setValue(registos);
                                    showProgressBar(false);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    showProgressBar(false);
                                }
                            }
                    );
        }
    }




    private class QuadroPessoal{

        List<Morada> moradas;
        ColaboradorRegisto registo;

    }
}
