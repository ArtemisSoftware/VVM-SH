package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.RiscoResultado;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
abstract public class RiscoDao implements BaseDao<RiscoResultado> {


    @Query("SELECT * FROM riscosResultado WHERE idLevantamento = :idLevantamento")
    abstract public Observable<List<RiscoResultado>> obterRiscos(int idLevantamento);

}
