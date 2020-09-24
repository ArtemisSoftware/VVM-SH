package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.ChecklistRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ChecklistViewModel extends BaseViewModel {

    private final ChecklistRepositorio checklistRepositorio;

    public MutableLiveData<Tipo> checklist;
    public MutableLiveData<List<Item>> itens;


    @Inject
    public ChecklistViewModel(ChecklistRepositorio checklistRepositorio){

        this.checklistRepositorio = checklistRepositorio;
        checklist = new MutableLiveData<>();
        itens = new MutableLiveData<>();
    }


    public MutableLiveData<Tipo> observarChecklist(){
        return checklist;
    }




    //--------------------
    //GRAVAR
    //--------------------

    public void inserArea(AreaChecklistResultado area){

        checklistRepositorio.inserir(area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Long>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Long aLong) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );

    }


    //--------------------
    //OBTER
    //--------------------


    public void obterChecklist(int idAtividade){

        checklistRepositorio.obterChecklist(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Tipo>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Tipo tipo) {
                                checklist.setValue(tipo);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );

    }


    public void obterAreas(int idAtividade, int idChecklist) {

        showProgressBar(true);


        checklistRepositorio.obterAreas(idAtividade, idChecklist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Item>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Item> items) {
                                itens.setValue(items);
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


    //--------------------
    //MiSC
    //--------------------


    public void inserirAreaGeral(int idAtividade, int idChecklist){


        checklistRepositorio.validarAreaGeral(idAtividade, idChecklist)
                .flatMap(new Function<Boolean, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Boolean existe) throws Exception {

                        if(existe == false){
                            AreaChecklistResultado resultado = new AreaChecklistResultado(idAtividade, idChecklist, Identificadores.ID_AREA_GERAL);

                            return checklistRepositorio.inserir(resultado);
                        }

                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Object o) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );

    }
}
