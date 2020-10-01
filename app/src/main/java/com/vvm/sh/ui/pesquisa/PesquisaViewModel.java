package com.vvm.sh.ui.pesquisa;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.Equipamento;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class PesquisaViewModel extends BaseViewModel {

    private final TiposRepositorio tiposRepositorio;
    private final int idApi;

    public MutableLiveData<List<Tipo>> tiposSelecionados;
    public MutableLiveData<List<Tipo>> tipos;


    public MutableLiveData<List<Equipamento>> equipamentos;
    public MutableLiveData<List<Equipamento>> equipamentosSelecionados;


    @Inject
    public PesquisaViewModel(int idApi, TiposRepositorio tiposRepositorio){

        this.tiposRepositorio = tiposRepositorio;
        this.idApi = idApi;

        tiposSelecionados = new MutableLiveData<>();
        tipos = new MutableLiveData<>();
        equipamentos = new MutableLiveData<>();
        equipamentosSelecionados = new MutableLiveData<>();
    }


    public void gravar(TipoNovo resultado) {


        //TODO: obter o numero provisorio e depois inserir

        tiposRepositorio.validarEquipamento(idApi, resultado.descricao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Boolean resultado) {

                                if(resultado == true) {

                                    messagemLiveData.setValue(Recurso.erro("O equipamento já se encontra registado"));
                                }
                                else{
                                    messagemLiveData.setValue(Recurso.successo("Equipamento registado com sucesso"));
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }



    //-----------------------
    //OBTER
    //-----------------------


    public void obterEquipamentos(int idAtividade) {
        obterEquipamentos(idAtividade, new ArrayList<>());
    }

    public void obterEquipamentos(int idAtividade, List<Integer> registos) {

        showProgressBar(true);

        Observable<PesquisaEquipamentos> observable = Observable.zip(
                tiposRepositorio.obterEquipamentos_Excluir(idAtividade, idApi, registos),
                tiposRepositorio.obterEquipamentos_Incluir(idAtividade, idApi, registos),
                new BiFunction<List<Equipamento>, List<Equipamento>, PesquisaEquipamentos>() {
                    @Override
                    public PesquisaEquipamentos apply(List<Equipamento> tipos, List<Equipamento> tiposSelecionados) throws Exception {
                        return new PesquisaEquipamentos(tipos, tiposSelecionados);
                    }
                }
        );

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<PesquisaEquipamentos>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(PesquisaEquipamentos registos) {

                                equipamentos.setValue(registos.registos);
                                equipamentosSelecionados.setValue(registos.registado);
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



    public void pesquisarEquipamento(int idAtividade, List<Integer> registos, String pesquisa) {

        tiposRepositorio.obterEquipamentos_Excluir(idAtividade, registos, pesquisa, idApi)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<List<Equipamento>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Equipamento> registos) {
                                equipamentos.setValue(registos);
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


    private class PesquisaEquipamentos{

        List<Equipamento> registos;
        List<Equipamento> registado;

        public PesquisaEquipamentos(List<Equipamento> registos, List<Equipamento> registado) {
            this.registos = registos;
            this.registado = registado;
        }
    }

}
