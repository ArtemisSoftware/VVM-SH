package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AvaliacaoAmbientalDao;
import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.MedidaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.AvaliacaoAmbiental;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.RelatorioAmbiental;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function3;

public class AvaliacaoAmbientalRepositorio {

    private final AvaliacaoAmbientalDao avaliacaoAmbientalDao;
    private final CategoriaProfissionalDao categoriaProfissionalDao;
    private final MedidaDao medidaDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;
    private int api;

    public AvaliacaoAmbientalRepositorio(@NonNull int api, @NonNull AvaliacaoAmbientalDao avaliacaoAmbientalDao, @NonNull CategoriaProfissionalDao categoriaProfissionalDao,
                                         @NonNull MedidaDao medidaDao,
                                         @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.avaliacaoAmbientalDao = avaliacaoAmbientalDao;
        this.categoriaProfissionalDao = categoriaProfissionalDao;
        this.medidaDao = medidaDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;

        this.api = api;
    }

    /**
     * Metodo que permite inserir um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Long> inserir(RelatorioAmbientalResultado registo){
        return avaliacaoAmbientalDao.inserir(registo);
    }

    /**
     * Metodo que permite atualizar um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Integer> atualizar(RelatorioAmbientalResultado registo){
        return avaliacaoAmbientalDao.atualizar(registo);
    }


    public Single<Long> inserir(AvaliacaoAmbientalResultado registo){
        return avaliacaoAmbientalDao.inserir(registo);
    }


    public Single<Integer> atualizar(AvaliacaoAmbientalResultado registo){
        return avaliacaoAmbientalDao.atualizar(registo);
    }


    public Single<List<Long>> inserir(List<CategoriaProfissionalResultado> categorias) {
        return categoriaProfissionalDao.inserir(categorias);
    }



    /**
     * Metodo que permite obter os dados gerais do relatorio
     * @param idAtividade o identificador da atividade
     * @param origem a origem do relatorio
     * @return os dados
     */
    public Maybe<RelatorioAmbientalResultado> obterGeral(int idAtividade, int origem) {
        return avaliacaoAmbientalDao.obterGeral(idAtividade, origem);
    }


    public Single<List<Tipo>> obterNebulosidade(){
        return tipoDao.obterTipos_(TiposUtil.MetodosTipos.CONDICOES_CLIMATERICAS, api);
    }

    public Single<List<Tipo>> obterAreas(){
        return tipoDao.obterTipos_(TiposUtil.MetodosTipos.TIPOS_AREA, api);
    }

    public Single<List<Tipo>> obterTipoIluminacao(){
        return tipoDao.obterTipos_(TiposUtil.MetodosTipos.TIPOS_ILUMINACAO, api);
    }

    public Single<List<Tipo>> obterElxArea(){
        return avaliacaoAmbientalDao.obterElxArea();
    }

    public Single<List<Tipo>> obterElx(int id){
        return avaliacaoAmbientalDao.obterElx(id, api);
    }

    public Flowable<List<Tipo>> obterTipos_Incluir(List<Integer> resultado){
        return tipoDao.obterTipos_Incluir(TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS, resultado, api);
    }


    public Single<List<Tipo>> obterTipos_Incluir(String metodo, String codigo, List<Integer> resultado){
        return tipoDao.obterTipos_Incluir(metodo, codigo, api, resultado);
    }





    /**
     * Metodo que permite obter o relatorio
     * @param idAtividade o identificador da atividade
     * @param origem a origem do relatorio
     * @return os dados do relatorio
     */
    public Observable<RelatorioAmbiental> obterRelatorio(int idAtividade, int origem) {

        if(origem == Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO) {
            return avaliacaoAmbientalDao.obterRelatorioIlumiacao(idAtividade, origem);
        }
        else{
            return avaliacaoAmbientalDao.obterRelatorioTermico(idAtividade, origem);
        }
    }


    /**
     * Metodo que permite obter as avaliacoes
     * @param idRelatorio o identificador do relatorio
     * @param origem a origem do relatorio
     * @return os registos
     */
    public Observable<List<AvaliacaoAmbiental>> obterAvaliacoes(int idRelatorio, int origem) {

        if(origem == Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO) {

            return avaliacaoAmbientalDao.obterAvaliacoesIluminacao(idRelatorio);
        }
        else{
            return avaliacaoAmbientalDao.obterAvaliacoesTemperaturaHumidade(idRelatorio);
        }
    }


    /**
     * Metodo que permite obter as categorias profissionais
     * @param id o identificador do registo
     * @param origem a origem do relatorio
     * @return uma lista de tipos
     */
    public Maybe<List<Tipo>> obterCategoriasProfissionais(int id, int origem){
        return categoriaProfissionalDao.obterTipoCategoriasProfissionais(id, origem);
    }


    /**
     * Metodo que permite obter as medidas
     * @param id o identificador do registo
     * @param origem a origem do relatorio
     * @return uma lista de medidas
     */
    public Maybe<List<Tipo>> obterMedidas(int id, int origem){
        if(origem == Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO) {

            return medidaDao.obterTipoMedidas(id, TiposUtil.MetodosTipos.MEDIDAS_ILUMINACAO_TERMICO, Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO_MEDIDAS_RECOMENDADAS);
        }
        else{
            return medidaDao.obterTipoMedidas(id, TiposUtil.MetodosTipos.MEDIDAS_ILUMINACAO_TERMICO, Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE_MEDIDAS_RECOMENDADAS);
        }
    }







    /**
     * Metodo que permite obter a medida recomendada
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     * @return a medida
     */
    public Observable<String> obterMedidaRecomendada(int idAtividade, int tipo) {

        if(tipo == Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO) {

            return avaliacaoAmbientalDao.obterMedidaRecomendadaIluminacao(idAtividade, tipo);
        }
        else{
            return avaliacaoAmbientalDao.obterMedidaRecomendadaTermico(idAtividade, tipo);
        }
    }





    public Maybe<Boolean> obterValidadeAvaliacoes(int idAtividade, int tipo) {

        if(tipo == Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO) {

            return avaliacaoAmbientalDao.obterValidadeAvaliacoesIluminacao(idAtividade, tipo);
        }
        else{
            return avaliacaoAmbientalDao.obterValidadeAvaliacoesTemperaturaHumidade(idAtividade, tipo);
        }
    }






    /**
     * Metodo que permite obter uma avaliacao
     * @param id o identificador da avaliacao
     * @param origem a origem do relatorio
     * @return um registo
     */
    public Maybe<AvaliacaoAmbiental> obterAvaliacao(int id, int origem) {

        int origemMedidas = Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE_MEDIDAS_RECOMENDADAS;

        if(origem == Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO) {
            origemMedidas = Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO_MEDIDAS_RECOMENDADAS;
        }

        Maybe<AvaliacaoAmbiental> maybe = Maybe.zip(
                avaliacaoAmbientalDao.obterAvaliacao(id),
                categoriaProfissionalDao.obterTipoCategoriasProfissionais(id, origem),
                medidaDao.obterTipoMedidas(id, TiposUtil.MetodosTipos.MEDIDAS_ILUMINACAO_TERMICO, origemMedidas),
                new Function3<AvaliacaoAmbientalResultado, List<Tipo>, List<Tipo>, AvaliacaoAmbiental>() {
                    @Override
                    public AvaliacaoAmbiental apply(AvaliacaoAmbientalResultado avaliacaoAmbientalResultado, List<Tipo> categoriasProfissionais, List<Tipo> medidas) throws Exception {
                        AvaliacaoAmbiental resultado = new AvaliacaoAmbiental();

                        resultado.resultado = avaliacaoAmbientalResultado;
                        resultado.categoriasProfissionais = categoriasProfissionais;
                        resultado.medidas = medidas;

                        return resultado;
                    }
                });

        return maybe;
    }


    /**
     * Metodo que permite remover uma avaliacao
     * @param registo os dados da avaliacao
     * @return
     */
    public Single<Object> removerAvaliacao(AvaliacaoAmbientalResultado registo, int origem) {

        int origemMedidas = Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE_MEDIDAS_RECOMENDADAS;

        if(origem == Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO) {
            origemMedidas = Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO_MEDIDAS_RECOMENDADAS;
        }

        Single<Object> single = Single.zip(
                avaliacaoAmbientalDao.remover(registo),
                categoriaProfissionalDao.remover(registo.id, origem),
                medidaDao.remover(registo.id, origemMedidas),
                new Function3<Integer, Integer, Integer, Object>() {
                    @Override
                    public Object apply(Integer integer, Integer integer2, Integer integer3) throws Exception {
                        return integer & integer2 & integer3;
                    }
                });

        return single;
    }





    /**
     * Metodo que permite obter o identificador do relatorio
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     * @return o identificador
     */
    public Maybe<Integer> obterIdRelatorio(int idAtividade, int tipo) {
        return avaliacaoAmbientalDao.obterIdRelatorio(idAtividade, tipo);
    }


    /**
     * Metodo que indica a validade dos dados gerais
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     * @return true caso seja valido ou false caso contrario
     */
    public Maybe<Boolean> obterValidadeGeral(int idAtividade, int tipo) {
        return avaliacaoAmbientalDao.obterValidadeGeral(idAtividade, tipo);
    }




    public Flowable<Object> atualizarAvaliacao(AvaliacaoAmbientalResultado registo, List<CategoriaProfissionalResultado> categorias, List<MedidaResultado> medidas, int origem, int origemMedidas){

        Flowable<Object> single = Single.merge(Arrays.asList(avaliacaoAmbientalDao.atualizar(registo),
                categoriaProfissionalDao.remover(registo.id, origem),
                medidaDao.remover(registo.id, origemMedidas),
                categoriaProfissionalDao.inserir(categorias),
                medidaDao.inserir(medidas)));

        return single;

    }


    public Observable<Object> atualizarAvaliacao__(AvaliacaoAmbientalResultado registo){

       return Observable.zip(avaliacaoAmbientalDao.atualizar(registo).toObservable(), avaliacaoAmbientalDao.atualizar(registo).toObservable(), new BiFunction<Integer, Integer, Object>() {
           @Override
           public Object apply(Integer integer, Integer integer2) throws Exception {
               return null;
           }
       });

    }

    public Observable inserir(int idRegisto, List<Integer> categoriasProfissionais, List<Integer> medidas, int origem, int origemMedidas) {

        List<CategoriaProfissionalResultado> categorias = new ArrayList<>();

        for(int idCategoria : categoriasProfissionais){
            categorias.add(new CategoriaProfissionalResultado(idRegisto, idCategoria, origem));
        }

        List<MedidaResultado> registosMedidas = new ArrayList<>();

        for(int idMedida : medidas){
            registosMedidas.add(new MedidaResultado(idRegisto, origemMedidas, idMedida));
        }

        return Observable.zip(
                categoriaProfissionalDao.inserir(categorias).toObservable(),
                medidaDao.inserir(registosMedidas).toObservable(),
                new BiFunction<List<Long>, List<Long>, Object>() {
                    @Override
                    public Object apply(List<Long> longs, List<Long> longs2) throws Exception {
                        return longs;
                    }
                }
        );
    }
}
