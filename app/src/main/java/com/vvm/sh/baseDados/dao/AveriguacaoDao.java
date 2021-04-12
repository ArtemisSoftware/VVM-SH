package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.Averiguacao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.AveriguacaoRegisto;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class AveriguacaoDao implements BaseDao<RelatorioAveriguacaoResultado> {


    @Query("SELECT rel_avg.id as id, descricao, tipo, numeroRegistos, total, numeroRegistosNaoImplementados, numeroRegistosImplementados, " +
            "CASE WHEN numeroRegistos = total THEN 1 ELSE 0 END as valido " +
            "FROM relatorioAveriguacao as rel_avg " +

            "LEFT JOIN (SELECT id, IFNULL(COUNT(*), 0) as total FROM medidasAveriguacao GROUP BY id) as med " +
            "ON rel_avg.id = med.id  " +



            "LEFT JOIN (" +
            "SELECT idRelatorio, IFNULL(COUNT(*), 0) as numeroRegistosImplementados FROM relatorioAveriguacaoResultado " +
            "WHERE implementado = 1 GROUP BY idRelatorio" +
            ") as rel_avg_res_implementado " +
            "ON rel_avg.id = rel_avg_res_implementado.idRelatorio  " +

            "LEFT JOIN (" +
            "SELECT idRelatorio, IFNULL(COUNT(*), 0) as numeroRegistosNaoImplementados FROM relatorioAveriguacaoResultado " +
            "WHERE implementado = 0 GROUP BY idRelatorio" +
            ") as rel_avg_res_nao_implementado " +
            "ON rel_avg.id = rel_avg_res_nao_implementado.idRelatorio  " +


            "LEFT JOIN (SELECT idRelatorio, IFNULL(COUNT(*), 0) as numeroRegistos FROM relatorioAveriguacaoResultado GROUP BY idRelatorio) as rel_avg_res " +
            "ON rel_avg.id = rel_avg_res.idRelatorio  " +

            "WHERE idTarefa = :idTarefa")
    abstract public Observable<List<Averiguacao>> obterRelatorio(int idTarefa);




    @Query("SELECT idRelatorio, rel_res.id as id, med.idMedida as idMedida, descricao, implementado " +
            "FROM medidasAveriguacao as med " +

            "LEFT JOIN (SELECT id as idMedida, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS + "' AND api = :api) as tp_med " +
            "ON med.idMedida = tp_med.idMedida " +

            "LEFT JOIN (SELECT id, idRelatorio, idMedida, implementado FROM relatorioAveriguacaoResultado) as rel_res " +
            "ON med.id = rel_res.idRelatorio AND med.idMedida = rel_res.idMedida " +

            "WHERE med.id =:idRelatorio AND med.origem = " + Identificadores.Origens.AVERIGUACAO_AVALIACAO_RISCOS + " ")
    abstract public Observable<List<AveriguacaoRegisto>> obterRegistos(int idRelatorio, int api);



    @Query("SELECT idRelatorio, rel_res.id as id, med.idMedida as idMedida, descricao, implementado " +
            "FROM medidasAveriguacao as med " +

            "LEFT JOIN (SELECT id as idMedida, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS + "' AND api = :api) as tp_med " +
            "ON med.idMedida = tp_med.idMedida " +

            "LEFT JOIN (SELECT id, idRelatorio, idMedida, implementado FROM relatorioAveriguacaoResultado) as rel_res " +
            "ON med.id = rel_res.idRelatorio AND med.idMedida = rel_res.idMedida " +

            "WHERE rel_res.id =:id AND med.origem = " + Identificadores.Origens.AVERIGUACAO_AVALIACAO_RISCOS + " ")
    abstract public Single<AveriguacaoRegisto> obterRegisto(int id, int api);





    @Query("DELETE FROM relatorioAveriguacaoResultado WHERE idRelatorio =:idRelatorio")
    abstract public Completable remover(int idRelatorio);


    @Query("INSERT INTO relatorioAveriguacaoResultado (idRelatorio, idMedida, implementado, idPonderacao) " +
            "SELECT id as idRelatorio, idMedida, 1 as implementado, 0 as idPonderacao " +
            "FROM medidasAveriguacao " +
            "WHERE origem =" + Identificadores.Origens.AVERIGUACAO_AVALIACAO_RISCOS +" AND idRelatorio =:idRelatorio")
    abstract public Completable inserirNaoImplementado(int idRelatorio);





}

