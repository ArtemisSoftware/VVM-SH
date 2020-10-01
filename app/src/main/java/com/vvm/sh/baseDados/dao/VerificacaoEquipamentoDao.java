package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.VerificacaoEquipamentoResultado;

import java.util.List;

import io.reactivex.Single;

@Dao
abstract public class VerificacaoEquipamentoDao implements BaseDao<VerificacaoEquipamentoResultado> {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract public Single<List<Long>> inserir(List<VerificacaoEquipamentoResultado> registos);

    @Query("DELETE FROM verificacaoEquipamentosResultado WHERE idAtividade = :idAtividade")
    public abstract Single<Integer> remover(int idAtividade);
}
