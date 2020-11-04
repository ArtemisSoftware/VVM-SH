package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.AvaliacaoAmbientalRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.AvaliacaoAmbiental;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.RelatorioAmbiental;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
    public MutableLiveData<List<Tipo>> medidas;

    public MutableLiveData<AvaliacaoAmbiental> avaliacao;
    public MutableLiveData<Boolean> possuiRelatorio;


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
        medidas = new MutableLiveData<>();

        avaliacao = new MutableLiveData<>();
        possuiRelatorio = new MutableLiveData<>();

    }



    public MutableLiveData<Boolean> observarRelatorio(){
        return possuiRelatorio;
    }

    public MutableLiveData<List<Tipo>> observarElxArea(){
        return elxArea;
    }

    public MutableLiveData<List<AvaliacaoAmbiental>> observarAvaliacoes(){
        return avaliacoes;
    }

    public MutableLiveData<AvaliacaoAmbiental> observarAvaliacao(){
        return avaliacao;
    }



    /**
     * Metodo que permite gravar um registo
     * @param idTarefa o identificador da tarefa
     * @param idRelatorio o identificador do relatorio
     * @param registo os dados a gravar
     */
    public void gravar(int idTarefa, int idRelatorio, RelatorioAmbientalResultado registo) {

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
                                    abaterAtividadePendente(avaliacaoAmbientalRepositorio.resultadoDao, idTarefa, registo.idAtividade);
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
                                    abaterAtividadePendente(avaliacaoAmbientalRepositorio.resultadoDao, idTarefa, registo.idAtividade);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                }
                            }
                    );
        }
    }


    /**
     * Metodo que permite gravar uma avaliacao
     * @param idTarefa
     * @param idAtividade
     * @param registo
     * @param categoriasProfissionais
     * @param medidas
     * @param nivel
     * @param origem
     * @param origemMedidas
     */
    public void gravar(int idTarefa, int idAtividade, AvaliacaoAmbientalResultado registo, List<Integer> categoriasProfissionais, List<Integer> medidas, boolean nivel, int origem, int origemMedidas) {

        resultadosAvaliacoes.clear();

        if(avaliacao.getValue() == null){

            avaliacaoAmbientalRepositorio.inserir(registo)
                    .flatMap(new Function<Long, SingleSource<?>>() {
                        @Override
                        public SingleSource<?> apply(Long id) throws Exception {

                            int idRegisto = ConversorUtil.converter_long_Para_int(id);

                            if(nivel == true){
                                medidas.clear();
                            }

                            return Single.fromObservable(avaliacaoAmbientalRepositorio.inserir(idRegisto, categoriasProfissionais, medidas, origem, origemMedidas));
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
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                    abaterAtividadePendente(avaliacaoAmbientalRepositorio.resultadoDao, idTarefa, idAtividade);
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
                    categorias.add(new CategoriaProfissionalResultado(registo.id, categoria.id, origem));
                }
            }
            else {

                for (int idCategoria : categoriasProfissionais) {
                    categorias.add(new CategoriaProfissionalResultado(registo.id, idCategoria, origem));
                }
            }



            List<MedidaResultado> medidasRegistas = new ArrayList<>();

            if(nivel == false){

                if(medidas == null){

                    for (Tipo medida : avaliacao.getValue().medidas) {
                        medidasRegistas.add(new MedidaResultado(registo.id, origemMedidas, medida.id));
                    }
                }
                else {

                    for (int idMedida : medidas) {
                        medidasRegistas.add(new MedidaResultado(registo.id, origemMedidas, idMedida));
                    }
                }
            }


            Disposable d = avaliacaoAmbientalRepositorio.atualizarAvaliacao(registo, categorias, medidasRegistas, origem, origemMedidas)
                    .toList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Consumer<Object>() {
                                @Override
                                public void accept(Object o) throws Exception {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                                    abaterAtividadePendente(avaliacaoAmbientalRepositorio.resultadoDao, idTarefa, idAtividade);
                                }
                            }
                    );

            disposables.add(d);

        }
    }



    //---------------------
    //REMOVER
    //---------------------



    /**
     * Metodo que permite remover uma avaliacao ambiental
     * @param idTarefa
     * @param idAtividade
     * @param registo os dados do registo
     * @param origem o identificador da origem do relatorio
     */
    public void remover(int idTarefa, int idAtividade, AvaliacaoAmbientalResultado registo, int origem) {

        avaliacoes.setValue(new ArrayList<>());
        resultadosAvaliacoes.clear();

        avaliacaoAmbientalRepositorio.removerAvaliacao(registo, origem)
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
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_REMOVIDOS_SUCESSO));
                                abaterAtividadePendente(avaliacaoAmbientalRepositorio.resultadoDao, idTarefa, idAtividade);
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


    /**
     * Metodo que permite obter o relatorio
     * @param idAtividade o identificador da atividade
     * @param origem a origem do relatorio
     */
    public void obterRelatorio(int idAtividade, int origem) {

        final RelatorioAmbiental[] registo = {new RelatorioAmbiental()};

        existeRelatorio(idAtividade, origem);

        avaliacaoAmbientalRepositorio.obterRelatorio(idAtividade, origem)
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
     * @param origem a origem do relatorio
     */
    public void obterGeral(int idAtividade, int origem) {

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
                            showProgressBar(false);
                        }
                    }
            );


        avaliacaoAmbientalRepositorio.obterGeral(idAtividade, origem)
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
                                showProgressBar(false);
                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }
                );

    }


    /**
     * Metodo que permite obter as avaliacoes
     * @param id o identificador do relatorio
     * @param origem a origem do relatorio
     */
    public void obterAvalicoes(int id, int origem) {

        avaliacaoAmbientalRepositorio.obterAvaliacoes(id, origem)
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
                                avaliacaoAmbientalRepositorio.obterCategoriasProfissionais(registo.resultado.id, origem).toObservable(),
                                avaliacaoAmbientalRepositorio.obterMedidas(registo.resultado.id, origem).toObservable(),
                                new BiFunction<List<Tipo>, List<Tipo>, Object>() {
                                    @Override
                                    public Object apply(List<Tipo> categoriaProfissionalResultados, List<Tipo> medidaResultados) throws Exception {

                                        registo.categoriasProfissionais = categoriaProfissionalResultados;
                                        registo.medidas = medidaResultados;
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
                            public void onSubscribe(Disposable d)  {
                                disposables.add(d);
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
    }



    /**
     * Metodo que permite obter uma avaliacao
     * @param id o identificador da avaliacao
     * @param origem a origem do relatorio
     */
    public void obterAvalicao(int id, int origem) {

        showProgressBar(true);

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
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(TiposAvaliacao tiposAvaliacao) {
                        areas.setValue(tiposAvaliacao.areas);
                        iluminacao.setValue(tiposAvaliacao.tiposIluminacao);
                        elxArea.setValue(tiposAvaliacao.elxArea);
                        obterGeneros(generos);
                        showProgressBar(false);
                        obterAvaliacaoAmbiental(id, origem);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showProgressBar(false);
                    }
                }

        );

    }


    /**
     * Metodo que permite obter a avaliacao
     * @param id o identificador da avaliacao
     * @param origem a origem da avaliacao
     */
    private void obterAvaliacaoAmbiental(int id, int origem){
        if(id != -1) {

            avaliacaoAmbientalRepositorio.obterAvaliacao(id, origem)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new MaybeObserver<AvaliacaoAmbiental>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(AvaliacaoAmbiental avaliacaoAmbiental) {
                                    avaliacao.setValue(avaliacaoAmbiental);
                                    categoriasProfissionais.setValue(avaliacaoAmbiental.categoriasProfissionais);
                                    medidas.setValue(avaliacaoAmbiental.medidas);
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


    //------------------
    //Misc
    //------------------



    /**
     * Metodo que indica se existe um relatorio
     * @param idAtividade
     * @param origem
     */
    private void existeRelatorio(int idAtividade, int origem) {

        avaliacaoAmbientalRepositorio.existeRelatorio(idAtividade, origem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Boolean aBoolean) {
                                possuiRelatorio.setValue(aBoolean);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }



    /**
     * Metodo que permite obter o elx
     * @param id
     */
    public void obterElx(int id) {

        avaliacaoAmbientalRepositorio.obterElx(id)
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
                                elx.setValue(tipos);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }


    /**
     * Metodo que permite fixar as categorias profissionais
     * @param resultado
     */
    public void fixarCategoriasProfissionais(List<Integer> resultado) {

        avaliacaoAmbientalRepositorio.obterTipos_Incluir(resultado).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
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
     * Metodo que permite fixar as medidas
     * @param metodo
     * @param codigo
     * @param resultado
     */
    public void fixarMedidas(String metodo, String codigo, List<Integer> resultado) {

        avaliacaoAmbientalRepositorio.obterTipos_Incluir(metodo, codigo, resultado).toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Tipo> tipos) {
                                medidas.setValue(tipos);
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





    //-----------------
    //Classe
    //-----------------


    private class TiposAvaliacao{

        List<Tipo> areas;
        List<Tipo> tiposIluminacao;
        List<Tipo> elxArea;
    }


}
