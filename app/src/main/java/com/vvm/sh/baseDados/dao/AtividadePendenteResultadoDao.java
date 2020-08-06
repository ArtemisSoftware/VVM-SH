package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteResultado;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
abstract public class AtividadePendenteResultadoDao implements BaseDao<AtividadePendenteResultado> {


    @Transaction
    @Query("SELECT * FROM atividadesPendentes WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<AtividadePendenteRegisto>> obterAtividades(int idTarefa);

    @Transaction
    @Query("SELECT * FROM atividadesPendentes WHERE id = :id")
    abstract public Maybe<AtividadePendenteRegisto> obterAtividade(int id);


    @Query("DELETE FROM atividadesPendentesResultado WHERE id = :id")
    abstract public Single<Integer> remover(int id);
}
