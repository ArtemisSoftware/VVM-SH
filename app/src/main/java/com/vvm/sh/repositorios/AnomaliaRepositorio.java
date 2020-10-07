package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AnomaliaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaRegistada;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class AnomaliaRepositorio implements Repositorio<AnomaliaResultado>{

    private final int idApi;
    private final AnomaliaDao anomaliaDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public AnomaliaRepositorio(int idApi, @NonNull AnomaliaDao anomaliaDao,
                               @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.idApi = idApi;
        this.anomaliaDao = anomaliaDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }


    /**
     * Metodo que permite obter as anomalias existentes
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Single<List<Anomalia>> obterAnomalias(int idTarefa) {
        return anomaliaDao.obterAnomalias(idTarefa);
    }


    /**
     * Metodo que permite obter as anomalias registadas
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Observable<List<AnomaliaRegistada>> obterAnomaliasRegistadas(int idTarefa) {
        return anomaliaDao.obterAnomaliasRegistadas(idTarefa, idApi);
    }


    /**
     * Metodo que permite obter uma anomalia registada
     * @param id o identificador da anomalia
     * @return uma anomalia
     */
    public Maybe<AnomaliaRegistada> obterAnomaliaRegistada(int id) {
        return anomaliaDao.obterAnomaliasRegistada(id, idApi);
    }


    /**
     * Metodo que permite obter os tipos de anomalias
     * @return uma lista de tipos
     */
    public Single<List<Tipo>> obterTiposAnomalias(){
        return tipoDao.obterTipos_(TiposUtil.MetodosTipos.TIPOS_ANOMALIAS, idApi);
    }




    /**
     * Metodo que permite remover uma anomalia
     * @param id o identificador da anomalia
     * @return o resultado da remocao
     */
    public Single<Integer> remover(int id) {
        return anomaliaDao.remover(id);
    }




    @Override
    public Single<Long> inserir(AnomaliaResultado anomalia) {
        return anomaliaDao.inserir(anomalia);
    }

    @Override
    public Single<Integer> atualizar(AnomaliaResultado anomalia) {
        return anomaliaDao.atualizar(anomalia);
    }

    @Override
    public Single<Integer> remover(AnomaliaResultado item) {
        return null;
    }


}
