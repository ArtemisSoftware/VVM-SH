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

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class AveriguacaoDao implements BaseDao<RelatorioAveriguacaoResultado> {

//    String query = "SELECT rel_avr_rsc.idRelatorio as id, descricao ";
//    query += "FROM relatorioAvaliacaoRiscos as rel_avr_rsc	 ";
//    query += "WHERE rel_avr_rsc.idTarefa = ?		 ";
//
//
//    String query = "SELECT IFNULL(posicao, 0) as posicao, IFNULL(limite, 0 ) as limite,  ";
//    query += "CASE WHEN IFNULL(limite, 0 ) = 0 THEN 0	 ";
//    query += "WHEN IFNULL(posicao, 0) != IFNULL(limite, 0 ) THEN 0	 ";
//    query += "ELSE 1 END as valido		 ";
//    query += "FROM relatorioAvaliacaoRiscos as rel_avr_rsc	 ";
//    query += "OUTER LEFT JOIN (SELECT idRelatorio, COUNT(idMedida) as limite FROM relatorioAvaliacaoRiscosMedidas GROUP by idRelatorio) as rel_avr_rsc_med ON rel_avr_rsc.idRelatorio = rel_avr_rsc_med.idRelatorio	 ";
//    query += "OUTER LEFT JOIN (SELECT idTarefa, idRelatorio, COUNT(medida) as posicao FROM relatorioAveriguacao_resultado GROUP by idTarefa, idRelatorio) as rel_avrg_res 		 ";
//    query += "ON rel_avr_rsc.idTarefa = rel_avrg_res.idTarefa AND rel_avr_rsc_med.idRelatorio = rel_avrg_res.idRelatorio		 ";
//    query += "WHERE rel_avr_rsc.idTarefa = ?	AND rel_avr_rsc.idRelatorio = ?	 ";
//
    @Query("SELECT rel_avg.id as id, descricao, tipo, numeroRegistos, total, " +
            "CASE WHEN numeroRegistos = total THEN 1 ELSE 0 END as valido " +
            "FROM relatorioAveriguacao as rel_avg " +

            "LEFT JOIN (SELECT id, IFNULL(COUNT(*), 0) as total FROM medidasAveriguacao GROUP BY id) as med " +
            "ON rel_avg.id = med.id  " +

            "LEFT JOIN (SELECT idRelatorio, IFNULL(COUNT(*), 0) as numeroRegistos FROM relatorioAveriguacaoResultado GROUP BY idRelatorio) as rel_avg_res " +
            "ON rel_avg.id = rel_avg_res.idRelatorio  " +

            "WHERE idTarefa = :idTarefa AND tipo = :tipo")
    abstract public Observable<List<Averiguacao>> obterRelatorio(int idTarefa, int tipo);


//
//    String query = "SELECT idMedida, tp_medidas.descricao as medida, risco, implementacao, ponderacao, rel_avrg_res.data  as data  ";
//    query += "FROM relatorioAvaliacaoRiscos as rel_avr_rsc   ";
//
//    query += "OUTER LEFT JOIN (SELECT idRelatorio, idMedida FROM relatorioAvaliacaoRiscosMedidas) as rel_avr_rsc_med ON rel_avr_rsc.idRelatorio = rel_avr_rsc_med.idRelatorio   ";
//    query += "OUTER LEFT JOIN (SELECT id, descricao FROM tipos WHERE tipo = 'MedidasPrevencaoRecomendadas') as tp_medidas ON rel_avr_rsc_med.idMedida = tp_medidas.id   ";
//
//    query += "OUTER LEFT JOIN (SELECT idTarefa, idRelatorio, medida, implementacao, risco, ponderacao, data FROM relatorioAveriguacao_resultado) as rel_avrg_res    ";
//    query += "ON rel_avr_rsc.idTarefa = rel_avrg_res.idTarefa AND rel_avr_rsc_med.idRelatorio = rel_avrg_res.idRelatorio AND rel_avr_rsc_med.idMedida = rel_avrg_res.medida   ";
//    query += "WHERE rel_avr_rsc.idTarefa = ? AND rel_avr_rsc_med.idRelatorio = ?   ";
//    query += "   ";
//    query += "   ";
//    query += "   ";
//
//    String argumentos [] = {
//            idTarefa, idRelatorio
//    };
//



    @Query("SELECT idRelatorio, med.idMedida as idMedida, descricao, implementado " +
            "FROM medidasAveriguacao as med " +

            "LEFT JOIN (SELECT id as idMedida, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS + "' AND api = :api) as tp_med " +
            "ON med.idMedida = tp_med.idMedida " +

            "LEFT JOIN (SELECT idRelatorio, idMedida, implementado FROM relatorioAveriguacaoResultado) as rel_res " +
            "ON med.id = rel_res.idRelatorio AND med.idMedida = rel_res.idMedida " +

            "WHERE med.id =:idRelatorio AND med.origem = " + Identificadores.Origens.AVERIGUACAO_AVALIACAO_RISCOS + " ")
    abstract public Observable<List<AveriguacaoRegisto>> obterRegistos(int idRelatorio, int api);



    @Query("SELECT idRelatorio, med.idMedida as idMedida, descricao, implementado " +
            "FROM medidasAveriguacao as med " +

            "LEFT JOIN (SELECT id as idMedida, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS + "' AND api = :api) as tp_med " +
            "ON med.idMedida = tp_med.idMedida " +

            "LEFT JOIN (SELECT idRelatorio, idMedida, implementado FROM relatorioAveriguacaoResultado) as rel_res " +
            "ON med.id = rel_res.idRelatorio AND med.idMedida = rel_res.idMedida " +

            "WHERE med.id =:id AND med.origem = " + Identificadores.Origens.AVERIGUACAO_AVALIACAO_RISCOS + " ")
    abstract public Single<AveriguacaoRegisto> obterRegisto(int id, int api);

    //https://mega.nz/folder/jqJAlYyL#Js1gkGWstR3F9O3gKrINgw
    //http://linkunshortner.glitch.me/

}

