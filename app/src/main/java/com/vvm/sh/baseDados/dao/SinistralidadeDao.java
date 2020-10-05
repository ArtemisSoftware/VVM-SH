package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.SinistralidadeResultado;

import io.reactivex.Maybe;

@Dao
abstract public class SinistralidadeDao implements BaseDao<SinistralidadeResultado> {


    @Query("SELECT * FROM sinistralidadesResultado WHERE idTarefa = :idTarefa")
    abstract public Maybe<SinistralidadeResultado> obterSinistralidade(int idTarefa);
}
