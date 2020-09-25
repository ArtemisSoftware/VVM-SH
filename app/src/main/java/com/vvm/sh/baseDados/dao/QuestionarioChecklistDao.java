package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Questao;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class QuestionarioChecklistDao implements BaseDao<QuestionarioChecklistResultado> {



//    String query = "SELECT chk_itens.idItem as idItem, item as pergunta,   ";
//    query += "IFNULL(resposta, '') as resposta, IFNULL(subResposta, '') as subResposta, IFNULL(origem, 0) as origem  ";
//
//    query += "FROM itens_checklist as chk_itens  ";
//
//    query += "OUTER LEFT JOIN (  ";
//    query += "SELECT idChecklist, idArea, idRegisto FROM areas_checklist_resultado   ";
//    query += ") ar_chk_res ON chk_itens.idChecklist = ar_chk_res.idChecklist AND  chk_itens.idArea = ar_chk_res.idArea  ";
//
//    query += "OUTER LEFT JOIN (  ";
//    query += "SELECT idSeccao, idItem, resposta, origem, idRegistoArea, tipo,   ";
//    query += "CASE   WHEN nr IS NULL AND ni IS NULL THEN ''   ";
//    query += "WHEN nr IS NULL THEN ni    ";
//    query += "ELSE nr END as subResposta   ";
//    query += "FROM   questionario_checklist_resultado   ";
//    query += ") as qst ON ar_chk_res.idRegisto = qst.idRegistoArea AND chk_itens.idSeccao = qst.idSeccao AND  chk_itens.idItem = qst.idItem AND chk_itens.tipo = qst.tipo   ";
//
//    query += "WHERE ar_chk_res.idRegisto = ? AND chk_itens.idSeccao = ?  AND chk_itens.tipo = ?   ";
//
//
//    String argumentos [] = {
//            idRegistoArea, idSeccao,  CheckListIF.TIPO_QUESTAO
//    };



    @Query("SELECT *, qst.id as idRegistoItem, resposta, ni, observacao " +
            "FROM itensChecklist as it_chk " +

            "LEFT JOIN ( " +
            "SELECT idChecklist, idArea, id FROM areasChecklistResultado " +
            ") as area_chk_res " +
            "ON it_chk.idChecklist = area_chk_res.idChecklist AND  it_chk.idArea = area_chk_res.idArea " +

            "LEFT JOIN (  " +
            "SELECT idArea, idSeccao, idItem, tipo, " +
            "id, resposta, ni, observacao " +
            "FROM   questionarioChecklistResultado " +
            ") as qst " +
            "ON area_chk_res.id = qst.idArea AND it_chk.idSeccao = qst.idSeccao AND  it_chk.uid = qst.idItem AND it_chk.tipo = qst.tipo " +
            "" +
            "" +


//                query += "";
//    query += "SELECT idSeccao, idItem, resposta, origem, idRegistoArea, tipo,   ";
//    query += "CASE   WHEN nr IS NULL AND ni IS NULL THEN ''   ";
//    query += "WHEN nr IS NULL THEN ni    ";
//    query += "ELSE nr END as subResposta   ";
//    query += "FROM   questionario_checklist_resultado   ";
//    query += ") as qst ON ar_chk_res.idRegisto = qst.idRegistoArea AND chk_itens.idSeccao = qst.idSeccao AND  chk_itens.idItem = qst.idItem AND chk_itens.tipo = qst.tipo   ";



            "WHERE it_chk.idSeccao = :idSeccao  AND it_chk.tipo = :tipo AND area_chk_res.id = :idRegistoArea ")
    abstract public Observable<List<Questao>> obterQuestoes(int idRegistoArea, String idSeccao, String tipo);


}
