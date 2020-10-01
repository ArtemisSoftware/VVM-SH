package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.PlanoAcao;
import com.vvm.sh.baseDados.entidades.PlanoAccaoResultado;
import com.vvm.sh.ui.planoAccao.modelo.AtividadeRegisto;
import com.vvm.sh.ui.planoAccao.modelo.Plano;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class PlanoAccaoDao implements BaseDao<PlanoAccaoResultado> {



/*

    String query = "SELECT id, descricaoSimples, equipaSST, anuidade,   servId,  ";


    query += "CASE WHEN sempreNecessario = 1   THEN 3  ";
    query += "WHEN observacao IS NOT NULL THEN 2  ";
    query += "ELSE 1 END as tipo,  ";

    query += "CASE WHEN sempreNecessario = 1   THEN 'Sempre necessário'  ";
    query += " WHEN observacao IS NOT NULL THEN observacao  ";
    query += "ELSE '' END as nota,  ";


    query += "CASE WHEN data IS NOT NULL                            THEN 6  "; 	//--'reprogramada'
    query += "WHEN sempreNecessario = 1                            THEN 0  ";  //--'NADA'
    query += "WHEN dataExecucao IS NOT NULL AND fixo = 2                        THEN 5  "; 	//--'programada'
    query += "WHEN dataExecucao IS NOT NULL                        THEN 2  "; 	 //--'executada'
    query += "WHEN group_concat(dataProg) IS NOT NULL AND reprogramada = '1'     THEN 3  "; 	//--'reprogramada'
    query += "WHEN group_concat(dataProg) IS NOT NULL                            THEN 4  "; 	//--'programada fixa'
    query += "WHEN fixo = 3    AND observacao IS NULL         THEN 1  "; 	//--'FIxa'
    query += "ELSE 3    "; 	//--'reprogramada'
    query += "END as tipoExecucao,  ";

    query += "CASE WHEN data IS NOT NULL                            THEN data  "; 	//--'reprogramada'
    query += "WHEN sempreNecessario = 1                                                 THEN  'SEM DATA'  ";
    query += "WHEN dataExecucao IS NOT NULL AND fixo = 2                        THEN group_concat(dataProg)  "; 	 //--'programada'
    query += "WHEN dataExecucao IS NOT NULL                                             THEN dataExecucao  ";
    query += "WHEN group_concat(dataProg) IS NOT NULL        AND reprogramada = '1'     THEN group_concat(dataProg)  ";
    query += "WHEN group_concat(dataProg) IS NOT NULL                                   THEN group_concat(dataProg)  ";
    query += "ELSE date('now')   ";
    query += "END as data,  ";
    query += "CASE WHEN fixo = 2 THEN 1 ELSE 0 END as reprogramavel, IFNULL(idRegisto, '') as idRegisto, descricao  ";

    query += "FROM (  ";
    query += "SELECT idTarefa, pl_ac.id as id, descricaoSimples, equipaSST, CASE WHEN anuidade = 'V' THEN 'Vigente' ELSE 'Outra' END as anuidade, sempreNecessario, observacao, fixo, dataExecucao, dataProg, data, idRegisto, reprogramada, descricao, servId  ";
    query += "FROM planoAcaoAtividades as pl_ac  ";
    query += "OUTER LEFT JOIN (SELECT id, data, idRegisto FROM planoAcao_resultado) as pl_ac_res ON pl_ac.id = pl_ac_res.id  ";
    query += "UNION  ";
    query += "SELECT idTarefa, id, descricaoSimples, equipaSST, anuidade, sempreNecessario, observacao, fixo , dataExecucao, dataProg, data, idRegisto, '0' as reprogramada, descricaoCompleta as descricao,  pl_ac_res.servID as servId  ";
    query += "FROM planoAcao_resultado as pl_ac_res  ";
    query += "OUTER LEFT JOIN (  ";
    query += "SELECT servId, descricao as descricaoSimples, descricaoCompleta,   ";
    query += "CASE WHEN equipa = '' THEN NULL ELSE equipa END as equipaSST,   ";
    query += "NULL as anuidade, sempreNecessario,   ";
    query += "CASE WHEN observacao = 'null' THEN NULL ELSE observacao END as observacao, 2 as fixo,  ";
    query += "NULL as dataExecucao, NULL as dataProg   ";
    query += "FROM actividadesPlaneaveis WHERE activo = '1'  ";
    query += ") as act ON pl_ac_res.servID = act.servId  ";

    query += "WHERE pl_ac_res.servID IS NOT NULL  ";
    query += ")  ";
    query += "WHERE idTarefa = ?   ";
    query += "GROUP BY descricaoSimples  ";
    query += "ORDER BY fixo ASC  ";
*/



    @Transaction
    @Query("SELECT *,  " +
            "CASE " +
            "WHEN sempreNecessario = 1   THEN " + Identificadores.TIPO_NOTA + " "+
            "WHEN observacao IS NOT NULL AND observacao != '' THEN " + Identificadores.TIPO_NOTA + " "+
            "ELSE " + Identificadores.TIPO_DATA + " END as tipo, " +
            "" +
            "CASE WHEN sempreNecessario = 1   THEN 'Sempre necessário'  " +
            "WHEN observacao IS NOT NULL THEN observacao  " +
            "ELSE '' END as nota,  " +
            "" +
            "CASE WHEN fixo = 2 THEN 1 ELSE 0 END as reprogramavel, " +
            "" +
            "CASE WHEN dataExecucao IS NOT NULL                            THEN " + Identificadores.REPROGRAMADA + "  " + //--'reprogramada'\n" +
            "WHEN sempreNecessario = 1                            THEN 0  " +  //--'NADA'\n" +
            "WHEN dataExecucao IS NOT NULL AND fixo = 2                        THEN " + Identificadores.PROGRAMADA + "  " + //--'programada'\n" +
            "WHEN dataExecucao IS NOT NULL                        THEN  " + Identificadores.EXECUTADA + "  " + //--'executada'\n" +
            "WHEN (group_concat(dataProgramada) IS NOT NULL AND group_concat(dataProgramada) != '') AND reprogramada = 1     THEN 3  " + //--'reprogramada'\n" +
            "WHEN (group_concat(dataProgramada) IS NOT NULL  AND group_concat(dataProgramada) != '')       THEN " + Identificadores.PROGRAMADA_FIXA + "  " + //--'programada fixa'\n" +
            "WHEN fixo = 3    AND (observacao IS NULL OR  observacao = '')        THEN 1  " +//--'FIxa'\n" +
            "ELSE 3    " + //--'reprogramada'\n" +
            "END as tipoExecucao, " +
            "" +
            "" +
            "" +
            "CASE WHEN dataExecucao IS NOT NULL                            THEN dataExecucao  " + 	//--'reprogramada'
            "WHEN sempreNecessario = 1                                                 THEN  'SEM DATA'  " +
            "WHEN dataExecucao IS NOT NULL AND fixo = 2                        THEN group_concat(dataProgramada)  " + 	 //--'programada'
            "WHEN dataExecucao IS NOT NULL                                             THEN dataExecucao  " +
            "WHEN (group_concat(dataProgramada) IS NOT NULL AND group_concat(dataProgramada) != '')        AND reprogramada = '1'     THEN group_concat(dataProgramada)  " +
            "WHEN (group_concat(dataProgramada) IS NOT NULL AND group_concat(dataProgramada) != '')                                   THEN group_concat(dataProgramada)  " +
            "ELSE date('now')   " +
            "END as data  " +
            "" +
            "" +
            "FROM planoAcaoAtividade as pl_acao " +


            "WHERE pl_acao.idTarefa = :idTarefa " +
            "GROUP BY descricaoSimples ORDER BY fixo ASC ")
    abstract public Observable<List<AtividadeRegisto>> obterAtividades(int idTarefa);



    @Transaction
    @Query("SELECT * FROM planoAcao WHERE idTarefa =:idTarefa")
    abstract public Single<Plano> obterPlano(int idTarefa);

}
