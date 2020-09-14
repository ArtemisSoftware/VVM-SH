package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;

import java.util.List;

import io.reactivex.Observable;

@Dao
abstract public class LevantamentoDao implements BaseDao<LevantamentoRiscoResultado> {


    @Query("SELECT * FROM levantamentosRiscoResultado  WHERE idAtividade = :idAtividade")
    abstract public Observable<List<LevantamentoRiscoResultado>> obterLevantamentos(int idAtividade);

}
