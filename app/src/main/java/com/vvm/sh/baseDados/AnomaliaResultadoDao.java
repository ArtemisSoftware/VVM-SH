package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.ui.anomalias.modelos.AnomaliaRegistada;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
abstract public class AnomaliaResultadoDao implements BaseDao<AnomaliaResultado>{


    @Query("SELECT * FROM anomaliasResultado WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<AnomaliaResultado>> obterAnomaliasResultado(int idTarefa);


    @Query("SELECT id, idAnomalia, descricao, observacao " +
            "FROM anomaliasResultado as anm_res " +
            "LEFT JOIN (SELECT id, descricao FROM tipos WHERE tipo = :tipo) as tp ON anm_res.idAnomalia = tp.id " +
            "WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<AnomaliaRegistada>> obterAnomaliasRegistadas(int idTarefa, String tipo);


    @Query("SELECT * FROM anomaliasResultado WHERE id = :id")
    abstract public Single<AnomaliaResultado> obterAnomaliaResultado(int id);
}
