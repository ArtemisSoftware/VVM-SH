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
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacaoAmbiental;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.RelatorioAmbiental;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

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


    public Single<List<Long>> inserir(List<CategoriaProfissionalResultado> categorias) {
        return categoriaProfissionalDao.inserir(categorias);
    }



    public Observable<RelatorioAmbiental> obterValidadeRelatorio(int idAtividade, int tipo) {
        return avaliacaoAmbientalDao.obterValidadeRelatorio(idAtividade, tipo);
    }




    /**
     * Metodo que permite obter os dados gerais do relatorio
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     * @return os dados
     */
    public Maybe<RelatorioAmbientalResultado> obterGeral(int idAtividade, int tipo) {
        return avaliacaoAmbientalDao.obterGeral(idAtividade, tipo);
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

    public Flowable<List<Tipo>> obterTipos_Incluir(ArrayList<Integer> resultado){
        return tipoDao.obterTipos_Incluir(TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS, resultado, api);
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



    /**
     * Metodo que permite obter as avaliacoes
     * @param idRelatorio o identificador do relatorio
     * @param tipo o tipo de relatorio
     * @return os registos
     */
    public Observable<List<AvaliacaoAmbiental>> obterAvaliacoes(int idRelatorio, int tipo) {

        if(tipo == Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO) {

            return avaliacaoAmbientalDao.obterAvaliacoesIluminacao(idRelatorio);
        }
        else{
            return avaliacaoAmbientalDao.obterAvaliacoesTemperaturaHumidade(idRelatorio);
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


    public Observable<List<CategoriaProfissionalResultado>> obterCategoriasProfissionais(int id, int tipo){
        if(tipo == Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO) {

            return categoriaProfissionalDao.obterCategoriasProfissionais(id, Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO);
        }
        else{
            return categoriaProfissionalDao.obterCategoriasProfissionais(id, Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE);
        }
    }


    public Observable<List<MedidaResultado>> obterMedidas(int id, int tipo){
        if(tipo == Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO) {

            return medidaDao.obterMedidas(id, Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO);
        }
        else{
            return medidaDao.obterMedidas(id, Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE);
        }
    }





    /**
     * Metodo que permite obter uma avaliacao
     * @param id o identificador da avaliacao
     * @return um registo
     */
    public Maybe<AvaliacaoAmbientalResultado> obterAvaliacao(int id) {
        return avaliacaoAmbientalDao.obterAvaliacao(id);
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



}
