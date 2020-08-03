package com.vvm.sh.ui.crossSelling;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.CrossSellingRepositorio;
import com.vvm.sh.servicos.ResultadoAsyncTask;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.ui.crossSelling.modelos.CrossSelling;
import com.vvm.sh.ui.crossSelling.modelos.CrossSellingResultado;
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


    public void gravar(String idTarefa, String idCrossSelling, String idDimensao, String idTipo){


        //TODO: gravar crossSelling. Ter atenção que idDimensao e idTipo podem ser nulos
    }



    /**
     * Metodo que permite obter os produtos existentes
     */
    public void obterProdutos(){

        showProgressBar(true);

        crossSellingRepositorio.obterProdutos().toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Tipo> tipos) {

                                produtos.setValue(tipos);
                                sinaletica.setValue(Boolean.parseBoolean(tipos.get(0).detalhe));
                                showProgressBar(false);
                                obterCrossSelling(tipos.get(0));
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



    /**
     * Metodo que permite obter o cross selling de um produto
     * @param produto os dados do produto
     */
    public void obterCrossSelling(Tipo produto){

        showProgressBar(true);

        crossSellingRepositorio.obterCrossSelling(produto.id + "").toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<CrossSelling>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<CrossSelling> tipos) {

                                crossSelling.setValue(tipos);

                                sinaletica.setValue(Boolean.parseBoolean(produto.detalhe));
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



    public void obterSinaletica(){
        obterDimensoes();
        obterTipos();
    }


    /**
     * Metodo que permite obter as dimensões
     */
    public void obterDimensoes(){

        showProgressBar(true);

        crossSellingRepositorio.obterDimensoes().toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Tipo> tipos) {

                                dimensoes.setValue(tipos);
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

    /**
     * Metodo que permite obter os tipos
     */
    public void obterTipos(){

        showProgressBar(true);

        crossSellingRepositorio.obterTipos().toObservable()
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

                                tipos.setValue(registos);
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





    public void gravar(CrossSellingResultado registo) {

        crossSellingRepositorio.inserir(registo).toObservable()
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


        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, crossSellingRepositorio.resultadoDao);
        servico.execute(new Resultado(registo.idTarefa, ResultadoId.CROSS_SELLING));
    }

    public void remover(CrossSelling crossSelling) {

        crossSellingRepositorio.remover(crossSelling).toObservable()
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
        
        
        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, crossSellingRepositorio.resultadoDao);
        servico.execute(new Resultado(crossSelling.idTarefa, ResultadoId.CROSS_SELLING));
    }
}
