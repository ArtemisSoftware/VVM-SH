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



    @Query("SELECT idRelatorio, med.idMedida as idMedida, descricao, implementado " +
            "FROM medidasResultado as med " +

            "LEFT JOIN (SELECT id as idMedida, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS + "' AND api = :api) as tp_med " +
            "ON med.idMedida = tp_med.idMedida " +

            "LEFT JOIN (SELECT idRelatorio, idMedida, implementado FROM relatorioAveriguacaoResultado) as rel_res " +
            "ON med.id = rel_res.idRelatorio AND med.idMedida = rel_res.idMedida " +

            "WHERE med.id =:idRelatorio AND med.origem = " + Identificadores.Origens.AVERIGUACAO_AVALIACAO_RISCOS + " ")
    abstract public Observable<List<AveriguacaoRegisto>> obterRegistos(int idRelatorio, int api);



    @Query("SELECT rel_avr_med.idMedida as id, descricao " +
            "FROM relatorioAvaliacaoRiscosMedidas as rel_avr_med " +
            "LEFT JOIN (SELECT id as idMedida, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS + "') as tp_medidas " +
            "ON rel_avr_med.idMedida = tp_medidas.idMedida   " +
            "WHERE id = :id")
    abstract public Single<AveriguacaoRegisto> obterRegisto(int id);

    //https://mega.nz/folder/jqJAlYyL#Js1gkGWstR3F9O3gKrINgw
    //http://linkunshortner.glitch.me/

    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg


    //https://mega.nz/folder/aQ1S3Y4I#9JTNLSLe0WYrIF1YH6KuTQ/folder/7FMnHCLI
    //https://mega.nz/folder/aQ1S3Y4I#9JTNLSLe0WYrIF1YH6KuTQ/folder/OUMyhIwR
    //https://mega.nz/folder/aQ1S3Y4I#9JTNLSLe0WYrIF1YH6KuTQ/folder/LVMwQKpL
    //https://mega.nz/folder/aQ1S3Y4I#9JTNLSLe0WYrIF1YH6KuTQ/folder/7NNyWQgR
    //https://mega.nz/folder/aQ1S3Y4I#9JTNLSLe0WYrIF1YH6KuTQ/folder/fNMC3Qob
    //https://mega.nz/folder/aQ1S3Y4I#9JTNLSLe0WYrIF1YH6KuTQ/folder/fMdyRI4A
    //https://mega.nz/folder/aQ1S3Y4I#9JTNLSLe0WYrIF1YH6KuTQ/folder/3IUGlAYT
    //https://mega.nz/folder/aQ1S3Y4I#9JTNLSLe0WYrIF1YH6KuTQ/folder/6dFEWCKa
    //https://mega.nz/folder/aQ1S3Y4I#9JTNLSLe0WYrIF1YH6KuTQ/folder/OEVkQQbS
    //https://mega.nz/folder/aQ1S3Y4I#9JTNLSLe0WYrIF1YH6KuTQ/folder/vNE2XQKQ
    //https://mega.nz/folder/aQ1S3Y4I#9JTNLSLe0WYrIF1YH6KuTQ/folder/6JE2zSjY

    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/JHZWBCxY
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/AbYCAQ4I --sph
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/IGY0QAqI --m mk + https://mega.nz/folder/qEMGzZTA#sDhaYSkVzwqIQL9q03KbHw/folder/SBUAGbAQ

    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/MXYUEajZ --jn
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/sXI0RCqY --js lo
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/1fI2WagQ --fth
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/NWY2gAID --an de

    //https://mega.nz/folder/1RADSQzY#iFdVNCrgfF_mdyUhUw1luQ --st ro
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/caQQxabT

//--https://mega.nz/folder/SohilbpA#Ahm58e1cpLlmECfstygiXA/folder/XooCxDID mm

    //res
    //sph + https://mega.nz/folder/OK5SyYCK#_9iSmqHs33w0mNhBjq9G8Q
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/JGJACIrY --k vong +
    //https://mega.nz/folder/9aIhXIrR#U9QHq_13tly4SYRLn10ykg/folder/YaJQAaZR --ca
    //https://mega.nz/folder/qEMGzZTA#sDhaYSkVzwqIQL9q03KbHw/folder/WQdEyRqA -mand mus
    //https://mega.nz/folder/qEMGzZTA#sDhaYSkVzwqIQL9q03KbHw/folder/bUcSARKJ --per bit
}

