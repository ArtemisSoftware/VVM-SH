package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.Averiguacao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.AveriguacaoRegisto;
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
    @Query("SELECT id, descricao, tipo, 0 as numeroRegistos, 0 as total, 0 as valido " +
            "FROM relatorioAveriguacao as rel_avg " +
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


    @Query("SELECT rel_avr_med.idMedida as id, descricao " +
            "FROM relatorioAvaliacaoRiscosMedidas as rel_avr_med " +
            "LEFT JOIN (SELECT id as idMedida, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS + "') as tp_medidas " +
            "ON rel_avr_med.idMedida = tp_medidas.idMedida   " +
            "WHERE idRelatorio = :idRelatorio")
    abstract public Observable<List<AveriguacaoRegisto>> obterRegistos(int idRelatorio);



    @Query("SELECT rel_avr_med.idMedida as id, descricao " +
            "FROM relatorioAvaliacaoRiscosMedidas as rel_avr_med " +
            "LEFT JOIN (SELECT id as idMedida, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS + "') as tp_medidas " +
            "ON rel_avr_med.idMedida = tp_medidas.idMedida   " +
            "WHERE id = :id")
    abstract public Single<AveriguacaoRegisto> obterRegisto(int id);



    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg

    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/YaJQAaZR
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/JHZWBCxY
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/AbYCAQ4I
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/BLB03SaJ
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/IGY0QAqI
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/JGJACIrY
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/MXYUEajZ
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/sXI0RCqY
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/deIGzI4a
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/1fI2WagQ
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/BDZmEILA
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/lTQ0UKZa
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/NWY2gAID

    //https://mega.nz/folder/1RADSQzY#iFdVNCrgfF_mdyUhUw1luQ



//--https://mega.nz/folder/SohilbpA#Ahm58e1cpLlmECfstygiXA/folder/XooCxDID
}

