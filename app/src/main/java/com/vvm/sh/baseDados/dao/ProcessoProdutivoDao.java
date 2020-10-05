package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.ProcessoProdutivoResultado;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
abstract public class ProcessoProdutivoDao implements BaseDao<ProcessoProdutivoResultado> {


    @Query("SELECT * FROM processosProdutivosResultado WHERE id = :idAtividade")
    abstract public Maybe<ProcessoProdutivoResultado> obterProcessiProdutivo(int idAtividade);
}
