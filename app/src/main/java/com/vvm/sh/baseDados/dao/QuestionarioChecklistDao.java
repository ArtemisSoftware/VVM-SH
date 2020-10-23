package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Questao;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Completable;
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
            "numeroImagens " +
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


            "LEFT JOIN (SELECT id, IFNULL(COUNT (idImagem), 0) as numeroImagens FROM imagensResultado WHERE id =:idRegistoArea AND origem = " + Identificadores.Imagens.IMAGEM_CHECKLIST + " GROUP BY id, origem) as img " +
            "ON area_chk_res.id = img.id " +

            "" +


            "WHERE it_chk.idSeccao = :idSeccao  AND it_chk.tipo = :tipo AND area_chk_res.id = :idRegistoArea ")
    abstract public Observable<List<Questao>> obterQuestoes(int idRegistoArea, String idSeccao, String tipo);


    @Query("SELECT * FROM questionarioChecklistResultado WHERE id = :id")
    abstract public Single<QuestionarioChecklistResultado> obterQuestao(int id);


    @Query("DELETE FROM areasChecklistResultado WHERE idAtividade = :idAtividade")
    abstract public Single<Integer> remover(int idAtividade);

    @Query("DELETE FROM areasChecklistResultado WHERE id = :id")
    abstract public Single<Integer> removerArea(int id);

    @Query("DELETE FROM imagensResultado WHERE id = :id AND origem = " + Identificadores.Imagens.IMAGEM_CHECKLIST + "")
    abstract public Single<Integer> removerImagensArea(int id);






    //-------------------
    //Remover area
    //-------------------

    @Query("DELETE FROM propostaPlanoAcaoResultado WHERE idQuestao IN (" +
            "SELECT id " +
            "FROM questionarioChecklistResultado as quest_res " +
            "WHERE idArea = :idRegistoArea" +
            ")")
    abstract public Completable removerPropostaPlanoAcao_ST(int idRegistoArea);


    @Query("DELETE FROM questionarioChecklistResultado WHERE idArea = :id AND idSeccao = :idSeccao AND tipo = :tipo")
    abstract public Completable removerArea(int id, String idSeccao, String tipo);


    @Query("INSERT INTO questionarioChecklistResultado (idArea, idSeccao, idItem, tipo, resposta, origem)" +
            "SELECT id as idArea, idSeccao, uid as idItem, tipo, :resposta as resposta, " + Identificadores.Origens.ORIGEM_BD + "  as origem " +
            "FROM itensChecklist as chk_itens " +
            "LEFT JOIN (SELECT idChecklist, idArea, id FROM areasChecklistResultado) as ar_chk_res " +
            "ON chk_itens.idChecklist = ar_chk_res.idChecklist AND chk_itens.idArea = ar_chk_res.idArea " +
            "WHERE ar_chk_res.id = :idRegistoArea AND chk_itens.tipo = :tipo AND chk_itens.idSeccao = :idSeccao")
    abstract public Completable inserirNaoAplicavel(int idRegistoArea, String idSeccao, String tipo, String resposta);

    //-------------------
    //Remover checklist
    //-------------------


    @Query("DELETE FROM propostaPlanoAcaoResultado WHERE idQuestao IN (" +
            "SELECT id " +
            "FROM questionarioChecklistResultado as quest_res " +
            "WHERE idArea IN (SELECT id FROM areasChecklistResultado WHERE idAtividade = :idAtividade)" +
            ")")
    abstract public Completable removerPropostaPlanoAcao_ST_Checklist(int idAtividade);


    @Query("DELETE FROM questionarioChecklistResultado WHERE idArea IN (SELECT id FROM areasChecklistResultado WHERE idAtividade = :idAtividade)")
    abstract public Completable removerQuestoes_Checklist(int idAtividade);

    @Query("DELETE FROM areasChecklistResultado WHERE idAtividade = :idAtividade")
    abstract public Completable removerArea_Checklist(int idAtividade);

    @Query("DELETE FROM imagensResultado WHERE id IN (SELECT id FROM areasChecklistResultado WHERE idAtividade = :idAtividade)")
    abstract public Completable removerImagens_Checklist(int idAtividade);

}
