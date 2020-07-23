package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;

import java.util.List;

import io.reactivex.Flowable;

@Dao
abstract public class AtividadePendenteDao implements BaseDao<AtividadePendente> {


    @Insert
    abstract public List<Long> inserir(List<AtividadePendente> registos);


    @Query("SELECT * FROM atividadesPendentes WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<AtividadePendente>> obterAtividades(int idTarefa);


}
