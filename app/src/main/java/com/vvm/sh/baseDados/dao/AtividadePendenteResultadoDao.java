package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
abstract public class AtividadePendenteResultadoDao implements BaseDao<AtividadePendenteResultado> {


    @Transaction
    @Query("SELECT *, " +

            "CASE WHEN formacao = 1 THEN  " + Identificadores.Relatorios.ID_RELATORIO_FORMACAO + " "+
            "ELSE 0 END as idRelatorio, " +

            "CASE WHEN formacao = 1 THEN  '" + Identificadores.Relatorios.FORMACAO + "' "+
            "ELSE '' END as nomeRelatorio, " +

            "CASE WHEN formacao = 1 THEN  1 " +
            "ELSE 0 END as possuiRelatorio, " +

            "CASE WHEN formacao = 1 AND IFNULL(ct_formando, 0) > 0 THEN  1 " +
            "ELSE 0 END as relatorioCompleto " +

            "FROM atividadesPendentes atp " +
            "LEFT JOIN (" +
            "SELECT ac_form.idAtividade, ct_formando " +
            "FROM acoesFormacaoResultado as ac_form " +
            "LEFT JOIN (SELECT idAtividade, COUNT(id) as ct_formando FROM formandosResultado WHERE selecionado = 1 GROUP BY idAtividade) as frm ON ac_form.idAtividade = frm.idAtividade " +
            ") as ac_form ON atp.id = ac_form.idAtividade " +
            "WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<AtividadePendenteRegisto>> obterAtividades(int idTarefa);

    @Transaction
    @Query("SELECT *, " +

            "CASE WHEN formacao = 1 THEN  " + Identificadores.Relatorios.ID_RELATORIO_FORMACAO + " "+
            "ELSE 0 END as idRelatorio, " +

            "CASE WHEN formacao = 1 THEN  '" + Identificadores.Relatorios.FORMACAO + "' "+
            "ELSE '' END as nomeRelatorio, " +

            "CASE WHEN formacao = 1 THEN  1 " +
            "ELSE 0 END as possuiRelatorio, " +

            "CASE WHEN formacao = 1 AND IFNULL(ct_formando, 0) > 0 THEN  1 " +
            "ELSE 0 END as relatorioCompleto " +

            "FROM atividadesPendentes atp " +
            "LEFT JOIN (" +
            "SELECT ac_form.idAtividade, ct_formando " +
            "FROM acoesFormacaoResultado as ac_form " +
            "LEFT JOIN (SELECT idAtividade, COUNT(id) as ct_formando FROM formandosResultado WHERE selecionado = 1 GROUP BY idAtividade) as frm ON ac_form.idAtividade = frm.idAtividade " +
            ") as ac_form ON atp.id = ac_form.idAtividade " +
            "WHERE atp.id = :id")
    abstract public Maybe<AtividadePendenteRegisto> obterAtividade(int id);


    @Query("DELETE FROM atividadesPendentesResultado WHERE id = :id")
    abstract public Single<Integer> remover(int id);
}
