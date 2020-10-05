package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Questao;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class QuestionarioChecklistDao implements BaseDao<QuestionarioChecklistResultado> {


    @Query("SELECT *, qst.id as idRegistoItem, " +
            "resposta, ni, " +
            "observacao," +
            "ut1Descricao, ut2Descricao, " +
            "ut1_CategoriasRisco, ut1_LocalRisco_A, ut1_LocalRisco_B, ut1_LocalRisco_C, ut1_LocalRisco_D, ut1_LocalRisco_E, ut1_LocalRisco_F, " +
            "ut2_CategoriasRisco, ut2_LocalRisco_A, ut2_LocalRisco_B, ut2_LocalRisco_C, ut2_LocalRisco_D, ut2_LocalRisco_E, ut2_LocalRisco_F," +
            "0 as numeroImagens " +
            "FROM itensChecklist as it_chk " +

            "LEFT JOIN ( " +
            "SELECT idChecklist, idArea, id FROM areasChecklistResultado " +
            ") as area_chk_res " +
            "ON it_chk.idChecklist = area_chk_res.idChecklist AND  it_chk.idArea = area_chk_res.idArea " +

            "LEFT JOIN (  " +
            "SELECT idArea, idSeccao, idItem, tipo, " +
            "id, resposta, ni, " +
            "observacao, " +

            "ut1, ut1_CategoriasRisco, ut1_LocalRisco_A, ut1_LocalRisco_B, ut1_LocalRisco_C, ut1_LocalRisco_D, ut1_LocalRisco_E, ut1_LocalRisco_F, " +
            "ut2, ut2_CategoriasRisco, ut2_LocalRisco_A, ut2_LocalRisco_B, ut2_LocalRisco_C, ut2_LocalRisco_D, ut2_LocalRisco_E, ut2_LocalRisco_F " +

            "FROM   questionarioChecklistResultado " +
            ") as qst " +
            "ON area_chk_res.id = qst.idArea AND it_chk.idSeccao = qst.idSeccao AND  it_chk.uid = qst.idItem AND it_chk.tipo = qst.tipo " +


            "LEFT JOIN (SELECT id, descricao as ut1Descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_UTS + "') as tp_ut " +
            "ON qst.ut1 = tp_ut.id " +

            "LEFT JOIN (SELECT id, descricao as ut2Descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_UTS + "') as tp_ut_2 " +
            "ON qst.ut2 = tp_ut_2.id " +
            "" +


            "WHERE it_chk.idSeccao = :idSeccao  AND it_chk.tipo = :tipo AND area_chk_res.id = :idRegistoArea ")
    abstract public Observable<List<Questao>> obterQuestoes(int idRegistoArea, String idSeccao, String tipo);


    @Query("SELECT * FROM questionarioChecklistResultado WHERE id = :id")
    abstract public Single<QuestionarioChecklistResultado> obterQuestao(int id);


    @Query("DELETE FROM areasChecklistResultado WHERE idAtividade = :idAtividade")
    abstract public Single<Integer> remover(int idAtividade);

    @Query("DELETE FROM areasChecklistResultado WHERE id = :id")
    abstract public Single<Integer> removerArea(int id);

}
