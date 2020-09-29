package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.LevantamentoRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.CategoriaProfissional;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.RelatorioLevantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Risco;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LevantamentosViewModel extends BaseViewModel {

    private final LevantamentoRepositorio levantamentoRepositorio;
    public MutableLiveData<List<Tipo>> modelos;


    public MutableLiveData<List<Levantamento>> levantamentos;

    public MutableLiveData<RelatorioLevantamento> relatorio;

    public MutableLiveData<LevantamentoRiscoResultado> levantamento;

    public MutableLiveData<List<CategoriaProfissional>> categoriasProfissionais;


    public MutableLiveData<List<Risco>> riscos;


    @Inject
    public LevantamentosViewModel(LevantamentoRepositorio levantamentoRepositorio){

        this.levantamentoRepositorio = levantamentoRepositorio;
        levantamentos = new MutableLiveData<>();
        relatorio = new MutableLiveData<>();
        levantamento = new MutableLiveData<>();
        categoriasProfissionais = new MutableLiveData<>();
        modelos = new MutableLiveData<>();
        riscos = new MutableLiveData<>();

    }

    /**
     * Metodo que permite gravar um registo
     * @param registo os dados
     */
    public void gravar(LevantamentoRiscoResultado registo){

        if(levantamento.getValue() == null){

            levantamentoRepositorio.inserir(registo)
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
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }
                            }
                    );
        }
        else{

            registo.id = levantamento.getValue().id;

            levantamentoRepositorio.atualizar(registo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(Integer integer) {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                                }

                                @Override
                                public void onError(Throwable e) {
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }
                            }
                    );
        }

        //TODO: remover atividade pendente + adicionar resultado

    }



    public void gravarCategoriasProfissionais(int idRegisto, ArrayList<Integer> resultado) {


        List<CategoriaProfissionalResultado> registos = new ArrayList<>();

        for(Integer id : resultado) {

            boolean existe = false;

            for (CategoriaProfissional item : categoriasProfissionais.getValue()) {

                if (id == item.categoria.idCategoriaProfissional){
                    existe = true;
                }
            }

            if(existe == false){
                registos.add(new CategoriaProfissionalResultado(idRegisto, id, Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS));
            }
        }


        levantamentoRepositorio.inserir(registos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new SingleObserver<List<Long>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<Long> longs) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );


        //TODO: remover atividade pendente + adicionar resultado
    }

    /**
     * Metodo que permite atualizar uma categoria profissional
     * @param registo os dados a atualizar
     */
    public void atualizarCategoriaProfissional(CategoriaProfissionalResultado registo) {

        levantamentoRepositorio.atualizar(registo).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integer) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {
                                messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

        //TODO: remover atividade pendente + adicionar resultado
    }


    public void remover(CategoriaProfissionalResultado categoria) {

        levantamentoRepositorio.remover(categoria)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Integer integer) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_REMOVIDOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );

        //TODO: remover atividade pendente + adicionar resultado
    }

    //--------------------
    //OBTER
    //--------------------



    /**
     * Metodo que permite obter os levantamentos
     * @param idAtividade o identificador da atividade
     */
    public void obterLevantamentos(int idAtividade) {

        showProgressBar(true);


        levantamentoRepositorio.obterLevantamentos(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Levantamento>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Levantamento> registos) {
                                levantamentos.setValue(registos);
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
     * Metodo que permite obter o relatorio do levantamento
     * @param id o identificador do levantamento
     */
    public void obterRelatorio(int id) {

        levantamentoRepositorio.obterRelatorio(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<RelatorioLevantamento>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RelatorioLevantamento registo) {
                                relatorio.setValue(registo);
                            }

                            @Override
                            public void onError(Throwable e) {
                                RelatorioLevantamento registo = new RelatorioLevantamento();
                                relatorio.setValue(registo);
                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }




    /**
     * Metodo que permite obter um levantamento
     * @param id o identificador do levantamento
     */
    public void obterLevantamento(int id) {

        levantamentoRepositorio.obterLevantamento(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<LevantamentoRiscoResultado>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(LevantamentoRiscoResultado registo) {
                                levantamento.setValue(registo);
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

    public void obterCategoriasProfissionais(int id) {

        levantamentoRepositorio.obterCategoriasProfissionais(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<CategoriaProfissional>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<CategoriaProfissional> registos) {

                                categoriasProfissionais.setValue(registos);
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

    public void obteRiscos(int id) {

        levantamentoRepositorio.obterRiscos(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<RiscoResultado>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<RiscoResultado> registos) {
                                riscos.setValue(registos);
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

    //---------------------
    //MISC
    //---------------------


    public void obterModelos(int idAtividade){


        levantamentoRepositorio.obterModelos(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<Tipo> tipos) {
                                modelos.setValue(tipos);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );
    }



}
