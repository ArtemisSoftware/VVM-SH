package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.PropostaPlanoAcaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos.Proposta;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class PropostaPlanoAcaoDao implements BaseDao<PropostaPlanoAcaoResultado> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract public Single<List<Long>> inserir(List<PropostaPlanoAcaoResultado> entity);


    @Query("SELECT DISTINCT * , descricao, " + Identificadores.Origens.PROPOSTA_CONDICOES_ST + " as tipo " +
            "FROM propostaPlanoAcaoResultado as prop_pl_accao_res " +
            "LEFT JOIN(" +
            "SELECT quest_chk_res.id as id, uid || ' ' || descricao as descricao " +
            "FROM questionarioChecklistResultado as quest_chk_res " +
            "LEFT JOIN (SELECT idChecklist, idArea, id FROM areasChecklistResultado) as area_chk_res " +
            "ON quest_chk_res.idArea = area_chk_res.id " +
            "LEFT JOIN (SELECT idChecklist, idArea, idSeccao, uid, descricao FROM itensChecklist) as itens_chk " +
            "ON area_chk_res.idChecklist = itens_chk.idChecklist AND area_chk_res.idArea = itens_chk.idArea AND quest_chk_res.idItem = itens_chk.uid " +
            ") as descricao ON prop_pl_accao_res.idQuestao = descricao.id " +
            "WHERE idAtividade = :idAtividade AND origem = " + Identificadores.Origens.CHECKLIST +"")
    abstract public Observable<List<Proposta>> obterPropostasSt(int idAtividade);



    @Query("SELECT DISTINCT * , descricao, " + Identificadores.Origens.PROPOSTA_MEDIDAS_AVALIACAO + " as tipo " +
            "FROM propostaPlanoAcaoResultado as prop_pl_accao_res " +
            "LEFT JOIN (SELECT id, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS + "' AND ativo = 1 AND api =:api) as tp_med " +
            "ON prop_pl_accao_res.idMedida = tp_med.id " +
            "WHERE idAtividade = :idAtividade AND origem = " + Identificadores.Origens.ORIGEM_LEVANTAMENTO_RISCO +" AND descricao IS NOT NULL")
    abstract public Observable<List<Proposta>> obterMedidasAvaliacao(int idAtividade, int api);


    @Query("UPDATE propostaPlanoAcaoResultado SET selecionado = :selecionado WHERE idAtividade = :idAtividade AND origem = " + Identificadores.Origens.ORIGEM_LEVANTAMENTO_RISCO + "")
    abstract public Completable selecionarTudo(int idAtividade, boolean selecionado);

    @Query("UPDATE propostaPlanoAcaoResultado SET selecionado = :selecionado WHERE idAtividade = :idAtividade AND id = :id AND origem = " + Identificadores.Origens.ORIGEM_LEVANTAMENTO_RISCO + "")
    abstract public Completable selecionar(int idAtividade, int id, boolean selecionado);


    @Query("INSERT INTO propostaPlanoAcaoResultado (idAtividade, idQuestao, origem, idMedida, selecionado) " +
            "SELECT :idAtividade as idAtividade,  rsc_res.id as id, " + Identificadores.Origens.ORIGEM_LEVANTAMENTO_RISCO + " as origem, idMedida, 1 as selecionado " +
            "FROM riscosResultado as rsc_res  " +
            "LEFT JOIN (SELECT id, idMedida FROM medidasResultado WHERE origem = :origem) med_res ON rsc_res.id = med_res.id " +
            "WHERE  rsc_res.idLevantamento IN (SELECT idLevantamento FROM levantamentosRiscoResultado WHERE idAtividade = :idAtividade AND idModelo = :idModelo )")
    abstract public Completable inserirModelo(int idAtividade, int idModelo, int origem);





    //remover


    @Query("DELETE FROM propostaPlanoAcaoResultado WHERE idQuestao = :idQuestao AND origem = :origem")
    abstract public Single<Integer> removerQuestoes(int idQuestao, int origem);

    @Query("DELETE FROM propostaPlanoAcaoResultado WHERE idQuestao IN (SELECT id FROM riscosResultado WHERE idLevantamento =:idLevantamento ) AND origem = :origem")
    abstract public Single<Integer> remover(int idLevantamento, int origem);


    @Query("DELETE FROM propostaPlanoAcaoResultado WHERE idQuestao IN (SELECT id FROM riscosResultado WHERE idLevantamento IN(:idsLevantamento) ) AND origem = :origem")
    abstract public Single<Integer> remover(List<Integer> idsLevantamento, int origem);

    @Query("DELETE FROM propostaPlanoAcaoResultado WHERE idQuestao IN (SELECT id FROM riscosResultado WHERE id = :idRisco ) AND origem = :origem")
    abstract public Single<Integer> removerRisco(int idRisco, int origem);


    @Query("DELETE FROM propostaPlanoAcaoResultado " +
            "WHERE idQuestao IN (SELECT id FROM riscosResultado WHERE idLevantamento IN (SELECT id FROM levantamentosRiscoResultado WHERE idAtividade =:idAtividade AND idModelo =:idModelo) ) AND origem = :origem")
    abstract public Completable removerModelo(int idAtividade, int idModelo, int origem);
}
