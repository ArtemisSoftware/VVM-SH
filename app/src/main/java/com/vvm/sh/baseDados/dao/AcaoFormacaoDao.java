package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.AcaoFormacao;
import com.vvm.sh.ui.ocorrencias.modelos.Ocore;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
abstract public class AcaoFormacaoDao implements BaseDao<AcaoFormacaoResultado> {


    @Query("SELECT * FROM acoesFormacaoResultado as acs_form " +
            "LEFT JOIN (SELECT id as idTipo, descricao as designacao FROM tipos WHERE tipo = :tipo) as tp ON acs_form.idDesignacao = tp.idTipo " +
            "WHERE idAtividade = :idAtividade")
    abstract public Maybe<AcaoFormacao> obterAcaoFormacao(int idAtividade, String tipo);



    @Query("SELECT COUNT(*) as ct " +
            "FROM formandosResultado as frm_res " +
            "LEFT JOIN (SELECT id as idImagem, imagem as assinatura FROM imagensResultado WHERE origem = 1) as img ON frm_res.id = img.idImagem " +
            "WHERE selecionado = 1 AND assinatura NOT NULL AND idAtividade = :idAtividade ")
    abstract public Flowable<Integer> obterValidadeFormacao(int idAtividade);

}
