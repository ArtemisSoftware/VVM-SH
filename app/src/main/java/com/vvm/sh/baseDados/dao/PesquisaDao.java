package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.ui.pesquisa.modelos.Medida;
import com.vvm.sh.util.constantes.AppConfig;

import java.util.List;

import io.reactivex.Observable;

@Dao
abstract public class PesquisaDao {

    @Query("SELECT *, CASE WHEN id IN (:registos) THEN 1 ELSE 0 END as selecionado " +
            "FROM tipos as tp " +
            "WHERE tipo = :tipo AND api = :api AND ativo = 1 AND idPai = :idPai " +
            "LIMIT " + AppConfig.NUMERO_RESULTADOS_QUERY + " OFFSET :pagina ")
    abstract public Observable<List<Medida>> obterMedidas(String tipo, int api, List<Integer> registos, String idPai, int pagina);


    @Query("SELECT *, CASE WHEN id IN (:registos) THEN 1 ELSE 0 END as selecionado " +
            "FROM tipos as tp " +
            "WHERE tipo = :tipo AND codigo = :codigo AND api = :api AND ativo = 1 " +
            "LIMIT " + AppConfig.NUMERO_RESULTADOS_QUERY + " OFFSET :pagina ")
    abstract public Observable<List<Medida>> obterMedidas(String tipo, String codigo, int api, List<Integer> registos, int pagina);

}
