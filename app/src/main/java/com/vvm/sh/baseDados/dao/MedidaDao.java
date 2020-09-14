package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.MedidaResultado;

import java.util.List;

import io.reactivex.Observable;

@Dao
abstract public class MedidaDao implements BaseDao<MedidaResultado> {


    @Query("SELECT * FROM medidasResultado WHERE id = :id AND origem = :origem")
    abstract public Observable<List<MedidaResultado>> obterMedidas(int id, int origem);
}
