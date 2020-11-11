package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.PropostaPlanoAcaoResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.LevantamentoAvaliacaoRepositorio;
import com.vvm.sh.repositorios.LevantamentoRepositorio;
import com.vvm.sh.servicos.levantamentos.DuplicarLevantamentoAsyncTask;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.CategoriaProfissional;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.RelatorioLevantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Risco;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.excepcoes.ModeloException;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.TiposUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
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
import io.reactivex.functions.Function5;
import io.reactivex.schedulers.Schedulers;

public class LevantamentosViewModel extends BaseViewModel {

    private final LevantamentoRepositorio levantamentoRepositorio;
    private final LevantamentoAvaliacaoRepositorio levantamentoAvaliacaoRepositorio;
    public MutableLiveData<List<Tipo>> modelos;


    public MutableLiveData<List<Levantamento>> levantamentos;

    public MutableLiveData<RelatorioLevantamento> relatorio;

    public MutableLiveData<LevantamentoRiscoResultado> levantamento;

    public MutableLiveData<List<CategoriaProfissional>> categoriasProfissionais;


    public MutableLiveData<List<Risco>> riscos;
    public MutableLiveData<Risco> risco;

    private List<Risco> resultadosRiscos;

    public MutableLiveData<List<Tipo>> tiposRiscos;
    public MutableLiveData<List<Tipo>> tipoRiscoEspecifico;
    public MutableLiveData<List<Tipo>> tiposNd;
    public MutableLiveData<List<Tipo>> tiposNe;
    public MutableLiveData<List<Tipo>> tiposNc;
    public List<Tipo> tiposNi;
    public MutableLiveData<List<Tipo>> tiposCategoriasProfissionais;

    public MutableLiveData<List<Tipo>> medidasRecomendadas;
    public MutableLiveData<List<Tipo>> medidasExistentes;
    public MutableLiveData<byte[]> imagem;

    @Inject
    public LevantamentosViewModel(LevantamentoRepositorio levantamentoRepositorio, LevantamentoAvaliacaoRepositorio levantamentoAvaliacaoRepositorio){

        this.levantamentoRepositorio = levantamentoRepositorio;
        this.levantamentoAvaliacaoRepositorio = levantamentoAvaliacaoRepositorio;

        levantamentos = new MutableLiveData<>();
        relatorio = new MutableLiveData<>();
        levantamento = new MutableLiveData<>();
        categoriasProfissionais = new MutableLiveData<>();
        tiposCategoriasProfissionais = new MutableLiveData<>();
        modelos = new MutableLiveData<>();
        riscos = new MutableLiveData<>();
        risco = new MutableLiveData<>();


        tiposRiscos = new MutableLiveData<>();
        tipoRiscoEspecifico = new MutableLiveData<>();
        tiposNd = new MutableLiveData<>();
        tiposNe = new MutableLiveData<>();
        tiposNc = new MutableLiveData<>();
        tiposNi = new ArrayList<>();


        resultadosRiscos = new ArrayList<>();
        medidasRecomendadas = new MutableLiveData<>();
        medidasExistentes = new MutableLiveData<>();
        imagem = new MutableLiveData<>();

    }


    public MutableLiveData<List<Tipo>> observarTiposCategorias(){
        return tiposCategoriasProfissionais;
    }

    public MutableLiveData<List<Tipo>> observarRiscos(){
        return tiposRiscos;
    }

    public MutableLiveData<Risco> observarRisco(){
        return risco;
    }

    //-------------------
    //GRAVAR
    //------------------


    /**
     * Metodo que permite gravar um registo
     * @param registo os dados
     */
    public void gravar(int idTarefa, LevantamentoRiscoResultado registo){

        if(levantamento.getValue() == null){

            levantamentoRepositorio.inserir(registo)
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
                                    messagemLiveData.setValue(Recurso.successo(ConversorUtil.converter_long_Para_int(aLong), Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                    abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, registo.idAtividade);
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
                                    abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, registo.idAtividade);
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
     * MEtodo que permite gravar as categorias profissionais
     * @param idTarefa
     * @param idAtividade
     * @param idRegisto
     * @param resultado uma lista de identificadores de categorias profissionais
     */
    public void gravarCategoriasProfissionais(int idTarefa, int idAtividade, int idRegisto, ArrayList<Integer> resultado) {

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
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Long> longs) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, idAtividade);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }



    /**
     * Metodo que permite atualizar uma categoria profissional
     * @param registo os dados a atualizar
     */
    public void atualizarCategoriaProfissional(int idTarefa, int idAtividade, CategoriaProfissionalResultado registo) {

        levantamentoRepositorio.atualizar(registo)
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
                                abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, idAtividade);

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }


    /**
     * Metodo que permite gravar um risco
     * @param idTarefa
     * @param idAtividade
     * @param registo
     * @param medidasExistentes
     * @param medidasRecomendadas
     */
    public void gravar(int idTarefa, int idAtividade, RiscoResultado registo, List<Integer> medidasExistentes, List<Integer> medidasRecomendadas) {

        if(risco.getValue() == null){

            levantamentoRepositorio.inserir(registo)
                    .flatMap(new Function<Long, SingleSource<?>>() {
                        @Override
                        public SingleSource<?> apply(Long aLong) throws Exception {
                            int idRegisto = ConversorUtil.converter_long_Para_int(aLong);

                            ImagemResultado imagemResultado = new ImagemResultado(idTarefa, idRegisto, Identificadores.Imagens.IMAGEM_RISCO, imagem.getValue());

                            return Single.fromObservable(levantamentoRepositorio.inserir(idAtividade, idRegisto, medidasExistentes, medidasRecomendadas, imagemResultado));
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
                                    abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, idAtividade);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }
                    );
        }
        else{

            registo.id = risco.getValue().resultado.id;


            List<MedidaResultado> medidasRegistas = new ArrayList<>();
            List<PropostaPlanoAcaoResultado> propostasRegistas = new ArrayList<>();

            for (int idMedida : medidasExistentes) {
                medidasRegistas.add(new MedidaResultado(registo.id, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS, idMedida));
            }


            for (int idMedida : medidasRecomendadas) {
                medidasRegistas.add(new MedidaResultado(registo.id, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS, idMedida));
                propostasRegistas.add(new PropostaPlanoAcaoResultado(idAtividade, registo.id, idMedida));
            }



            ImagemResultado imagemResultado = new ImagemResultado(idTarefa, registo.id, Identificadores.Imagens.IMAGEM_RISCO, imagem.getValue());



            Disposable d = levantamentoRepositorio.atualizarRisco(registo, medidasRegistas, propostasRegistas, imagemResultado)
                    .toList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Consumer<Object>() {
                                @Override
                                public void accept(Object o) throws Exception {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                                    abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, idAtividade);
                                }
                            }
                    );

            disposables.add(d);

        }

    }


    /**
     * Metodo que permite duplicar um levantamento
     * @param idTarefa
     * @param levantamento os dados do levantamento
     */
    public void duplicarLevantamento(int idTarefa, LevantamentoRiscoResultado levantamento) {

        LevantamentoRiscoResultado resultado = new LevantamentoRiscoResultado(levantamento);

        levantamentoRepositorio.inserir(resultado)
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

                                abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, levantamento.idAtividade);
                                DuplicarLevantamentoAsyncTask servico = new DuplicarLevantamentoAsyncTask(vvmshBaseDados, levantamentoAvaliacaoRepositorio, levantamento);
                                servico.execute(ConversorUtil.converter_long_Para_int(aLong));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }




    /**
     * Metodo que permite inserir um modelo
     * @param idTarefa o identificador da tarefa
     * @param idAtividade
     * @param idModelo o identificador do modelo
     */
    public void inserirModelo(int idTarefa, int idAtividade, int idModelo) {

        limite = AppConfig.NUMERO_RESULTADOS_QUERY;
        levantamentos.setValue(new ArrayList<>());

        levantamentoRepositorio.inserirModelo(idAtividade, idModelo)
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
                                abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, idAtividade);
                                messagemLiveData.setValue(Recurso.successo(1, Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }


    /**
     * Metodo que permite inserir o modelo de categorias profissionais
     * @param idAtividade
     * @param idModelo
     * @param resultado
     */
    public void inserirModeloCategoriasProfissionais(int idTarefa, int idAtividade, int idModelo, List<CategoriaProfissionalResultado> resultado) {

        levantamentoRepositorio.inserirModeloCategoriasProfissionais(idAtividade, idModelo, resultado)
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

                                abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, idAtividade);
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );

    }


    //--------------------
    //REMOVER
    //--------------------

    public void removerLevantamento(int idTarefa, Levantamento levantamento) {

        levantamentoRepositorio.removerLevantamento(levantamento)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Integer>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Integer> integers) {
                                abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, levantamento.resultado.idAtividade);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }


    public void removerLevantamentos(int idTarefa, int idAtividade, List<Integer> idsLevantamento) {

        levantamentoRepositorio.removerLevantamentos(idsLevantamento)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Integer>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Integer> integers) {
                                abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, idAtividade);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }


    /**
     * Metodo que permite remover um risco
     * @param idTarefa
     * @param idAtividade
     * @param registo
     */
    public void removerRisco(int idTarefa, int idAtividade, Risco registo) {

        riscos.setValue(new ArrayList<>());
        resultadosRiscos.clear();

        levantamentoRepositorio.removerRisco(registo.resultado)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Integer>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Integer> integers) {
                                abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, idAtividade);
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_REMOVIDOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }


    /**
     * Metodo que permite remover uma categoria profissional
     * @param idTarefa o identificador da tarefa
     * @param idAtividade o identificador da atividade
     * @param categoria a categoria profissional
     */
    public void remover(int idTarefa, int idAtividade, CategoriaProfissionalResultado categoria) {

        levantamentoRepositorio.remover(categoria)
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
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_REMOVIDOS_SUCESSO));
                                abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, idAtividade);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );

    }

    /**
     * Metodo que permite remover um modelo
     * @param idTarefa
     * @param idAtividade
     * @param idModelo
     */
    public void removerModelo(int idTarefa, int idAtividade, int idModelo) {

        limite = AppConfig.NUMERO_RESULTADOS_QUERY;
        levantamentos.setValue(new ArrayList<>());

        levantamentoRepositorio.removerModelo(idAtividade, idModelo)
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
                                abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, idAtividade);
                                messagemLiveData.setValue(Recurso.successo(2, Sintaxe.Frases.DADOS_REMOVIDOS_SUCESSO));
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

    public void obterRegistos(int idAtividade, boolean reiniciar){

        if(reiniciar == true){
            limite = AppConfig.NUMERO_RESULTADOS_QUERY;
            levantamentos.setValue(new ArrayList<>());
        }

        obterLevantamentos(idAtividade);
    }

    public void carregarRegistos(int idAtividade){
        limite += AppConfig.NUMERO_RESULTADOS_QUERY;
        obterLevantamentos(idAtividade);
    }



    /**
     * Metodo que permite obter os levantamentos
     * @param idAtividade o identificador da atividade
     */
    private void obterLevantamentos(int idAtividade) {

        levantamentoRepositorio.obterLevantamentos(idAtividade, limite)
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
     * Metodo que permite obter o relatorio do levantamento
     * @param id o identificador do levantamento
     */
    public void obterRelatorio(int id) {

//        RelatorioLevantamento registo = new RelatorioLevantamento();
//        relatorio.setValue(registo);

        levantamentoRepositorio.obterRelatorio(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<RelatorioLevantamento>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
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



    //------------------------
    //OBTER
    //------------------------


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
                                disposables.add(d);
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


    /**
     * Metodo que permite obter as categorias profissionais
     * @param id o identificador do levantamento
     */
    public void obterCategoriasProfissionais(int id) {

        levantamentoRepositorio.obterCategoriasProfissionais(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<CategoriaProfissional>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
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


    /**
     * Metodo que permite obter os riscos
     * @param id o identificador do levantamento
     */
    public void obteRiscos(int id) {

        levantamentoRepositorio.obterRiscos(id)
                .flatMap(new Function<List<Risco>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(List<Risco> riscos) throws Exception {

                        return Observable.fromIterable(riscos);
                    }
                })
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {
                        Risco registo = (Risco) o;

                        Observable<Risco> observables = Observable.zip(
                                levantamentoRepositorio.obterMedidas(registo.resultado.id, TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_ADOPTADAS, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS).toObservable(),
                                levantamentoRepositorio.obterMedidas(registo.resultado.id, TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS).toObservable(),
                                new BiFunction<List<Tipo>, List<Tipo>, Risco>() {
                                    @Override
                                    public Risco apply(List<Tipo> medidasExistentes, List<Tipo> medidasRecomendadas) throws Exception {

                                        registo.medidasExistentes = medidasExistentes;
                                        registo.medidasRecomendadas = medidasRecomendadas;
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
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(Object o) {
                                resultadosRiscos.add((Risco) o);
                                List<Risco> registos = new ArrayList<>();

                                for (Risco item : resultadosRiscos) {

                                    for(int index = 0; index < registos.size(); ++index){

                                        if(registos.get(index).resultado.id == item.resultado.id){
                                            registos.remove(index);
                                            break;
                                        }
                                    }
                                    registos.add(item);
                                }

                                riscos.setValue(registos);
                            }

                            @Override
                            public void onError(Throwable e) {
                                riscos.setValue(new ArrayList<>());
                            }

                            @Override
                            public void onComplete() {

                            }
                        }


                );
    }


    /**
     * Metodo que permite obter os dados do risco
     * @param id o identificador do risco
     */
    public void obterLevamentoRisco(int id) {

        Single.zip(levantamentoRepositorio.obterTipos(TiposUtil.MetodosTipos.RISCOS),
                levantamentoRepositorio.obterTipos(TiposUtil.MetodosTipos.TIPOS_NC),
                levantamentoRepositorio.obterTipos(TiposUtil.MetodosTipos.TIPOS_ND),
                levantamentoRepositorio.obterTipos(TiposUtil.MetodosTipos.TIPOS_NE),
                levantamentoRepositorio.obterTipos(TiposUtil.MetodosTipos.TIPOS_NI),
                new Function5<List<Tipo>, List<Tipo>, List<Tipo>, List<Tipo>, List<Tipo>, TiposRiscos>() {
                    @Override
                    public TiposRiscos apply(List<Tipo> riscos, List<Tipo> nc, List<Tipo> nd, List<Tipo> ne, List<Tipo> ni) throws Exception {

                        TiposRiscos tipos = new TiposRiscos();
                        tipos.riscos = riscos;
                        tipos.nc = nc;
                        tipos.nd = nd;
                        tipos.ne = ne;
                        tipos.ni = ni;
                        return tipos;
                    }
                }
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(

                new SingleObserver<TiposRiscos>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(TiposRiscos registo) {
                        tiposNc.setValue(registo.nc);
                        tiposNd.setValue(registo.nd);
                        tiposNe.setValue(registo.ne);
                        tiposNi = registo.ni;
                        tiposRiscos.setValue(registo.riscos);
                        obterRisco(id);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }

        );

    }


    /**
     * Metodo que permite obter um risco
     * @param id o identificador do risco
     */
    private void obterRisco(int id) {

        levantamentoRepositorio.obterRisco(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<Risco>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Risco registo) {
                                medidasExistentes.setValue(registo.medidasExistentes);
                                medidasRecomendadas.setValue(registo.medidasRecomendadas);

                                if(registo.imagem.size() != 0) {
                                    imagem.setValue(registo.imagem.get(0).imagem);
                                }
                                else{
                                    imagem.setValue(new byte[]{});
                                }
                                risco.setValue(registo);
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

    /**
     * Metodo que permite obter as categorias profissionais
     * @param ids
     */
    public void obterTiposCategoriasProfissionais(List<Integer> ids){

        levantamentoRepositorio.obterTipoCategoriasProfissionais(ids)
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
                                tiposCategoriasProfissionais.setValue(tipos);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );

    }


    /**
     * Metodo que permite obter o risco especifico
     * @param id o identificador do risco
     */
    public void obteRiscoEspecifico(int id) {

        levantamentoRepositorio.obterTipos(TiposUtil.MetodosTipos.RISCOS_ESPECIFICOS, id)
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
                                tipoRiscoEspecifico.setValue(tipos);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }


    /**
     * Metodo que permite obter os modelos
     * @param idAtividade
     */
    public void obterModelos(int idAtividade){

        levantamentoRepositorio.obterModelos(idAtividade)
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
                                modelos.setValue(tipos);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );
    }



    /**
     * Metodo que permite fixar as medidas
     * @param metodo
     * @param resultado
     */
    public void fixarMedidasRecomendadas(String metodo, List<Integer> resultado) {

        levantamentoRepositorio.obterTipos_Incluir(metodo, resultado).toObservable()
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
                                medidasRecomendadas.setValue(tipos);
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


    public void fixarMedidasExistentes(String metodo, List<Integer> resultado) {

        levantamentoRepositorio.obterTipos_Incluir(metodo, resultado).toObservable()
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
                                medidasExistentes.setValue(tipos);
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




    private class TiposRiscos{

        List<Tipo> riscos;
        List<Tipo> nd;
        List<Tipo> ne;
        List<Tipo> nc;
        List<Tipo> ni;
    }


}
