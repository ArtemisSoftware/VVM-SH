package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;

import io.reactivex.Maybe;

@Dao
abstract public class RegistoVisitaDao implements BaseDao<RegistoVisitaResultado> {


    @Query("SELECT * FROM registoVisitaResultado WHERE idTarefa =:idTarefa")
    abstract public Maybe<RegistoVisitaResultado> obterRegistoVisita(int idTarefa);

}
