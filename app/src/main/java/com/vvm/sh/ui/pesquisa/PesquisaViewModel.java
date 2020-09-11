package com.vvm.sh.ui.pesquisa;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PesquisaViewModel extends BaseViewModel {

    private final TiposRepositorio tiposRepositorio;
    private final int idApi;

    public MutableLiveData<List<Tipo>> tipos;


    @Inject
    public PesquisaViewModel(int idApi, TiposRepositorio tiposRepositorio){

        this.tiposRepositorio = tiposRepositorio;
        this.idApi = idApi;

        tipos = new MutableLiveData<>();
    }


    public void obterRegistos(String metodo, List<Integer> registos){

        showProgressBar(true);

        tiposRepositorio.obterTipos(metodo, registos, idApi).toObservable()
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
    }

}
