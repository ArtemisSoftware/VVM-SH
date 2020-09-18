package com.vvm.sh.ui.pesquisa;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class PesquisaViewModel extends BaseViewModel {

    private final TiposRepositorio tiposRepositorio;
    private final int idApi;

    public MutableLiveData<List<Tipo>> tiposSelecionados;
    public MutableLiveData<List<Tipo>> tipos;


    @Inject
    public PesquisaViewModel(int idApi, TiposRepositorio tiposRepositorio){

        this.tiposRepositorio = tiposRepositorio;
        this.idApi = idApi;

        tiposSelecionados = new MutableLiveData<>();
        tipos = new MutableLiveData<>();
    }


    public void obterRegistos(String metodo, List<Integer> registos){

        showProgressBar(true);

        Observable<PesquisaTipos> observables = Observable.zip(
                tiposRepositorio.obterTipos_Excluir(metodo, registos, idApi).toObservable(),
                tiposRepositorio.obterTipos_Incluir(metodo, registos, idApi).toObservable(),
                new BiFunction<List<Tipo>, List<Tipo>, PesquisaTipos>() {
                    @Override
                    public PesquisaTipos apply(List<Tipo> tipos, List<Tipo> tiposSelecionados) throws Exception {

                        return new PesquisaTipos(tipos, tiposSelecionados);
                    }
                });


        observables
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<PesquisaTipos>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(PesquisaTipos pesquisaTipos) {

                                tipos.setValue(pesquisaTipos.registos);
                                tiposSelecionados.setValue(pesquisaTipos.registado);
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

        /*
        tiposRepositorio.obterTipos_Excluir(metodo, registos, idApi).toObservable()
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
                                showProgressBar(false);
                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }

                );
        */
    }


    public void pesquisar(String metodo, List<Integer> registos, String pesquisa) {

        tiposRepositorio.obterTipos_Excluir(metodo, registos, pesquisa, idApi)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Tipo> registos) {
                                tipos.setValue(registos);
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



    //----------------------
    //
    //----------------------

    /**
     * Metodo que permite obter os registos selecionados
     * @return uma lista de resultados
     */
    public ArrayList<Integer> obterRegistosSelecionados(){

        ArrayList<Integer> resultado = new ArrayList<>();

        for (Tipo item : tiposSelecionados.getValue()) {
            resultado.add(item.id);
        }

        return resultado;
    }



    //----------------------
    //classe
    //----------------------


    private class PesquisaTipos{

        List<Tipo> registos;
        List<Tipo> registado;

        public PesquisaTipos(List<Tipo> registos, List<Tipo> registado) {
            this.registos = registos;
            this.registado = registado;
        }
    }
}
