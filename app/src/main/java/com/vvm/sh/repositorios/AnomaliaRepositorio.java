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

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class AnomaliaRepositorio {

    private final AnomaliaDao anomaliaDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public AnomaliaRepositorio(@NonNull AnomaliaDao anomaliaDao,
                               @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.anomaliaDao = anomaliaDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }


    /**
     * Metodo que permite obter as anomalias existentes
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Flowable<List<Anomalia>> obterAnomalias(int idTarefa) {
        return anomaliaDao.obterAnomalias(idTarefa);
    }


    /**
     * Metodo que permite obter as anomalias registadas
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Flowable<List<AnomaliaRegistada>> obterAnomaliasRegistadas(int idTarefa) {
        return anomaliaDao.obterAnomaliasRegistadas(idTarefa, TiposConstantes.MetodosTipos.TIPOS_ANOMALIA);
    }


    /**
     * Metodo que permite obter uma anomalia registada
     * @param id o identificador da anomalia
     * @return uma anomalia
     */
    public Maybe<AnomaliaRegistada> obterAnomaliaRegistada(int id) {
        return anomaliaDao.obterAnomaliasRegistada(id, TiposConstantes.MetodosTipos.TIPOS_ANOMALIA);
    }


    /**
     * Metodo que permite remover uma anomalia
     * @param id o identificador da anomalia
     * @return o resultado da remocao
     */
    public Single<Integer> remover(int id) {
        return anomaliaDao.remover(id);
    }





    public Single<Long> inserir(AnomaliaResultado anomalia) {
        return anomaliaDao.inserir(anomalia);
    }

    public Single<Integer> atualizar(AnomaliaResultado anomalia) {
        return anomaliaDao.atualizar(anomalia);
    }


    public Flowable<List<Tipo>> obterTiposAnomalias(){
        return tipoDao.obterTipos(TiposConstantes.MetodosTipos.TIPOS_ANOMALIA);
    }




}
