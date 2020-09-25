package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.util.metodos.TiposUtil;

import io.reactivex.Completable;

@Dao
abstract public class TrabalhadoresVulneraveisDao implements BaseDao<TrabalhadorVulneravelResultado> {




    @Query("INSERT INTO trabalhadoresVulneraveisResultado (idAtividade, idVulnerabilidade) " +
            "SELECT :idAtividade as idAtividade, id as idVulnerabilidade " +
            "FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_VULNERABILIDADES + "'  AND ativo = 1 AND api =:idApi " +
            "AND id IN ( " +

            "SELECT id " +
            "FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_VULNERABILIDADES + "'  AND ativo = 1 AND id AND api =:idApi " +
            "NOT IN (" +
            "SELECT idVulnerabilidade  FROM trabalhadoresVulneraveisResultado " +
            "WHERE idAtividade = :idAtividade " +
            ")" +
            ")")
    abstract public Completable inserirVulnerabilidades(int idAtividade, int idApi);
}
