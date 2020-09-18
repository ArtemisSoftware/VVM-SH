package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.ui.opcoes.modelos.Colecao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class TipoDao implements BaseDao<Tipo> {


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

    @Query("SELECT descricao, numeroRegistosSA, numeroRegistosSHT, seloTemporal " +
            "FROM atualizacoes as atl " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistosSA FROM tipos WHERE ativo = 1 AND api = " + Identificadores.App.APP_SA + " GROUP BY tipo) as tp_sa ON atl.descricao = tp_sa.tipo " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistosSHT FROM tipos WHERE ativo = 1 AND api = " + Identificadores.App.APP_ST + " GROUP BY tipo) as tp_sht ON atl.descricao = tp_sht.tipo " +
            "ORDER BY descricao ASC")
    abstract public Observable<List<ResumoTipo>> obterResumoTipos();




    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND idPai = '' AND ativo = 1")
    abstract public Single<List<Tipo>> obterCrossSellingProdutos(String tipo);



    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos(String tipo);

    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND id = :id AND ativo = 1")
    abstract public Flowable<Tipo> obterTipo(String tipo, int id);



    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND idPai = :idPai AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos(String tipo, String idPai);


    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos(String tipo, int api);

    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND id NOT IN (:registos) AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos_Excluir(String tipo, List<Integer> registos, int api);

    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND id NOT IN (:registos) AND descricao LIKE '%' || :pesquisa || '%' AND ativo = 1")
    abstract public Maybe<List<Tipo>> obterTipos_Excluir(String tipo, List<Integer> registos, String pesquisa, int api);


    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND id IN (:registos) AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos_Incluir(String tipo, List<Integer> registos, int api);
}
