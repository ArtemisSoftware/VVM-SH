package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Atualizacao;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
abstract public class AtualizacaoDao implements BaseDao<Atualizacao> {


    @Insert
    abstract public Long inserirRegisto(Atualizacao atualizacao);

    @Update
    abstract public void atualizarRegisto(Atualizacao atualizacao);

    @Query("DELETE FROM atualizacoes WHERE descricao = :descricao ")
    abstract public void remover(String descricao);

    @Query("SELECT CASE WHEN IFNULL(COUNT(*), 0) > 0 THEN 1 ELSE 0 END as existe FROM atualizacoes WHERE descricao = :descricao ")
    abstract public boolean existeRegisto(String descricao);


    @Query("SELECT * FROM atualizacoes WHERE descricao")
    abstract public Maybe<List<Atualizacao>> obterAtualizacoes();



}