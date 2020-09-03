package com.vvm.sh.ui.crossSelling;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.CrossSellingRepositorio;
import com.vvm.sh.servicos.ResultadoAsyncTask;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.crossSelling.modelos.CrossSelling;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class CrossSellingViewModel extends BaseViewModel {


    private final CrossSellingRepositorio crossSellingRepositorio;

    public MutableLiveData<List<Tipo>> produtos;
    public MutableLiveData<List<CrossSelling>> crossSelling;
    public MutableLiveData<List<Tipo>> dimensoes;
    public MutableLiveData<List<Tipo>> tipos;
    public MutableLiveData<Boolean> sinaletica;


    @Inject
    public CrossSellingViewModel(CrossSellingRepositorio crossSellingRepositorio){

        this.crossSellingRepositorio = crossSellingRepositorio;

        produtos = new MutableLiveData<>();
        crossSelling = new MutableLiveData<>();
        sinaletica = new MutableLiveData<>();
        dimensoes = new MutableLiveData<>();
        tipos = new MutableLiveData<>();
    }



    //---------------------------
    //GRAVAR
    //---------------------------

    /**
     * Metodo que permite gravar um registo
     * @param registo os dados do registo
     */
    public void gravar(CrossSellingResultado registo) {

        crossSellingRepositorio.inserir(registo).toObservable()
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


        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, crossSellingRepositorio.resultadoDao);
        servico.execute(new Resultado(registo.idTarefa, ResultadoId.CROSS_SELLING));
    }


    //----------------------
    //OBTER
    //----------------------


    /**
     * Metodo que permite obter os produtos existentes
     */
    public void obterProdutos(int idTarefa){

        showProgressBar(true);

        crossSellingRepositorio.obterProdutos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Tipo> tipos) {
                                produtos.setValue(tipos);
                                sinaletica.setValue(ConversorUtil.converter_String_Para_Boolean(tipos.get(0).detalhe));
                                showProgressBar(false);
                                obterCrossSelling(idTarefa, tipos.get(0));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }



    /**
     * Metodo que permite obter o cross selling de um produto
     * @param produto os dados do produto
     */
    public void obterCrossSelling(int idTarefa, Tipo produto){

        sinaletica.setValue(ConversorUtil.converter_String_Para_Boolean(produto.detalhe));

        showProgressBar(true);

        crossSellingRepositorio.obterCrossSelling(idTarefa, produto.id + "").toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<CrossSelling>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<CrossSelling> resultado) {

                                crossSelling.setValue(resultado);
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


    //---------------------------
    //MISC
    //---------------------------


    /**
     * Metodo que permite obter os dados da sinaletica (dimensoes + tipos
     */
    public void obterSinaletica(){

        Observable<Sinaletica> observables = Observable.zip(crossSellingRepositorio.obterDimensoes().toObservable(), crossSellingRepositorio.obterTipos().toObservable(),
                new BiFunction<List<Tipo>, List<Tipo>, Sinaletica>() {
            @Override
            public Sinaletica apply(List<Tipo> dimensoes, List<Tipo> tipos) throws Exception {

                Sinaletica sinaletica = new Sinaletica();
                sinaletica.dimensoes = dimensoes;
                sinaletica.tipos = tipos;

                return sinaletica;
            }
        });

        showProgressBar(true);

        observables
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Sinaletica>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Sinaletica o) {
                                dimensoes.setValue(o.dimensoes);
                                tipos.setValue(o.tipos);
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


    //---------------------------
    //REMOVER
    //---------------------------


    /**
     * Metodo que permite remover um registo
     * @param crossSelling os dados do registo
     */
    public void remover(CrossSelling crossSelling) {

        crossSellingRepositorio.remover(crossSelling).toObservable()
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
        
        
        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, crossSellingRepositorio.resultadoDao);
        servico.execute(new Resultado(crossSelling.idTarefa, ResultadoId.CROSS_SELLING));
    }


    private class Sinaletica{

        List<Tipo> dimensoes;
        List<Tipo> tipos;

    }

}
