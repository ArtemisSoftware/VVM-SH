package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.modelos.TrabalhadorVulneravel;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

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


    @Query("SELECT *, descricao " +
            "FROM trabalhadoresVulneraveisResultado as tbr_vul_res " +
            "LEFT JOIN (SELECT id, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_VULNERABILIDADES + "' AND ativo = 1 AND api =:idApi)" +
            "WHERE idAtividade =:idAtividade")
    abstract public Observable<List<TrabalhadorVulneravel>> obterVulnerabilidades(int idAtividade, int idApi);
}
