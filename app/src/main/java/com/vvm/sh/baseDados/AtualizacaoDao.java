package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.baseDados.entidades.Atualizacao;

@Dao
abstract public class AtualizacaoDao implements BaseDao<Atualizacao>{


    @Insert
    abstract public void inserirRegisto(Atualizacao atualizacao);

    @Query("DELETE FROM atualizacoes WHERE descricao = :descricao ")
    abstract public void remover(String descricao);

}