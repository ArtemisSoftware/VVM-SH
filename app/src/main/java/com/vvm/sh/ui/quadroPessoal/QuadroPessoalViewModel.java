package com.vvm.sh.ui.quadroPessoal;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.QuadroPessoalRepositorio;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QuadroPessoalViewModel extends BaseViewModel {


    private final QuadroPessoalRepositorio quadroPessoalRepositorio;

    public MutableLiveData<List<ColaboradorRegisto>> colaboradores;


    @Inject
    public QuadroPessoalViewModel(QuadroPessoalRepositorio quadroPessoalRepositorio){

        this.quadroPessoalRepositorio = quadroPessoalRepositorio;
        colaboradores = new MutableLiveData<>();

    }



    //--------------------
    //OBTER
    //--------------------


    /**
     * Metodo que permite obter o quadro pessoal
     * @param idTarefa o identificador da tarefa
     */
    public void obterQuadroPessoal(int idTarefa){

        showProgressBar(true);

        quadroPessoalRepositorio.obterQuadroPessoal(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<ColaboradorRegisto>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<ColaboradorRegisto> resultados) {
                                colaboradores.setValue(resultados);
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


    public void obterColaborador(int idTarefa){



    }

}
