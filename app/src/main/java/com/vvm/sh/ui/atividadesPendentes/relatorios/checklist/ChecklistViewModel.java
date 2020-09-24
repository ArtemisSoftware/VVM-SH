package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.ChecklistRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Questao;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
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


    public MutableLiveData<List<Tipo>> respostas;

    public MutableLiveData<List<AreaChecklist>> tipoAreas;


    public MutableLiveData<List<Questao>> questionario;


    @Inject
    public ChecklistViewModel(ChecklistRepositorio checklistRepositorio){

        this.checklistRepositorio = checklistRepositorio;
        checklist = new MutableLiveData<>();
        itens = new MutableLiveData<>();
        tipoAreas = new MutableLiveData<>();
        questionario = new MutableLiveData<>();
        respostas = new MutableLiveData<>();
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



    public void inserNovaArea(AreaChecklistResultado area){

        checklistRepositorio.validarSubDescricaoArea(area.idAtividade, area.idChecklist, area.idArea, area.subDescricao)
                .flatMap(new Function<Boolean, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Boolean resultado) throws Exception {


                        if(resultado == false){
                            return checklistRepositorio.inserir(area);
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
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {
                                messagemLiveData.setValue(Recurso.erro("A descrição já existe"));
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


    public void obterSeccoes(int idRegistoArea) {

        showProgressBar(true);


        checklistRepositorio.obterSeccoes(idRegistoArea)
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




    public void obterAreasChecklist(int idChecklist){

        checklistRepositorio.obterAreasChecklist(idChecklist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<AreaChecklist>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<AreaChecklist> areaChecklists) {
                                tipoAreas.setValue(areaChecklists);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }


    public void obterQuestoes(Item item){


        checklistRepositorio.obterQuestoes(item.id, item.uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Questao>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Questao> resultado) {
                                questionario.setValue(resultado);
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
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Object o) {
                                obterAreas(idAtividade, idChecklist);
                            }

                            @Override
                            public void onError(Throwable e) {
                                obterAreas(idAtividade, idChecklist);
                            }
                        }

                );

    }



    /**
     * Metodo que permite obter as respostas
     */
    private void obterRespostas(){

        List<Tipo> estado = new ArrayList<>();

        estado.add(TiposConstantes.Checklist.SIM);
        estado.add(TiposConstantes.Checklist.NAO);
        estado.add(TiposConstantes.Checklist.NAO_APLICAVEL);
        respostas.setValue(estado);

    }


}
