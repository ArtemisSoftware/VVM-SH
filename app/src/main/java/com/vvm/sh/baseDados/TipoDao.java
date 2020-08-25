package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vvm.sh.ui.opcoes.modelos.Colecao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;

import java.util.List;

import io.reactivex.Flowable;

@Dao
abstract public class TipoDao implements BaseDao<Tipo>{


    @Insert
    abstract public List<Long> inserir(List<Tipo> tipo);

    @Update
    abstract public Integer atualizar(List<Tipo> tipo);


/*
    @Query("SELECT descricao, numeroRegistos, seloTemporal " +
            "FROM atualizacoes as atl " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistos FROM tipos GROUP BY tipo) as tp ON atl.descricao = tp.tipo " +
            "ORDER BY descricao ASC")
    abstract public Maybe<List<Colecao>> obterResumoTipos();
*/

    @Query("SELECT descricao, numeroRegistos, seloTemporal " +
            "FROM atualizacoes as atl " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistos FROM tipos WHERE ativo = 1 GROUP BY tipo) as tp ON atl.descricao = tp.tipo " +
            "ORDER BY descricao ASC")
    abstract public Flowable<List<ResumoTipo>> obterResumoTipos();




    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND idPai = '' AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterCrossSellingProdutos(String tipo);



    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos(String tipo);

    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND id = :id AND ativo = 1")
    abstract public Flowable<Tipo> obterTipo(String tipo, int id);



    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND idPai = :idPai AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos(String tipo, String idPai);

}
