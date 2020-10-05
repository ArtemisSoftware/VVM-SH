package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.util.constantes.Identificadores;

import static com.vvm.sh.util.constantes.Identificadores.Relatorios.*;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;


@Dao
abstract public class AtividadePendenteDao implements BaseDao<AtividadePendenteResultado> {


    @Transaction
    @Query("SELECT *, " +

            "CASE WHEN idRelatorio = " + ID_RELATORIO_FORMACAO + " THEN  '" + FORMACAO + "' " +
            "WHEN idRelatorio = " + ID_RELATORIO_ILUMINACAO + " THEN  '" + ILUMINACAO + "' "+
            "WHEN idRelatorio = " + ID_RELATORIO_TEMPERATURA_HUMIDADE + " THEN  '" + TEMPERATURA_E_HUMIDADE + "' "+
            "WHEN idRelatorio = " + ID_RELATORIO_AVALIACAO_RISCO + " THEN  '" + AVALIACAO_RISCO + "' "+
            "WHEN ct_averiguacao > 0 AND tipo = " + ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO + " THEN  '" + AVERIGUACAO_AVALIACAO_RISCO + "' "+
            "WHEN ct_averiguacao > 0 AND tipo = " + ID_RELATORIO_AVERIGUACAO_AUDITORIA + " THEN  '" + AVERIGUACAO_AUDITORIA + "' "+
            "ELSE '' END as nomeRelatorio, " +

            "CASE WHEN idRelatorio > 0 THEN  1 " +
            "WHEN ct_averiguacao > 0 AND tipo = " + ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO + " THEN  1 "+
            "WHEN ct_averiguacao > 0 AND tipo = " + ID_RELATORIO_AVERIGUACAO_AUDITORIA + " THEN  1 "+
            "ELSE 0 END as possuiRelatorio, " +


            "CASE WHEN idRelatorio = " + ID_RELATORIO_FORMACAO + " AND IFNULL(ct_formando, 0) > 0 THEN  1 " +
            "ELSE 0 END as relatorioCompleto " +

            "FROM atividadesPendentes as atp " +

            //relatorios

            "LEFT JOIN( " +
            "SELECT idTarefa, IFNULL(COUNT(*), 0) as ct_averiguacao, tipo FROM relatorioAveriguacao GROUP BY idTarefa " +
            ") as rel_averiguacao " +




            //formacao validacao

            "LEFT JOIN (" +
            "SELECT ac_form.idAtividade, ct_formando " +
            "FROM acoesFormacaoResultado as ac_form " +
            "LEFT JOIN (SELECT idAtividade, COUNT(id) as ct_formando FROM formandosResultado WHERE selecionado = 1 GROUP BY idAtividade) as frm ON ac_form.idAtividade = frm.idAtividade " +
            ") as ac_form ON atp.id = ac_form.idAtividade " +


            "WHERE atp.idTarefa = :idTarefa")
    abstract public Flowable<List<AtividadePendenteRegisto>> obterAtividades(int idTarefa);

    @Transaction
    @Query("SELECT *, " +

            "CASE WHEN idRelatorio = " + ID_RELATORIO_FORMACAO + " THEN  '" + FORMACAO + "' " +
            "WHEN idRelatorio = " + ID_RELATORIO_ILUMINACAO + " THEN  '" + ILUMINACAO + "' "+
            "WHEN idRelatorio = " + ID_RELATORIO_TEMPERATURA_HUMIDADE + " THEN  '" + TEMPERATURA_E_HUMIDADE + "' "+
            "WHEN idRelatorio = " + ID_RELATORIO_AVALIACAO_RISCO + " THEN  '" + AVALIACAO_RISCO + "' "+
            "WHEN ct_averiguacao > 0 AND tipo = " + ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO + " THEN  '" + AVERIGUACAO_AVALIACAO_RISCO + "' "+
            "WHEN ct_averiguacao > 0 AND tipo = " + ID_RELATORIO_AVERIGUACAO_AUDITORIA + " THEN  '" + AVERIGUACAO_AUDITORIA + "' "+
            "ELSE '' END as nomeRelatorio, " +

            "CASE WHEN idRelatorio > 0 THEN  1 " +
            "WHEN ct_averiguacao > 0 AND tipo = " + ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO + " THEN  1 "+
            "WHEN ct_averiguacao > 0 AND tipo = " + ID_RELATORIO_AVERIGUACAO_AUDITORIA + " THEN  1 "+
            "ELSE 0 END as possuiRelatorio, " +

            "CASE WHEN idRelatorio = " + ID_RELATORIO_FORMACAO + " AND IFNULL(ct_formando, 0) > 0 THEN  1 " +
            "ELSE 0 END as relatorioCompleto " +

            "FROM atividadesPendentes as atp " +

            //relatorios

            "LEFT JOIN( " +
            "SELECT idTarefa, IFNULL(COUNT(*), 0) as ct_averiguacao, tipo FROM relatorioAveriguacao GROUP BY idTarefa " +
            ") as rel_averiguacao " +

            //formacao validacao

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
