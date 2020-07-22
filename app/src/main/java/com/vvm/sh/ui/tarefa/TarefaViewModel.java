package com.vvm.sh.ui.tarefa;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.TarefaRepositorio;
import com.vvm.sh.ui.atividadesExecutadas.AtividadeExecutada;
import com.vvm.sh.ui.cliente.Cliente;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TarefaViewModel extends BaseViewModel {

    private final TarefaRepositorio tarefaRepositorio;

    public MutableLiveData<List<Cliente>> cliente;
    public MutableLiveData<List<AtividadeExecutada>> atividadesExecutadas;


    @Inject
    public TarefaViewModel(TarefaRepositorio tarefaRepositorio){

        this.tarefaRepositorio = tarefaRepositorio;
        cliente = new MutableLiveData<>();
        atividadesExecutadas = new MutableLiveData<>();
    }



    public void obterAtividadesExecutadas(int idTarefa){

//        tarefaRepositorio.obterAtividadesExecutadas(idTarefa).toObservable()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//
//                        new Observer<List<AtividadeExecutada>>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(List<AtividadeExecutada> registos) {
//
//                                atividadesExecutadas.setValue(registos);
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
    }



}
