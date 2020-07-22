package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.ui.anomalias.modelos.Anomalia;

import java.util.List;

import io.reactivex.Flowable;

@Dao
abstract public class AnomaliaDao implements BaseDao<Anomalia>{

    @Insert
    abstract public List<Long> inserir(List<Anomalia> registos);


    @Query("SELECT * FROM anomalias WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<Anomalia>> obterAnomalias(int idTarefa);
}
