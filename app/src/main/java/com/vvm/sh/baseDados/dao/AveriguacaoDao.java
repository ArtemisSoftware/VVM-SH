package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacaoResultado;

import java.util.Observable;

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
//    @Query("DELETE FROM categoriasProfissionaisResultado WHERE idRegisto = :idRegisto AND origem = :origem")
//    abstract public Observable<RelatorioAveriguacaoResultado> remover(int idRegisto, int origem);

}

