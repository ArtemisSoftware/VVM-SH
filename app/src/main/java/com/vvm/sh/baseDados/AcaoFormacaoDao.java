package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.ui.atividadesPendentes.relatorios.AcaoFormacaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.AcaoFormacao;

import io.reactivex.Maybe;

@Dao
abstract public class AcaoFormacaoDao implements BaseDao<AcaoFormacaoResultado>{


    @Query("SELECT * FROM acoesFormacaoResultado as acs_form " +
            "LEFT JOIN (SELECT id as idTipo, descricao as designacao FROM tipos WHERE tipo = :tipo) as tp ON acs_form.idDesignacao = tp.idTipo " +
            "WHERE idAtividade = :idAtividade")
    abstract public Maybe<AcaoFormacao> obterAcaoFormacao(int idAtividade, String tipo);

}
