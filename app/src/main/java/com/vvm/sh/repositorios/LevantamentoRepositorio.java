package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.LevantamentoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.CategoriaProfissional;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.RelatorioLevantamento;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class LevantamentoRepositorio {


    private final int idApi;
    private final LevantamentoDao levantamentoDao;
    private final CategoriaProfissionalDao categoriaProfissionalDao;

    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;


    public LevantamentoRepositorio(int idApi, @NonNull LevantamentoDao levantamentoDao, @NonNull CategoriaProfissionalDao categoriaProfissionalDao,
                                   @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.idApi = idApi;

        this.levantamentoDao = levantamentoDao;
        this.categoriaProfissionalDao = categoriaProfissionalDao;


        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }

    /**
     * Metodo que permite inserir um registo
     * @param registo os dados
     * @return o resultado da operação
     */
    public Single<Long> inserir(LevantamentoRiscoResultado registo){
        return levantamentoDao.inserir(registo);
    }

    /**
     * Metodo que permite atualizar um registo
     * @param registo os dados
     * @return o resultado da operação
     */
    public Single<Integer> atualizar(LevantamentoRiscoResultado registo){
        return levantamentoDao.atualizar(registo);
    }


    /**
     * Metodo que permite inserir registos
     * @param registos os dados
     * @return o resultado da operação
     */
    public Single<List<Long>> inserir(List<CategoriaProfissionalResultado> registos){
        return categoriaProfissionalDao.inserir(registos);
    }

    /**
     * Metodo que permite atualizar um registo
     * @param registo os dados
     * @return o resultado da operação
     */
    public Single<Integer> atualizar(CategoriaProfissionalResultado registo){
        return categoriaProfissionalDao.atualizar(registo);
    }



    /**
     * Metodo que permite obter os levantamentos
     * @param idAtividade o identificador da atividade
     * @return uma lista de registos
     */
    public Observable<List<Levantamento>> obterLevantamentos(int idAtividade){
        return levantamentoDao.obterLevantamentos(idAtividade);
    }

    /**
     * Metodo que permite obter o levantamento
     * @param id o identificador do levantamento
     * @return um registo
     */
    public Maybe<LevantamentoRiscoResultado> obterLevantamento(int id){
        return levantamentoDao.obterLevantamento(id);
    }

    /**
     * Metodo que permite obter as categorias profissionais
     * @param id o identificador do registo ao qual as categorias profissionais pertencem
     * @return os registos
     */
    public Observable<List<CategoriaProfissional>> obterCategoriasProfissionais(int id){
        return categoriaProfissionalDao.obterCategoriasProfissionais(id, idApi, Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS) ;
    }


    public Observable<RelatorioLevantamento> obterRelatorio(int id){
        return levantamentoDao.obterRelatorio(id);
    }


    /**
     * Metodo que permite obter a listagem de modelos
     * @return uma lista
     */
    public Single<List<Tipo>> obterModelos(int idAtividade){
        return levantamentoDao.obterModelos(idAtividade, TiposUtil.MetodosTipos.TEMPLATE_AVALIACAO_RISCOS, idApi);
    }

    public Single<Integer> remover(CategoriaProfissionalResultado categoria) {
        return categoriaProfissionalDao.remover(categoria);
    }
}
