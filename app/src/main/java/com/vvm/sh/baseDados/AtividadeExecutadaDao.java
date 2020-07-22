package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.ui.atividadesExecutadas.AtividadeExecutada;

import java.util.List;

import io.reactivex.Flowable;

@Dao
abstract public class AtividadeExecutadaDao implements BaseDao<AtividadeExecutada>{

    @Insert
    abstract public List<Long> inserir(List<AtividadeExecutada> registos);


    @Query("SELECT * FROM atividadeExecutadas WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<AtividadeExecutada>> obterAtividades(int idTarefa);

}
