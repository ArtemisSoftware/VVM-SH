package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.LevantamentoRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.AvaliacaoAmbiental;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.CategoriaProfissional;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.RelatorioLevantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Risco;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.TiposUtil;
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
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.schedulers.Schedulers;

public class LevantamentosViewModel extends BaseViewModel {

    private final LevantamentoRepositorio levantamentoRepositorio;
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

    public MutableLiveData<List<Tipo>> medidasRecomendadas;
    public MutableLiveData<List<Tipo>> medidasExistentes;

    @Inject
    public LevantamentosViewModel(LevantamentoRepositorio levantamentoRepositorio){

        this.levantamentoRepositorio = levantamentoRepositorio;
        levantamentos = new MutableLiveData<>();
        relatorio = new MutableLiveData<>();
        levantamento = new MutableLiveData<>();
        categoriasProfissionais = new MutableLiveData<>();
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




    public void gravar(int idTarefa, int idAtividade, RiscoResultado registo, List<Integer> medidasExistentes, List<Integer> medidasRecomendadas) {


        if(risco.getValue() == null){

            levantamentoRepositorio.inserir(registo)
                    .flatMap(new Function<Long, SingleSource<?>>() {
                        @Override
                        public SingleSource<?> apply(Long aLong) throws Exception {
                            int idRegisto = ConversorUtil.converter_long_Para_int(aLong);
                            return Single.fromObservable(levantamentoRepositorio.inserir(idRegisto, medidasExistentes, medidasRecomendadas));
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

//            registo.id = risco.getValue().id;
//
//
//            List<MedidaResultado> medidasExistentesRegistas = new ArrayList<>();
//
//            if(medidasExistentes == null){
//
//                for (Tipo medida : risco.getValue().medidasExistentes) {
//                    medidasExistentesRegistas.add(new MedidaResultado(registo.id, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS, medida.id));
//                }
//            }
//            else {
//
//                for (int idMedida : medidasExistentes) {
//                    medidasExistentesRegistas.add(new MedidaResultado(registo.id, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS, idMedida));
//                }
//            }
//
//
//            List<MedidaResultado> medidasRecomendadasRegistas = new ArrayList<>();
//
//            if(medidasExistentes == null){
//
//                for (Tipo medida : risco.getValue().medidasRecomendadas) {
//                    medidasRecomendadasRegistas.add(new MedidaResultado(registo.id, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS, medida.id));
//                }
//            }
//            else {
//
//                for (int idMedida : medidasRecomendadas) {
//                    medidasRecomendadasRegistas.add(new MedidaResultado(registo.id, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS, idMedida));
//                }
//            }
//
//
//
//            Disposable d = levantamentoRepositorio.atualizarRisco(registo, medidasExistentesRegistas, medidasRecomendadasRegistas)
//                    .toList()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//
//                            new Consumer<Object>() {
//                                @Override
//                                public void accept(Object o) throws Exception {
//                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
//                                    abaterAtividadePendente(levantamentoRepositorio.resultadoDao, idTarefa, idAtividade);
//                                }
//                            }
//                    );
//
//            disposables.add(d);


        }

    }




    //--------------------
    //REMOVER
    //--------------------

    public void remover(int idTarefa, Levantamento levantamento) {

        levantamentoRepositorio.remover(levantamento)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new FlowableSubscriber<Integer>() {
                            @Override
                            public void onSubscribe(Subscription s) {

                            }

                            @Override
                            public void onNext(Integer integer) {

                            }

                            @Override
                            public void onError(Throwable t) {

                            }

                            @Override
                            public void onComplete() {

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

                            }

                            @Override
                            public void onComplete() {

                            }
                        }


                );
    }


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


    public void obteRiscoEspecifico(int id) {

        levantamentoRepositorio.obterTipos(TiposUtil.MetodosTipos.RISCOS_ESPECIFICOS, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

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
