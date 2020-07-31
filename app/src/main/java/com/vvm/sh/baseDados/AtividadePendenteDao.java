package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
abstract public class AtividadePendenteDao implements BaseDao<AtividadePendente> {


    @Insert
    abstract public List<Long> inserir(List<AtividadePendente> registos);


    @Transaction
    @Query("SELECT * FROM atividadesPendentes WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<AtividadePendenteRegisto>> obterAtividades(int idTarefa);

}
