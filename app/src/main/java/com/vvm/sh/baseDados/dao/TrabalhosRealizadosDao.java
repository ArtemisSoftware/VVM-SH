package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;

import java.util.List;

import io.reactivex.Observable;

@Dao
abstract public class TrabalhosRealizadosDao implements BaseDao<TrabalhoRealizadoResultado> {


    @Query("SELECT * FROM trabalhoRealizadoResultado WHERE idTarefa =:idTarefa")
    abstract public Observable<List<Tipo>> obterTrabalhosRealizados(int idTarefa);

}
