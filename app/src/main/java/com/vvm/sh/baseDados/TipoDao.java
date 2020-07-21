package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.ui.contaUtilizador.Colecao;
import com.vvm.sh.ui.opcoes.modelos.Atualizacao;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
abstract public class TipoDao implements BaseDao<Tipo>{


    @Insert
    abstract public List<Long> inserir(List<Tipo> tipo);

/*
    @Query("SELECT descricao, numeroRegistos, seloTemporal " +
            "FROM atualizacoes as atl " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistos FROM tipos GROUP BY tipo) as tp ON atl.descricao = tp.tipo " +
            "ORDER BY descricao ASC")
    abstract public Maybe<List<Colecao>> obterTipos();
*/

    @Query("SELECT descricao, numeroRegistos, seloTemporal " +
            "FROM atualizacoes as atl " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistos FROM tipos GROUP BY tipo) as tp ON atl.descricao = tp.tipo " +
            "ORDER BY descricao ASC")
    abstract public Flowable<List<Colecao>> obterTipos();




    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND idPai = '' AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterCrossSellingProdutos(String tipo);

    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND idPai = :idProduto AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterCrossSelling(String tipo, String idProduto);


    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos(String tipo);
}
