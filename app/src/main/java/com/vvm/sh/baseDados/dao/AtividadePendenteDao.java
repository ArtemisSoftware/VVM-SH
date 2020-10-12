package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import static com.vvm.sh.util.constantes.Identificadores.Relatorios.*;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
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
            "WHEN  idRelatorio = " + ID_RELATORIO_ILUMINACAO + " AND validade_aval_amb_ilum = 1 THEN  1 "+
            "WHEN  idRelatorio = " + ID_RELATORIO_TEMPERATURA_HUMIDADE + " AND validade_aval_amb_temperatura = 1 THEN  1 "+
            "WHEN  idRelatorio = " + ID_RELATORIO_AVALIACAO_RISCO + " AND  (validade_processo_produtivo AND validade_equipamentos) = 1 THEN 1 "+
            "ELSE 0 END as relatorioCompleto," +

            "validade_processo_produtivo, validade_trabalhadores_vulneraveis, validade_equipamentos " +
            " " +

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


            //avaliacao ambiental - temperatura + humidade

            "LEFT JOIN( " +

            "SELECT idAtividade,  " +
            "CASE " +
            "WHEN marca IS NULL OR numeroSerie IS NULL OR data IS NULL THEN 0 " +
            "WHEN  avaliacoesValido = 0 THEN 0 " +
            "ELSE 1 " +
            "END as validade_aval_amb_temperatura " +

            "FROM relatorioAmbientalResultado as rel_amb_res " +

            "LEFT JOIN( " +

            "SELECT idRelatorio, " +
            "CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END as avaliacoesValido " +
            "FROM (" +
            "SELECT  idRelatorio,	" +
            "CASE WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22) AND  IFNULL(numeroMedidas, 0)  = 0 THEN 0	" +
            "WHEN  (CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70) AND  IFNULL(numeroMedidas, 0)  = 0 THEN 0 " +
            "ELSE 1 END as valido  " +


            "FROM avaliacoesAmbientaisResultado as av_amb_res	" +

            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas FROM medidasResultado WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE_MEDIDAS_RECOMENDADAS + " GROUP BY id) as ct_medidas " +
            "ON av_amb_res.id = ct_medidas.id	" +
            ") as validacao " +
            "GROUP BY idRelatorio" +

            ") as vald_iluminacao ON rel_amb_res.id = vald_iluminacao.idRelatorio " +

            ") as avl_amb_term ON atp.id = avl_amb_term.idAtividade " +

            //avaliacao ambiental - iluminacao

            "LEFT JOIN( " +

            "SELECT idAtividade,  " +
            "CASE " +
            "WHEN marca IS NULL OR numeroSerie IS NULL OR data IS NULL THEN 0 " +
            "WHEN  avaliacoesValido = 0 THEN 0 " +
            "ELSE 1 " +
            "END as validade_aval_amb_ilum " +

            "FROM relatorioAmbientalResultado as rel_amb_res " +

            "LEFT JOIN( " +

            "SELECT idRelatorio, " +
            "CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END as avaliacoesValido "  +

            "FROM(   " +
            "SELECT idRelatorio,   " +
            "CASE WHEN CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) AND IFNULL(numeroMedidas, 0) = 0 THEN 0  " +
            "ELSE 1 END as valido  " +
            "FROM avaliacoesAmbientaisResultado as av_amb_res  " +

            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas FROM medidasResultado WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO_MEDIDAS_RECOMENDADAS + " GROUP BY id) as ct_medidas " +
            "ON av_amb_res.id = ct_medidas.id  " +
            ") as validacao " +
            "GROUP BY idRelatorio" +

            ") as vald_iluminacao ON rel_amb_res.id = vald_iluminacao.idRelatorio " +

            ") as avl_amb_ilum ON atp.id = avl_amb_ilum.idAtividade " +


            //processo produtivo

            "LEFT JOIN (   " +
            "SELECT id as idAtividade, CASE WHEN descricao IS NULL OR descricao = '' THEN 0 ELSE 1 END as validade_processo_produtivo FROM processosProdutivosResultado  " +

            ") as process_prod_res ON atp.id = process_prod_res.idAtividade " +


            //trabalhadore vulneraveis

            "LEFT JOIN(   " +
            "SELECT tb_vul_res.idAtividade as idAtividade, CASE WHEN COUNT(tb_vul_res.id) > 0 THEN 1 ELSE 0 END as validade_trabalhadores_vulneraveis    " +
            "FROM trabalhadoresVulneraveisResultado as tb_vul_res     " +
            "LEFT JOIN (SELECT id, homens  FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_HOMENS + " GROUP BY id ) as ctPro_homens " +
            "ON  tb_vul_res.id = ctPro_homens.id    " +
            "LEFT JOIN (SELECT id, mulheres  FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_MULHERES + " GROUP BY id) as ctPro_mulheres " +
            "ON  tb_vul_res.id = ctPro_mulheres.id   " +
            "WHERE IFNULL(ctPro_homens.homens,0) != 0 OR IFNULL(ctPro_mulheres.mulheres,0) != 0   " +
            ") as vld_tbr_vul ON atp.id = vld_tbr_vul.idAtividade		   " +


            //equipamentos


            "LEFT JOIN (    " +
            "SELECT idAtividade, CASE WHEN IFNULL(COUNT (idEquipamento), 0) > 0 THEN 1 ELSE 0 END as validade_equipamentos     "+
            "FROM verificacaoEquipamentosResultado     "+
            "GROUP by idAtividade    "+
            ")as vld_eqp ON atp.id = vld_eqp.idAtividade	    "+





            "WHERE atp.idTarefa = :idTarefa")
    abstract public Observable<List<AtividadePendenteRegisto>> obterAtividades(int idTarefa);



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
            "ELSE 0 END as relatorioCompleto, " +

            "0 as validade_processo_produtivo, 0 as validade_trabalhadores_vulneraveis, 0 as validade_equipamentos " +

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
