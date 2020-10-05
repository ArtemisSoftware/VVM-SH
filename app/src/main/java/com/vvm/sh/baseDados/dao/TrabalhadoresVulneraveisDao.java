package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.modelos.TrabalhadorVulneravel;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

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



//    query += "OUTER LEFT JOIN (   ";
//    query += "SELECT tb_vul_res.id, CASE WHEN COUNT(idRegisto) > 0 THEN 1 ELSE 0 END as valido    ";
//    query += "FROM trabalhadoresVulneraveis_resultado as tb_vul_res     ";
//    query += "OUTER LEFT JOIN (SELECT id, homens  FROM categoriasProfissionais_resultado WHERE origem = 13 GROUP BY id ) as ctPro_homens ON  tb_vul_res.idRegisto = ctPro_homens.id    ";
//    query += "OUTER LEFT JOIN (SELECT id, mulheres  FROM categoriasProfissionais_resultado WHERE origem = 14 GROUP BY id) as ctPro_mulheres ON  tb_vul_res.idRegisto = ctPro_mulheres.id   ";
//    query += "WHERE IFNULL(homens,0) != 0 OR IFNULL(mulheres,0) != 0   ";
//    query += ") as validade ON tb_vul_res.id = validade.id   ";
//
//



    @Query("SELECT *, descricao, " +
            "CASE WHEN idVulnerabilidade = 1 OR idVulnerabilidade = 2 OR idVulnerabilidade = 3 THEN 1 ELSE 0 END feminina, " +
            "CASE WHEN (idVulnerabilidade = 1 OR idVulnerabilidade = 2 OR idVulnerabilidade = 3) AND (mulheres = ct_mulheres OR mulheres > ct_mulheres) THEN 1 " +
            "WHEN (homens = ct_homens OR homens > ct_homens) AND  mulheres = 0 THEN 1 " +
            "WHEN (mulheres = ct_mulheres OR mulheres > ct_mulheres) AND  homens = 0 THEN 1 " +
            "WHEN (homens = ct_homens OR homens > ct_homens) AND (mulheres = ct_mulheres OR mulheres > ct_mulheres) THEN 1 ELSE 0 END as valido " +
            "FROM trabalhadoresVulneraveisResultado as tbr_vul_res " +

            "LEFT JOIN (SELECT idRegisto, IFNULL(COUNT(*), 0) as ct_homens  FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.CATEGORIAS_PROFISSIONAIS_VULNERABILIDADE_HOMENS +" GROUP BY origem, idRegisto ) as ctPro_homens " +
            "ON  tbr_vul_res.id = ctPro_homens.idRegisto    " +

            "LEFT JOIN (SELECT idRegisto, IFNULL(COUNT(*), 0) as ct_mulheres FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.CATEGORIAS_PROFISSIONAIS_VULNERABILIDADE_MULHERES +" GROUP BY origem, idRegisto) as ctPro_mulheres " +
            "ON  tbr_vul_res.id = ctPro_mulheres.idRegisto " +


            "LEFT JOIN (SELECT id, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_VULNERABILIDADES + "' AND ativo = 1 AND api =:idApi) as tp_vul " +
            "ON tbr_vul_res.idVulnerabilidade = tp_vul.id " +
            "WHERE idAtividade =:idAtividade")
    abstract public Observable<List<TrabalhadorVulneravel>> obterVulnerabilidades(int idAtividade, int idApi);



    @Query("SELECT *, descricao, " +
            "CASE WHEN idVulnerabilidade = 1 OR idVulnerabilidade = 2 OR idVulnerabilidade = 3 THEN 1 ELSE 0 END feminina, " +
            "CASE WHEN (idVulnerabilidade = 1 OR idVulnerabilidade = 2 OR idVulnerabilidade = 3) AND (mulheres = ct_mulheres OR mulheres > ct_mulheres) THEN 1 " +
            "WHEN (homens = ct_homens OR homens > ct_homens) AND  mulheres = 0 THEN 1 " +
            "WHEN (mulheres = ct_mulheres OR mulheres > ct_mulheres) AND  homens = 0 THEN 1 " +
            "WHEN (homens = ct_homens OR homens > ct_homens) AND (mulheres = ct_mulheres OR mulheres > ct_mulheres) THEN 1 ELSE 0 END as valido " +
            "FROM trabalhadoresVulneraveisResultado as tbr_vul_res " +

            "LEFT JOIN (SELECT idRegisto, IFNULL(COUNT(*), 0) as ct_homens  FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.CATEGORIAS_PROFISSIONAIS_VULNERABILIDADE_HOMENS +" GROUP BY origem, idRegisto ) as ctPro_homens " +
            "ON  tbr_vul_res.id = ctPro_homens.idRegisto    " +

            "LEFT JOIN (SELECT idRegisto, IFNULL(COUNT(*), 0) as ct_mulheres FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.CATEGORIAS_PROFISSIONAIS_VULNERABILIDADE_MULHERES +" GROUP BY origem, idRegisto) as ctPro_mulheres " +
            "ON  tbr_vul_res.id = ctPro_mulheres.idRegisto " +

            "LEFT JOIN (SELECT id, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_VULNERABILIDADES + "' AND ativo = 1 AND api =:idApi) as tp_vul " +
            "ON tbr_vul_res.idVulnerabilidade = tp_vul.id " +
            "WHERE tbr_vul_res.id =:id")
    abstract public Single<TrabalhadorVulneravel> obterVulnerabilidade(int id, int idApi);

}
