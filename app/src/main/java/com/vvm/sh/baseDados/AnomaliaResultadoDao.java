package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
abstract public class AnomaliaResultadoDao implements BaseDao<AnomaliaResultado>{


    @Query("SELECT * FROM anomalias WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<AnomaliaResultado>> obterAnomaliasResultado(int idTarefa);


    @Query("SELECT * FROM anomalias WHERE id = :id")
    abstract public Single<AnomaliaResultado> obterAnomaliaResultado(int id);
}
