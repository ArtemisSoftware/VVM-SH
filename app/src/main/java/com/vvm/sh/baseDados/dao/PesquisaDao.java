package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.pesquisa.modelos.Medida;
import com.vvm.sh.util.constantes.AppConfig;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class PesquisaDao {

    @Query("SELECT * " +
            "FROM tipos " +
            "WHERE tipo = :tipo AND api = :api AND id IN (:registos) AND ativo = 1 ")
    abstract public Single<List<Tipo>> obterTipos_Incluir(String tipo, List<Integer> registos, int api);

    @Query("SELECT * " +
            "FROM tipos " +
            "WHERE tipo = :tipo AND api = :api AND id NOT IN (:registos) AND ativo = 1 " +
            "LIMIT :limite OFFSET 0 ")
    abstract public Single<List<Tipo>> obterTipos_Excluir(String tipo, List<Integer> registos, int api, int limite);


    @Query("SELECT * " +
            "FROM tipos " +
            "WHERE tipo = :tipo AND api = :api AND id NOT IN (:registos) AND descricao LIKE '%' || :pesquisa || '%' AND ativo = 1 " +
            "LIMIT :limite OFFSET 0 ")
    abstract public Maybe<List<Tipo>> pesquisar(String tipo, List<Integer> registos, String pesquisa, int api, int limite);




    @Query("SELECT *, CASE WHEN id IN (:registos) THEN 1 ELSE 0 END as selecionado " +
            "FROM tipos as tp " +
            "WHERE tipo = :tipo AND api = :api AND ativo = 1 AND idPai = :idPai " +
            "LIMIT " + AppConfig.NUMERO_RESULTADOS_QUERY + " OFFSET :pagina ")
    abstract public Single<List<Medida>> obterMedidas(String tipo, int api, List<Integer> registos, String idPai, int pagina);


    @Query("SELECT *, CASE WHEN id IN (:registos) THEN 1 ELSE 0 END as selecionado " +
            "FROM tipos as tp " +
            "WHERE tipo = :tipo AND codigo = :codigo AND api = :api AND ativo = 1 " +
            "LIMIT " + AppConfig.NUMERO_RESULTADOS_QUERY + " OFFSET :pagina ")
    abstract public Single<List<Medida>> obterMedidas(String tipo, String codigo, int api, List<Integer> registos, int pagina);



    @Query("SELECT *, CASE WHEN id IN (:registos) THEN 1 ELSE 0 END as selecionado " +
            "FROM tipos as tp " +
            "WHERE tipo = :tipo AND api = :api AND ativo = 1 " +
            "LIMIT " + AppConfig.NUMERO_RESULTADOS_QUERY + " OFFSET :pagina ")
    abstract public Single<List<Medida>> obterMedidas(String tipo, int api, List<Integer> registos, int pagina);
}
