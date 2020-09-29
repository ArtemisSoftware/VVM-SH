package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Risco;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
abstract public class RiscoDao implements BaseDao<RiscoResultado> {


    @Query("SELECT *, '----' as risco FROM riscosResultado WHERE idLevantamento = :idLevantamento")
    abstract public Observable<List<Risco>> obterRiscos(int idLevantamento);

}
