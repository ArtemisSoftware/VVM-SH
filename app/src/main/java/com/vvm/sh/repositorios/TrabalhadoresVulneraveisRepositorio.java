package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhadoresVulneraveisDao;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.modelos.TrabalhadorVulneravel;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function5;

public class TrabalhadoresVulneraveisRepositorio implements Repositorio<TrabalhadorVulneravelResultado> {

    private final int idApi;
    private final TrabalhadoresVulneraveisDao trabalhadoresVulneraveisDao;
    private final CategoriaProfissionalDao categoriaProfissionalDao;
    public final ResultadoDao resultadoDao;

    public TrabalhadoresVulneraveisRepositorio(int idApi,
                                               @NonNull TrabalhadoresVulneraveisDao trabalhadoresVulneraveisDao, @NonNull CategoriaProfissionalDao categoriaProfissionalDao,
                                               @NonNull ResultadoDao resultadoDao) {

        this.idApi = idApi;
        this.trabalhadoresVulneraveisDao = trabalhadoresVulneraveisDao;
        this.categoriaProfissionalDao = categoriaProfissionalDao;
        this.resultadoDao = resultadoDao;
    }

    public Single<Integer> atualizar(TrabalhadorVulneravelResultado registo, List<CategoriaProfissionalResultado> homens, List<CategoriaProfissionalResultado> mulheres) {

        Single<Integer> single = Single.zip(trabalhadoresVulneraveisDao.atualizar(registo),
                categoriaProfissionalDao.remover(registo.id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_HOMENS),
                categoriaProfissionalDao.remover(registo.id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_MULHERES),
                categoriaProfissionalDao.inserir(homens),
                categoriaProfissionalDao.inserir(mulheres),
                new Function5<Integer, Integer, Integer, List<Long>, List<Long>, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2, Integer integer3, List<Long> longs, List<Long> longs2) throws Exception {
                        return integer;
                    }
                });

        return single;
    }



    @Override
    public Single<Long> inserir(TrabalhadorVulneravelResultado item) {
        return trabalhadoresVulneraveisDao.inserir(item);
    }

    @Override
    public Single<Integer> atualizar(TrabalhadorVulneravelResultado item) {
        return trabalhadoresVulneraveisDao.atualizar(item);
    }


    public Completable validarVulnerabilidades(int idAtividade){
        return trabalhadoresVulneraveisDao.inserirVulnerabilidades(idAtividade, idApi);
    }

    public Observable<Object> obterVulnerabilidades(int idAtividade){

        Observable<Object> observable = trabalhadoresVulneraveisDao.obterVulnerabilidades(idAtividade, idApi)
                .flatMap(new Function<List<TrabalhadorVulneravel>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(List<TrabalhadorVulneravel> trabalhadorVulneravels) throws Exception {
                        return Observable.fromIterable(trabalhadorVulneravels);
                    }
                })
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {

                        TrabalhadorVulneravel registo = ((TrabalhadorVulneravel) o);

                        Observable<Object> observables = Observable.zip(
                                categoriaProfissionalDao.obterTipoCategoriasProfissionais(registo.resultado.id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_HOMENS).toObservable(),
                                categoriaProfissionalDao.obterTipoCategoriasProfissionais(registo.resultado.id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_MULHERES).toObservable(),
                                new BiFunction<List<Tipo>, List<Tipo>, Object>() {
                                    @Override
                                    public Object apply(List<Tipo> categoriasProfissionaisHomens, List<Tipo> categoriasProfissionaisMulheres) throws Exception {

                                        registo.categoriasProfissionaisHomens = categoriasProfissionaisHomens;
                                        registo.categoriasProfissionaisMulheres = categoriasProfissionaisMulheres;
                                        return registo;
                                    }
                                }
                        );


                        return observables;
                    }
                });


        return observable;
//
//                .filter(new
//                                Predicate<Object>() {
//                                    @Override
//                                    public boolean test(Object o) throws Exception {
//                                        return true;
//                                    }
//                                })
//                .toList()
//                .toObservable()
//                ;
//                .map(new Function<List<TrabalhadorVulneravel>, Object>() {
//                    @Override
//                    public Object apply(List<TrabalhadorVulneravel> trabalhadorVulneravels) throws Exception {
//                        return Observable.fromIterable(trabalhadorVulneravels);
//                    }
//                })
                /*
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Exception {
                        TrabalhadorVulneravel registo = ((TrabalhadorVulneravel) o);

                        return Maybe.zip(
                                trabalhadoresVulneraveisDao.obterVulnerabilidade(registo.resultado.id, idApi).toMaybe(),
                                categoriaProfissionalDao.obterTipoCategoriasProfissionais(registo.resultado.id, Identificadores.Origens.CATEGORIAS_PROFISSIONAIS_VULNERABILIDADE_HOMENS),
                                categoriaProfissionalDao.obterTipoCategoriasProfissionais(registo.resultado.id, Identificadores.Origens.CATEGORIAS_PROFISSIONAIS_VULNERABILIDADE_MULHERES),
                                new Function3<TrabalhadorVulneravel, List<Tipo>, List<Tipo>, Object>() {
                                    @Override
                                    public Object apply(TrabalhadorVulneravel trabalhadorVulneravel, List<Tipo> categoriasProfissionaisHomens, List<Tipo> categoriasProfissionaisMulheres) throws Exception {

                                        trabalhadorVulneravel.categoriasProfissionaisHomens = categoriasProfissionaisHomens;
                                        trabalhadorVulneravel.categoriasProfissionaisMulheres = categoriasProfissionaisMulheres;
                                        return trabalhadorVulneravel;
                                    }
                                }
                        );
                    }
                })
                */
                /*
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {

                        TrabalhadorVulneravel registo = ((TrabalhadorVulneravel) o);

                        return Maybe.zip(
                                trabalhadoresVulneraveisDao.obterVulnerabilidade(registo.resultado.id, idApi).toMaybe(),
                                categoriaProfissionalDao.obterTipoCategoriasProfissionais(registo.resultado.id, Identificadores.Origens.CATEGORIAS_PROFISSIONAIS_VULNERABILIDADE_HOMENS),
                                categoriaProfissionalDao.obterTipoCategoriasProfissionais(registo.resultado.id, Identificadores.Origens.CATEGORIAS_PROFISSIONAIS_VULNERABILIDADE_MULHERES),
                                new Function3<TrabalhadorVulneravel, List<Tipo>, List<Tipo>, Object>() {
                                    @Override
                                    public Object apply(TrabalhadorVulneravel trabalhadorVulneravel, List<Tipo> categoriasProfissionaisHomens, List<Tipo> categoriasProfissionaisMulheres) throws Exception {

                                        trabalhadorVulneravel.categoriasProfissionaisHomens = categoriasProfissionaisHomens;
                                        trabalhadorVulneravel.categoriasProfissionaisMulheres = categoriasProfissionaisMulheres;
                                        return trabalhadorVulneravel;
                                    }
                                }
                        ).toObservable();


                    }
                })*/
//                .flatMap(new Function<Object, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Object o) throws Exception {
//
//                        TrabalhadorVulneravel registo = ((TrabalhadorVulneravel) o);
//
//                        return Maybe.zip(
//                                trabalhadoresVulneraveisDao.obterVulnerabilidade(registo.resultado.id, idApi).toMaybe(),
//                                trabalhadoresVulneraveisDao.obterVulnerabilidade(registo.resultado.id, idApi).toMaybe(),
//                                trabalhadoresVulneraveisDao.obterVulnerabilidade(registo.resultado.id, idApi).toMaybe(),
//                                new Function3<TrabalhadorVulneravel, TrabalhadorVulneravel, TrabalhadorVulneravel, Object>() {
//                                    @Override
//                                    public Object apply(TrabalhadorVulneravel trabalhadorVulneravel, TrabalhadorVulneravel trabalhadorVulneravelHomens, TrabalhadorVulneravel trabalhadorVulneravelMulheres) throws Exception {
//
//                                        //trabalhadorVulneravel.categoriasProfissionaisHomens = categoriasProfissionaisHomens;
//                                        //trabalhadorVulneravel.categoriasProfissionaisMulheres = categoriasProfissionaisMulheres;
//                                        return trabalhadorVulneravel;
//                                    }
//                                }
//                        ).toObservable();
//
//
//                    }
//                })
                //.toList()
                //.toObservable();



        //return trabalhadoresVulneraveisDao.obterVulnerabilidades(idAtividade, idApi);
    }

    public Observable<List<TrabalhadorVulneravel>> obterVulnerabilidades__(int idAtividade){
        return trabalhadoresVulneraveisDao.obterVulnerabilidades(idAtividade, idApi);
    }


    /**
     * Metodo que permite obter a vulnerabilidade
     * @param id o identificador do registo da vulnerabilidade
     * @return uma vulnerabilidade
     */
    public Maybe<TrabalhadorVulneravel> obterVulnerabilidade(int id){

        Maybe<TrabalhadorVulneravel> maybe = Maybe.zip(trabalhadoresVulneraveisDao.obterVulnerabilidade(id, idApi).toMaybe(),
                categoriaProfissionalDao.obterTipoCategoriasProfissionais(id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_HOMENS),
                categoriaProfissionalDao.obterTipoCategoriasProfissionais(id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_MULHERES),

                new Function3<TrabalhadorVulneravel, List<Tipo>, List<Tipo>, TrabalhadorVulneravel>() {
                    @Override
                    public TrabalhadorVulneravel apply(TrabalhadorVulneravel trabalhadorVulneravel, List<Tipo> categoriasProfissionaisHomens, List<Tipo> categoriasProfissionaisMulheres) throws Exception {

                        trabalhadorVulneravel.categoriasProfissionaisHomens = categoriasProfissionaisHomens;
                        trabalhadorVulneravel.categoriasProfissionaisMulheres = categoriasProfissionaisMulheres;

                        return trabalhadorVulneravel;
                    }
                }

        );

        return  maybe;
    }





    public Single<List<Tipo>> obterTiposCategorias(List<Integer> resultado){
        return categoriaProfissionalDao.obterTiposCategorias(resultado, idApi);
    }


    @Override
    public Single<Integer> remover(TrabalhadorVulneravelResultado item) {

        Single<Integer> single = Single.zip(trabalhadoresVulneraveisDao.atualizar(item),
                categoriaProfissionalDao.remover(item.id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_HOMENS),
                categoriaProfissionalDao.remover(item.id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_MULHERES),
                new Function3<Integer, Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2, Integer integer3) throws Exception {
                        return integer;
                    }
                });


        return single;
    }


}
