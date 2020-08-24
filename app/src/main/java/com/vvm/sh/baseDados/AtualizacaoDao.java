package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.baseDados.entidades.Atualizacao;

import java.util.List;

import io.reactivex.Flowable;

@Dao
abstract public class AtualizacaoDao implements BaseDao<Atualizacao>{


    @Insert
    abstract public void inserirRegisto(Atualizacao atualizacao);

    @Query("DELETE FROM atualizacoes WHERE descricao = :descricao ")
    abstract public void remover(String descricao);


    @Query("SELECT * FROM atualizacoes ")
    abstract public Flowable<List<Atualizacao>> obterAtualizacoes();

}