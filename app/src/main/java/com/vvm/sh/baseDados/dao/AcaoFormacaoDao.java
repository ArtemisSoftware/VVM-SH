package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.AcaoFormacao;

import io.reactivex.Flowable;

@Dao
abstract public class AcaoFormacaoDao implements BaseDao<AcaoFormacaoResultado> {


    @Query("SELECT *," +
            "CASE WHEN ct_formados > 0 THEN 1 ELSE 0 END as completo " +
            "FROM acoesFormacaoResultado as acs_form " +
            "LEFT JOIN (SELECT id as idTipo, descricao as designacao FROM tipos WHERE tipo = :tipo) as tp " +
            "ON acs_form.idDesignacao = tp.idTipo " +

            "LEFT JOIN (" +
            "SELECT frm_res.idAtividade as idAtividade, COUNT(*) as ct_formados " +
            "FROM formandosResultado as frm_res " +
            "LEFT JOIN (SELECT id as idRegisto, imagem as assinatura FROM imagensResultado WHERE origem = 1) as img ON frm_res.id = img.idRegisto " +
            "WHERE selecionado = 1 AND assinatura NOT NULL " +
            "GROUP BY frm_res.idAtividade " +
            ") as frm ON acs_form.idAtividade = frm.idAtividade " +
            "WHERE acs_form.idAtividade = :idAtividade")
    abstract public Flowable<AcaoFormacao> obterAcaoFormacao(int idAtividade, String tipo);



}
