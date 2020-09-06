package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaRegistada;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
abstract public class AnomaliaDao implements BaseDao<AnomaliaResultado> {


    @Query("SELECT * FROM anomalias WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<Anomalia>> obterAnomalias(int idTarefa);


    @Query("SELECT * " +
            "FROM anomaliasResultado as anm_res " +
            "LEFT JOIN (SELECT id, descricao FROM tipos WHERE tipo = :tipo) as tp ON anm_res.idAnomalia = tp.id " +
            "WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<AnomaliaRegistada>> obterAnomaliasRegistadas(int idTarefa, String tipo);


    @Query("SELECT * " +
            "FROM anomaliasResultado as anm_res " +
            "LEFT JOIN (SELECT id, descricao FROM tipos WHERE tipo = :tipo) as tp ON anm_res.idAnomalia = tp.id " +
            "WHERE anm_res.id = :id")
    abstract public Maybe<AnomaliaRegistada> obterAnomaliasRegistada(int id, String tipo);






    @Query("SELECT * FROM anomaliasResultado WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<AnomaliaResultado>> obterAnomaliasResultado(int idTarefa);




    @Query("DELETE FROM anomaliasResultado WHERE id = :id")
    abstract public Single<Integer> remover(int id);

}
