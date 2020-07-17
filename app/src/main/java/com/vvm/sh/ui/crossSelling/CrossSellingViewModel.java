package com.vvm.sh.ui.crossSelling;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.CrossSellingRepositorio;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
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


    @Inject
    public CrossSellingViewModel(CrossSellingRepositorio crossSellingRepositorio){

        this.crossSellingRepositorio = crossSellingRepositorio;

        produtos = new MutableLiveData<>();
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
     * Metodo que permite obter o cross selling de um produto
     * @param idProduto o identificador do produto
     */
    public void obterCrossSelling(String idProduto){

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
