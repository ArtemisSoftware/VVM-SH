package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.AvaliacaoAmbientalRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.RelatorioAmbiental;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class AvaliacaoAmbientalViewModel extends BaseViewModel {

    private final AvaliacaoAmbientalRepositorio avaliacaoAmbientalRepositorio;

    public MutableLiveData<RelatorioAmbiental> relatorio;



    public MutableLiveData<RelatorioAmbientalResultado> geral;
    public MutableLiveData<List<Tipo>> nebulosidades;



    private List<AvaliacaoAmbiental> resultadosAvaliacoes;
    public MutableLiveData<List<AvaliacaoAmbiental>> avaliacoes;

    public MutableLiveData<List<Tipo>> areas;
    public MutableLiveData<List<Tipo>> generos;
    public MutableLiveData<List<Tipo>> iluminacao;
    public MutableLiveData<List<Tipo>> elxArea;
    public MutableLiveData<List<Tipo>> elx;
    public MutableLiveData<List<Tipo>> categoriasProfissionais;

    public MutableLiveData<AvaliacaoAmbiental> avaliacao;
    //--public MutableLiveData<CategoriaProfissionalResultado> categoriasProfissionais;


    @Inject
    public AvaliacaoAmbientalViewModel(AvaliacaoAmbientalRepositorio avaliacaoAmbientalRepositorio){

        this.avaliacaoAmbientalRepositorio = avaliacaoAmbientalRepositorio;

        relatorio = new MutableLiveData<>();

        geral = new MutableLiveData<>();
        nebulosidades = new MutableLiveData<>();

        resultadosAvaliacoes = new ArrayList<>();
        avaliacoes = new MutableLiveData<>();
        areas = new MutableLiveData<>();
        generos = new MutableLiveData<>();
        iluminacao = new MutableLiveData<>();
        elxArea = new MutableLiveData<>();
        elx = new MutableLiveData<>();
        categoriasProfissionais = new MutableLiveData<>();


        avaliacao = new MutableLiveData<>();

        //---categoriasProfissionais = new MutableLiveData<>();
    }



    public MutableLiveData<RelatorioAmbiental> observarRelatorio(){
        return relatorio;
    }

    public MutableLiveData<List<Tipo>> observaElxArea(){
        return elxArea;
    }


    /**
     * Metodo que permite gravar um registo
     * @param idRelatorio o identificador do relatorio
     * @param registo os dados a gravar
     */
    public void gravar(int idRelatorio, RelatorioAmbientalResultado registo) {

        if(idRelatorio == 0){

            avaliacaoAmbientalRepositorio.inserir(registo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Long>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Long aLong) {
                                    messagemLiveData.setValue(Recurso.successo(aLong, Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                }

                                @Override
                                public void onError(Throwable e) {
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }
                            }
                    );
        }
        else{
            registo.id = idRelatorio;
            avaliacaoAmbientalRepositorio.atualizar(registo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new SingleObserver<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
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
    }


    public void gravar(AvaliacaoAmbientalResultado registo, List<Integer> categoriasProfissionais, boolean nivel) {

        resultadosAvaliacoes.clear();

        if(avaliacao.getValue() == null){

            avaliacaoAmbientalRepositorio.inserir(registo)
                    .flatMap(new Function<Long, SingleSource<?>>() {
                        @Override
                        public SingleSource<?> apply(Long id) throws Exception {


                            int idRegisto = ConversorUtil.converter_long_Para_int(id);
                            
                            List<CategoriaProfissionalResultado> categorias = new ArrayList<>();

                            for(int idCategoria : categoriasProfissionais){
                                categorias.add(new CategoriaProfissionalResultado(idRegisto, idCategoria, Identificadores.Origens.AVALIACAO_AMBIENTAL_ILUMINACAO_CATEGORIAS_PROFISSIONAIS));
                            }

                            
                            return Single.fromObservable(avaliacaoAmbientalRepositorio.inserir(categorias).toObservable());
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

                                    //TODO: remover a atividade pendente
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }

                    );
        }
        else{

            registo.id = avaliacao.getValue().resultado.id;


            List<CategoriaProfissionalResultado> categorias = new ArrayList<>();

            if(categoriasProfissionais == null){

                for (Tipo categoria : avaliacao.getValue().categoriasProfissionais) {
                    categorias.add(new CategoriaProfissionalResultado(registo.id, categoria.id, Identificadores.Origens.AVALIACAO_AMBIENTAL_ILUMINACAO_CATEGORIAS_PROFISSIONAIS));
                }
            }
            else {

                for (int idCategoria : categoriasProfissionais) {
                    categorias.add(new CategoriaProfissionalResultado(registo.id, idCategoria, Identificadores.Origens.AVALIACAO_AMBIENTAL_ILUMINACAO_CATEGORIAS_PROFISSIONAIS));
                }
            }


            Disposable d = avaliacaoAmbientalRepositorio.atualizarAvaliacao(registo, categorias)
                    .toList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Consumer<Object>() {
                                @Override
                                public void accept(Object o) throws Exception {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                                    //TODO: remover a atividade pendente
                                }
                            }
                    );

            disposables.add(d);

        }
        
/*
        if(idAvaliacao.equals(AppIF.SEM_DADOS) == true){
            idAvaliacao = inserirAvaliacao(idRelatorio, idArea, anexoArea, nome,
                    sexo, idTipoIluminacao, emedioLx, idElx, elxArea, elx,
                    homens, mulheres, temperatura, humidadeRelativa);
        }
        else{
            atualizarAvaliacao(idAvaliacao, idArea, anexoArea, nome,
                    sexo, idTipoIluminacao, emedioLx, idElx, elxArea, elx,
                    homens, mulheres, temperatura, humidadeRelativa);
        }


        gravarCategoriasProfissionais_(Integer.parseInt(origem), idAvaliacao, categoriasProfissionais, homens, mulheres);


        if(nivel == true){
            removerMedidas(idAvaliacao);
        }

        abaterAtividadePendente(idAtividade);
        gravarResultado();
*/
    }

    public void gravar(AvaliacaoAmbientalResultado registo, List<Integer> categoriasProfissionais, boolean nivelHumidade, boolean nivelTemperatura) {
    }


    //--------------------
    //OBTER
    //--------------------


    /**
     * Metodo que permite obter a validade do relatorio
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     */
    public void obterValidadeRelatorio(int idAtividade, int tipo) {

        final RelatorioAmbiental[] registo = {new RelatorioAmbiental()};

        avaliacaoAmbientalRepositorio.obterValidadeRelatorio(idAtividade, tipo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(Object o) {
                                registo[0] = (RelatorioAmbiental) o;
                                relatorio.setValue(registo[0]);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                relatorio.setValue(registo[0]);
                            }
                        }
                );
    }


    /**
     * Metodo que permite obter os dados gerais
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     */
    public void obterGeral(int idAtividade, int tipo) {

        showProgressBar(true);


        avaliacaoAmbientalRepositorio.obterNebulosidade()
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
                            nebulosidades.setValue(tipos);
                            showProgressBar(false);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    }
            );



        avaliacaoAmbientalRepositorio.obterGeral(idAtividade, tipo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<RelatorioAmbientalResultado>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(RelatorioAmbientalResultado registo) {
                                geral.setValue(registo);
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


    /**
     * Metodo que permite obter as avaliacoes
     * @param id o identificador do relatorio
     */
    public void obterAvalicoes(int id, int tipo) {


        avaliacaoAmbientalRepositorio.obterAvaliacoes(id, tipo)
                .flatMap(new Function<List<AvaliacaoAmbiental>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(List<AvaliacaoAmbiental> avaliacaoAmbientals) throws Exception {
                        return Observable.fromIterable(avaliacaoAmbientals);
                    }
                })
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {
                        AvaliacaoAmbiental registo = (AvaliacaoAmbiental) o;

                        Observable<Object> observables = Observable.zip(
                                avaliacaoAmbientalRepositorio.obterCategoriasProfissionais(registo.resultado.id, tipo).toObservable(),
                                avaliacaoAmbientalRepositorio.obterMedidas(registo.resultado.id, tipo),
                                new BiFunction<List<Tipo>, List<MedidaResultado>, Object>() {
                                    @Override
                                    public Object apply(List<Tipo> categoriaProfissionalResultados, List<MedidaResultado> medidaResultados) throws Exception {

                                        registo.categoriasProfissionais = categoriaProfissionalResultados;
                                        return registo;
                                    }
                                }
                        );



                        return observables;
                    }
                })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Object o) {

                                resultadosAvaliacoes.add((AvaliacaoAmbiental)o);
                                List<AvaliacaoAmbiental> registos = new ArrayList<>();

                                for (AvaliacaoAmbiental item : resultadosAvaliacoes) {

                                    for(int index = 0; index < registos.size(); ++index){

                                        if(registos.get(index).resultado.id == item.resultado.id){
                                            registos.remove(index);
                                            break;
                                        }
                                    }

                                    registos.add(item);
                                }


                                avaliacoes.setValue(registos);
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
        showProgressBar(true);


        avaliacaoAmbientalRepositorio.obterAvaliacoes(id, tipo)
                .map(new Function<List<AvaliacaoAmbiental>, Object>() {
                    @Override
                    public Object apply(List<AvaliacaoAmbiental> avaliacaoAmbientals) throws Exception {
                        return avaliacaoAmbientals;
                    }
                })
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {

                        AvaliacaoAmbiental registo = (AvaliacaoAmbiental) o;

                        Observable<Object> observables = Observable.zip(
                                avaliacaoAmbientalRepositorio.obterCategoriasProfissionais(registo.resultado.id, tipo),
                                avaliacaoAmbientalRepositorio.obterMedidas(registo.resultado.id, tipo),
                                new BiFunction<List<CategoriaProfissionalResultado>, List<MedidaResultado>, Object>() {
                                    @Override
                                    public Object apply(List<CategoriaProfissionalResultado> categoriaProfissionalResultados, List<MedidaResultado> medidaResultados) throws Exception {

                                        registo.categoriasProfissionais = categoriaProfissionalResultados;
                                        return registo;
                                    }
                                }
                        );



                        return observables;
                    }
                })
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(


                        new Observer<List<Object>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Object> objects) {
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

        */
    }


    /**
     * Metodo que permite obter uma avaliacao
     * @param id o identificador da avaliacao
     */
    public void obterAvalicao(int id, int tipo) {

        showProgressBar(true);

        if(id != -1) {

            avaliacaoAmbientalRepositorio.obterAvaliacao(id, tipo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new MaybeObserver<AvaliacaoAmbiental>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(AvaliacaoAmbiental avaliacaoAmbiental) {
                                    avaliacao.setValue(avaliacaoAmbiental);
                                    categoriasProfissionais.setValue(avaliacaoAmbiental.categoriasProfissionais);
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

        Single.zip(avaliacaoAmbientalRepositorio.obterTipoIluminacao(), avaliacaoAmbientalRepositorio.obterAreas(), avaliacaoAmbientalRepositorio.obterElxArea(),
                new Function3<List<Tipo>, List<Tipo>, List<Tipo>, TiposAvaliacao>() {
                    @Override
                    public TiposAvaliacao apply(List<Tipo> iluminacao, List<Tipo> areas, List<Tipo> elxArea) throws Exception {

                        TiposAvaliacao registo = new TiposAvaliacao();
                        registo.areas = areas;
                        registo.tiposIluminacao = iluminacao;
                        registo.elxArea = elxArea;
                        return registo;
                    }
                }
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(

                new SingleObserver<TiposAvaliacao>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(TiposAvaliacao tiposAvaliacao) {
                        areas.setValue(tiposAvaliacao.areas);
                        iluminacao.setValue(tiposAvaliacao.tiposIluminacao);
                        elxArea.setValue(tiposAvaliacao.elxArea);
                        obterGeneros(generos);
                        showProgressBar(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }

        );


    }






    //---------------------
    //REMOVER
    //---------------------


    public void remover(AvaliacaoAmbientalResultado registo, int tipo) {

        resultadosAvaliacoes.clear();

        avaliacaoAmbientalRepositorio.removerAvaliacao(registo, tipo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Object o) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_REMOVIDOS_SUCESSO));
                                //TODO: remover a atividade pendente
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }









    public void obterElx(int id) {

        avaliacaoAmbientalRepositorio.obterElx(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<Tipo> tipos) {
                                elx.setValue(tipos);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }



    public void fixarCategoriasProfissionais(List<Integer> resultado) {

        avaliacaoAmbientalRepositorio.obterTipos_Incluir(resultado).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Tipo> tipos) {
                                categoriasProfissionais.setValue(tipos);
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






    /**
     * Metodo que permite obter os registos selecionados
     * @return uma lista de resultados
     */
    public ArrayList<Integer> obterRegistosSelecionados(){

        ArrayList<Integer> resultado = new ArrayList<>();

//        for (Tipo item : categoriasProfissionais.getValue()) {
//            resultado.add(item.id);
//        }

        return resultado;
    }




    private class TiposAvaliacao{

        List<Tipo> areas;
        List<Tipo> tiposIluminacao;
        List<Tipo> elxArea;
    }


}
