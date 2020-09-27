package com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.repositorios.TrabalhadoresVulneraveisRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacaoAmbiental;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.modelos.TrabalhadorVulneravel;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TrabalhadoresVulneraveisViewModel extends BaseViewModel {

    private final TrabalhadoresVulneraveisRepositorio trabalhadoresVulneraveisRepositorio;


    public MutableLiveData<List<TrabalhadorVulneravel>> vulnerabilidades;
    public MutableLiveData<TrabalhadorVulneravel> vulnerabilidade;
    public MutableLiveData<List<Tipo>> categoriasProfissionaisHomens;
    public MutableLiveData<List<Tipo>> categoriasProfissionaisMulheres;


    private List<TrabalhadorVulneravel> resultadosVulnerabulidades;

    @Inject
    public TrabalhadoresVulneraveisViewModel(TrabalhadoresVulneraveisRepositorio trabalhadoresVulneraveisRepositorio){

        this.trabalhadoresVulneraveisRepositorio = trabalhadoresVulneraveisRepositorio;
        vulnerabilidades = new MutableLiveData<>();
        vulnerabilidade = new MutableLiveData<>();

        categoriasProfissionaisHomens = new MutableLiveData<>();
        categoriasProfissionaisMulheres = new MutableLiveData<>();

        resultadosVulnerabulidades = new ArrayList<>();
    }



    public void validarVulnerabilidades(int idAtividade){

        showProgressBar(true);

        trabalhadoresVulneraveisRepositorio.validarVulnerabilidades(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onComplete() {

                                obterVulnerabilidades(idAtividade);
                                showProgressBar(false);

                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }
                );

        //TODO:dar baixa da atividades pendente
    }


    public void gravar(TrabalhadorVulneravelResultado registo) {

        resultadosVulnerabulidades = new ArrayList<>();

        trabalhadoresVulneraveisRepositorio.atualizar(
                registo,
                ObterCategoriasProfissionais(categoriasProfissionaisHomens.getValue(), registo.id, Identificadores.Origens.CATEGORIAS_PROFISSIONAIS_VULNERABILIDADE_HOMENS),
                ObterCategoriasProfissionais(categoriasProfissionaisMulheres.getValue(), registo.id, Identificadores.Origens.CATEGORIAS_PROFISSIONAIS_VULNERABILIDADE_MULHERES)
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Integer integer) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );

        //TODO:dar baixa da atividades pendente
    }


    public void remover(TrabalhadorVulneravelResultado resultado) {

        resultadosVulnerabulidades = new ArrayList<>();

        resultado.homens = 0;
        resultado.mulheres = 0;

        trabalhadoresVulneraveisRepositorio.remover(resultado)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Integer integer) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );


        //TODO:dar baixa da atividades pendente
    }


    //----------------
    //OBTER
    //----------------


//    public void obterVulnerabilidades(int idAtividade){
//
//        trabalhadoresVulneraveisRepositorio.obterVulnerabilidades__(idAtividade)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//
//                        new Observer<List<TrabalhadorVulneravel>>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(List<TrabalhadorVulneravel> trabalhadorVulneravels) {
//
//                                vulnerabilidades.setValue(trabalhadorVulneravels);
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
//    }


    public void obterVulnerabilidades(int idAtividade){

        showProgressBar(true);

        trabalhadoresVulneraveisRepositorio.obterVulnerabilidades(idAtividade)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                    new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Object o) {

                            resultadosVulnerabulidades.add((TrabalhadorVulneravel)o);
                            List<TrabalhadorVulneravel> registos = new ArrayList<>();

                            for (TrabalhadorVulneravel item : resultadosVulnerabulidades) {

                                for(int index = 0; index < registos.size(); ++index){

                                    if(registos.get(index).resultado.id == item.resultado.id){
                                        registos.remove(index);
                                        break;
                                    }
                                }

                                registos.add(item);
                            }


                            vulnerabilidades.setValue(registos);


                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    }

/*
                    new Observer<List<Object>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<Object> objects) {

                            List<TrabalhadorVulneravel> registos = new ArrayList<>();

                            for (Object o: objects) {

                                registos.add((TrabalhadorVulneravel) o);
                            }


                            vulnerabilidades.setValue(registos);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }

                    }
*/
                    /*
                    new Observer<List<TrabalhadorVulneravel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<TrabalhadorVulneravel> registos) {
                            vulnerabilidades.setValue(registos);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    }
*/
            );
    }


    public void obterVulnerabilidade(int id){


        showProgressBar(true);

        trabalhadoresVulneraveisRepositorio.obterVulnerabilidade(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<TrabalhadorVulneravel>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                
                            }

                            @Override
                            public void onSuccess(TrabalhadorVulneravel trabalhadorVulneravel) {
                                vulnerabilidade.setValue(trabalhadorVulneravel);
                                categoriasProfissionaisHomens.setValue(trabalhadorVulneravel.categoriasProfissionaisHomens);
                                categoriasProfissionaisMulheres.setValue(trabalhadorVulneravel.categoriasProfissionaisMulheres);
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


    public void fixarCategoriasProfissionais(boolean homens, ArrayList<Integer> categoriasProfissionais) {

        trabalhadoresVulneraveisRepositorio.obterTiposCategorias(categoriasProfissionais)
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
                                if(homens == true){
                                    categoriasProfissionaisHomens.setValue(tipos);
                                }
                                else{
                                    categoriasProfissionaisMulheres.setValue(tipos);
                                }


                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );
    }



}
