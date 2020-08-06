package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AnomaliaResultadoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
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

    private final AnomaliaResultadoDao anomaliaResultadoDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public AnomaliaRepositorio(@NonNull AnomaliaResultadoDao anomaliaResultadoDao,
                               @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.anomaliaResultadoDao = anomaliaResultadoDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }


    /**
     * Metodo que permite obter as anomalias existentes
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Flowable<List<Anomalia>> obterAnomalias(int idTarefa) {
        return anomaliaResultadoDao.obterAnomalias(idTarefa);
    }


    /**
     * Metodo que permite obter as anomalias registadas
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Flowable<List<AnomaliaRegistada>> obterAnomaliasRegistadas(int idTarefa) {
        return anomaliaResultadoDao.obterAnomaliasRegistadas(idTarefa, TiposConstantes.TIPOS_ANOMALIA);
    }


    /**
     * Metodo que permite obter uma anomalia registada
     * @param id o identificador da anomalia
     * @return uma anomalia
     */
    public Maybe<AnomaliaRegistada> obterAnomaliaRegistada(int id) {
        return anomaliaResultadoDao.obterAnomaliasRegistada(id, TiposConstantes.TIPOS_ANOMALIA);
    }


    /**
     * Metodo que permite remover uma anomalia
     * @param id o identificador da anomalia
     * @return o resultado da remocao
     */
    public Single<Integer> remover(int id) {
        return anomaliaResultadoDao.remover(id);
    }





    public Single<Long> inserir(AnomaliaResultado anomalia) {
        return anomaliaResultadoDao.inserir(anomalia);
    }

    public Single<Integer> atualizar(AnomaliaResultado anomalia) {
        return anomaliaResultadoDao.atualizar(anomalia);
    }


    public Flowable<List<Tipo>> obterTiposAnomalias(){
        return tipoDao.obterTipos(TiposConstantes.TIPOS_ANOMALIA);
    }




}
