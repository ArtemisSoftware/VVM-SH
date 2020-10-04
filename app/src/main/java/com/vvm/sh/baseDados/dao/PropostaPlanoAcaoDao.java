package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.PropostaPlanoAcaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos.Proposta;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class PropostaPlanoAcaoDao implements BaseDao<PropostaPlanoAcaoResultado> {



//    String query = "SELECT idPlano, qst_chk_res.idItem as idMedida, item, tp_ni.ni, prazo,idNi, idPrazo, selecionado, MIN(pl_ac_avr_res.idRegisto) ";
//
//    query += "FROM planoAcaoAVR_resultado as pl_ac_avr_res   ";
//    query += "OUTER LEFT JOIN (SELECT idRegisto, idChecklist,idArea  FROM areas_checklist_resultado) as ar_chk_res ON pl_ac_avr_res.idRegisto = ar_chk_res.idRegisto   ";
//
//    query += "OUTER LEFT JOIN (SELECT idRegistoArea, idItem, idSeccao FROM questionario_checklist_resultado WHERE tipo = 'q') as qst_chk_res    ";
//    query += "ON ar_chk_res.idRegisto = qst_chk_res.idRegistoArea AND pl_ac_avr_res.idMedida = qst_chk_res.idItem   ";
//
//    query += "OUTER LEFT JOIN (SELECT idChecklist, idArea, idSeccao, idItem, item FROM itens_checklist) as it_chk   ";
//    query += "ON ar_chk_res.idChecklist = it_chk.idChecklist AND ar_chk_res.idArea = it_chk.idArea AND qst_chk_res.idSeccao = it_chk.idSeccao AND qst_chk_res.idItem = it_chk.idItem   ";
//
//    query += "OUTER LEFT JOIN (SELECT id, descricao as prazo FROM tipos WHERE tipo = 'PrazosImplementacao') as tp_prazo ON pl_ac_avr_res.idPrazo = tp_prazo.id   ";
//    query += "OUTER LEFT JOIN (SELECT id, descricao as ni FROM tipos WHERE tipo = 'GetTiposNI') as tp_ni ON pl_ac_avr_res.idNi = tp_ni.id   ";
//    query += "WHERE pl_ac_avr_res.id = ? AND pl_ac_avr_res.origem = ?		   ";
//    query += "GROUP BY qst_chk_res.idItem ";
//    query += "ORDER BY qst_chk_res.idItem   ";
//
//
//    String argumentos [] = { idRelatorio, IdentificadoresIF.ORIGEM_CHECKLIST + "" };



    @Query("SELECT * , 'lolo' as descricao, 0 as selecionado, " + Identificadores.Origens.PROPOSTA_CONDICOES_ST + " as tipo " +
            "FROM propostaPlanoAcaoResultado as prop_pl_accao_res " +
            "WHERE idAtividade = :idAtividade")
    abstract public Observable<List<Proposta>> obterPropostasSt(int idAtividade);



//    String query = "SELECT idMedida,  idPlano, medida, selecionado, MIN(idRegisto)   ";
//    query += "FROM planoAcaoAVR_resultado as pl_ac_avr_res	   ";
//    query += "OUTER LEFT JOIN (SELECT id, descricao as medida FROM tipos WHERE tipo = 'MedidasPrevencaoRecomendadas') as tp_medidas ON pl_ac_avr_res.idMedida = tp_medidas.id   ";
//    query += "WHERE pl_ac_avr_res.id = ? AND pl_ac_avr_res.origem = ?	AND medida IS NOT NULL		   ";
//    query += "GROUP BY idMedida ";
//    query += "ORDER BY selecionado DESC, idMedida ASC   ";
//
//    String argumentos [] = {
//            idRelatorio, IdentificadoresIF.ORIGEM_LEVANTAMENTO_RISCO + ""
//    };


    @Query("SELECT * , 'lolo' as descricao, 0 as selecionado, " + Identificadores.Origens.PROPOSTA_MEDIDAS_AVALIACAO + " as tipo " +
            "FROM propostaPlanoAcaoResultado as prop_pl_accao_res " +
            "WHERE idAtividade = :idAtividade")
    abstract public Observable<List<Proposta>> obterMedidasAvaliacao(int idAtividade);


    @Query("UPDATE propostaPlanoAcaoResultado SET selecionado = :selecionado WHERE idAtividade = :idAtividade AND origem = " + Identificadores.Origens.PROPOSTA_MEDIDAS_AVALIACAO + "")
    abstract public Completable selecionarTudo(int idAtividade, boolean selecionado);

    @Query("UPDATE propostaPlanoAcaoResultado SET selecionado = :selecionado WHERE idAtividade = :idAtividade AND id = :id AND origem = " + Identificadores.Origens.PROPOSTA_MEDIDAS_AVALIACAO + "")
    abstract public Completable selecionar(int idAtividade, int id, boolean selecionado);

}
