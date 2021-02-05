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
            "WHEN idRelatorio = " + ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO + " THEN  '" + AVERIGUACAO_AVALIACAO_RISCO + "' "+
            "WHEN idRelatorio = " + ID_RELATORIO_CERTIFICADO_TRATAMENTO + " THEN  '" + CERTIFICADO_TRATAMENTO + "' "+
            "ELSE '' END as nomeRelatorio, " +

            "CASE WHEN idRelatorio > 0 THEN  1 " +
            "ELSE 0 END as possuiRelatorio, " +


            "CASE WHEN idRelatorio = " + ID_RELATORIO_FORMACAO + " AND IFNULL(ct_formando, 0) > 0 THEN  1 " +
            "WHEN  idRelatorio = " + ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO + " AND validade_relatorio_avaliacao = 1 THEN  1 " +
            "WHEN  idRelatorio = " + ID_RELATORIO_ILUMINACAO + " AND validade_aval_amb_ilum = 1 THEN  1 "+
            "WHEN  idRelatorio = " + ID_RELATORIO_TEMPERATURA_HUMIDADE + " AND validade_aval_amb_temperatura = 1 THEN  1 "+
            "WHEN  idRelatorio = " + ID_RELATORIO_AVALIACAO_RISCO + " AND  (validade_processo_produtivo AND validade_equipamentos AND validade_proposta_plano_acao AND validade_checklist AND validade_avaliacao_riscos) = 1 THEN 1 "+
            "WHEN  idRelatorio = " + ID_RELATORIO_CERTIFICADO_TRATAMENTO + " AND validade_certificado_tratamento = 1 THEN  1 " +
            "ELSE 0 END as relatorioCompleto," +

            "validade_processo_produtivo, validade_trabalhadores_vulneraveis, validade_equipamentos, validade_proposta_plano_acao, validade_checklist, validade_avaliacao_riscos, " +
            "validade_certificado_tratamento, " +
            "capa_Relatorio " +

            "FROM atividadesPendentes as atp " +


            //certificado tratamento

            "LEFT JOIN ( " +
            "SELECT idAtividade, sincronizacao as validade_certificado_tratamento FROM certificadoTratamentoResultado " +
            ") as cert_tratamento ON atp.id = cert_tratamento.idAtividade  " +

            //capa relatorio


            "LEFT JOIN ( " +
            "SELECT idTarefa, COUNT(idImagem) as capa_Relatorio FROM imagensResultado WHERE capaRelatorio = 1 GROUP BY idTarefa " +
            ") as cp_rel ON atp.idTarefa = cp_rel.idTarefa  " +

            //relatorio avaliacao de riscos


            "LEFT JOIN ( " +
            "SELECT idTarefa, COUNT(VALIDO) , SUM(VALIDO), CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END as validade_relatorio_avaliacao " +
            "FROM ( " +
            "SELECT rel_avg.id as id, descricao, tipo, numeroRegistos, total, idTarefa, " +
            "CASE WHEN numeroRegistos = total THEN 1 ELSE 0 END as valido " +
            "FROM relatorioAveriguacao as rel_avg " +
            "LEFT JOIN (SELECT id, IFNULL(COUNT(*), 0) as total FROM medidasAveriguacao GROUP BY id) as med " +
            "ON rel_avg.id = med.id " +
            "LEFT JOIN (SELECT idRelatorio, IFNULL(COUNT(*), 0) as numeroRegistos FROM relatorioAveriguacaoResultado GROUP BY idRelatorio) as rel_avg_res " +
            "ON rel_avg.id = rel_avg_res.idRelatorio" +
            ") as validacao GROUP BY idTarefa" +
            ") as valid_relatorio_avaliacao ON atp.idTarefa = valid_relatorio_avaliacao.idTarefa " +

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
            "WHEN  IFNULL(avaliacoesValido, 0) = 0 THEN 0 " +
            "ELSE 1 " +
            "END as validade_aval_amb_temperatura " +

            "FROM relatorioAmbientalResultado as rel_amb_res " +

            "LEFT JOIN( " +

            "SELECT idRelatorio, " +
            "CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END as avaliacoesValido " +
            "FROM (" +
            "SELECT  idRelatorio,	" +
            "CASE " +
            "WHEN IFNULL(ct_categorias_prof, 0) = 0 THEN 0  " +
            "WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22) AND  IFNULL(numeroMedidas, 0)  = 0 THEN 0	" +
            "WHEN  (CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70) AND  IFNULL(numeroMedidas, 0)  = 0 THEN 0 " +
            "ELSE 1 END as valido  " +


            "FROM avaliacoesAmbientaisResultado as av_amb_res	" +

            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas FROM medidasResultado WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE_MEDIDAS_RECOMENDADAS + " GROUP BY id) as ct_medidas " +
            "ON av_amb_res.id = ct_medidas.id	" +

            "LEFT JOIN (SELECT idRegisto , IFNULL(COUNT(id), 0) as ct_categorias_prof FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE + " GROUP BY idRegisto) as ct_categorias_profissionais " +
            "ON av_amb_res.id = ct_categorias_profissionais.idRegisto  " +
            ") as validacao " +
            "GROUP BY idRelatorio" +

            ") as vald_iluminacao ON rel_amb_res.id = vald_iluminacao.idRelatorio " +

            ") as avl_amb_term ON atp.id = avl_amb_term.idAtividade " +



            //avaliacao ambiental - iluminacao

            "LEFT JOIN( " +

            "SELECT idAtividade,  " +
            "CASE " +
            "WHEN marca IS NULL OR numeroSerie IS NULL OR data IS NULL THEN 0 " +
            "WHEN  IFNULL(avaliacoesValido, 0) = 0 THEN 0 " +
            "ELSE 1 " +
            "END as validade_aval_amb_ilum " +

            "FROM relatorioAmbientalResultado as rel_amb_res " +

            "LEFT JOIN( " +

            "SELECT idRelatorio, " +
            "CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END as avaliacoesValido "  +

            "FROM(   " +
            "SELECT idRelatorio,   " +
            "CASE " +
            "WHEN IFNULL(ct_categorias_prof, 0) = 0 THEN 0  " +
            "WHEN  CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) AND  IFNULL(numeroMedidas, 0) > 0 THEN 1 " +
            "WHEN  (CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100))) = 0 THEN 1 " +
            "ELSE 0 END as valido " +
            "FROM avaliacoesAmbientaisResultado as av_amb_res  " +

            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas FROM medidasResultado WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO_MEDIDAS_RECOMENDADAS + " GROUP BY id) as ct_medidas " +
            "ON av_amb_res.id = ct_medidas.id  " +

            "LEFT JOIN (SELECT idRegisto , IFNULL(COUNT(id), 0) as ct_categorias_prof FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO + " GROUP BY idRegisto) as ct_categorias_profissionais " +
            "ON av_amb_res.id = ct_categorias_profissionais.idRegisto  " +

            ") as validacao " +
            "GROUP BY idRelatorio" +

            ") as vald_iluminacao ON rel_amb_res.id = vald_iluminacao.idRelatorio " +

            ") as avl_amb_ilum ON atp.id = avl_amb_ilum.idAtividade " +


            //processo produtivo

            "LEFT JOIN (   " +
            "SELECT id as idAtividade, CASE WHEN descricao IS NULL OR descricao = '' THEN 0 ELSE 1 END as validade_processo_produtivo " +
            "FROM processosProdutivosResultado  " +
            ") as process_prod_res ON atp.id = process_prod_res.idAtividade " +

            //proposta plano acao

            "LEFT JOIN ( " +
            "SELECT idAtividade, CASE WHEN IFNULL(COUNT(id), 0) > 0 THEN 1 ELSE 0 END as validade_proposta_plano_acao " +
            "FROM propostaPlanoAcaoResultado " +
            "WHERE selecionado = 1 GROUP by idAtividade" +
            ") as proposta_plano_acao ON atp.id = proposta_plano_acao.idAtividade " +



            //trabalhadore vulneraveis

            "LEFT JOIN(   " +

            "SELECT idAtividade, CASE WHEN IFNULL(COUNT(contagem), 0)  > 0 THEN 1 ELSE 0 END as validade_trabalhadores_vulneraveis " +
            "FROM ( " +

            "SELECT tb_vul_res.idAtividade as idAtividade, " +
            "(ct_homens + ct_mulheres) as contagem " +
            "FROM trabalhadoresVulneraveisResultado as tb_vul_res     " +

            "LEFT JOIN (SELECT idRegisto , IFNULL(COUNT(homens), 0) as ct_homens  FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_HOMENS + " GROUP BY idRegisto  ) as ctPro_homens " +
            "ON  tb_vul_res.id = ctPro_homens.idRegisto     " +

            "LEFT JOIN (SELECT idRegisto , IFNULL(COUNT(mulheres), 0) as ct_mulheres  FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_MULHERES + " GROUP BY idRegisto ) as ctPro_mulheres " +
            "ON  tb_vul_res.id = ctPro_mulheres.idRegisto    " +
            ")" +
            ") as vld_tbr_vul ON atp.id = vld_tbr_vul.idAtividade " +

            //equipamentos


            "LEFT JOIN (    " +
            "SELECT idAtividade, CASE WHEN IFNULL(COUNT (idEquipamento), 0) > 0 THEN 1 ELSE 0 END as validade_equipamentos     "+
            "FROM verificacaoEquipamentosResultado     "+
            "GROUP by idAtividade    "+
            ")as vld_eqp ON atp.id = vld_eqp.idAtividade	    "+


            //checklist

            "LEFT JOIN (    " +

            "SELECT idAtividade,  "+
            "CASE WHEN itens.tipo = 'q' AND IFNULL(numeroRespostas,0) = 0 THEN 0    "+
            "WHEN itens.tipo = 'q' AND IFNULL(numeroRespostas,0) != total THEN 0    "+
            "ELSE 1 END as validade_checklist    "+

            "FROM areasChecklistResultado as ar_chk_res    "+

            "LEFT JOIN (" +
            "SELECT idChecklist, idArea, uid as idSeccao, tipo  " +
            "FROM seccoesChecklist" +
            ") as chk_scs " +
            "ON ar_chk_res.idChecklist = chk_scs.idChecklist AND ar_chk_res.idArea = chk_scs.idArea    "+

            "LEFT JOIN (    "+
            "SELECT COUNT(tipo) as total, idChecklist, idArea, idSeccao, tipo " +
            "FROM itensChecklist    "+
            "WHERE  tipo = '"+ Identificadores.Checklist.TIPO_QUESTAO + "' "+
            "GROUP BY idChecklist, idArea , idSeccao    "+
            ") as itens     "+
            "ON ar_chk_res.idChecklist = itens.idChecklist AND ar_chk_res.idArea = itens.idArea AND chk_scs.idSeccao = itens.idSeccao    "+

            "LEFT JOIN (    "+
            "SELECT idArea, idSeccao, COUNT(idItem) as numeroRespostas FROM questionarioChecklistResultado    "+
            "WHERE  tipo = '"+ Identificadores.Checklist.TIPO_QUESTAO + "' "+
            "GROUP BY idArea, idSeccao    "+
            ") as qst_res ON ar_chk_res.id = qst_res.idArea AND chk_scs.idSeccao = qst_res.idSeccao    " +

            "GROUP BY idAtividade"+

            ") as vld_checklist ON atp.id = vld_checklist.idAtividade " +


            //levantamento de risco

            "LEFT JOIN (    " +
            "SELECT idAtividade, CASE WHEN COUNT(valido) = SUM(valido) AND COUNT(valido) > 0 THEN 1 ELSE 0 END validade_avaliacao_riscos " +
            "FROM( " +
            "SELECT idAtividade, CASE WHEN (tarefa != '' AND perigo != '' AND valido_categorias_prof = 1 AND valido_riscos = 1) THEN 1  ELSE 0 END as valido " +
            "FROM levantamentosRiscoResultado as lv_riscos_res " +

            "LEFT JOIN(" +
            "SELECT idLevantamento, " +
            "CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido_riscos   " +
            "FROM (			  " +
            "SELECT idLevantamento,			  " +
            "CASE WHEN nd IS NULL OR nd = '' THEN 0   			  " +
            "WHEN numeroMedidasExistentes IS NULL AND numeroMedidasRecomendadas IS NULL THEN 0  			  " +
            "WHEN numeroMedidasExistentes = 0 OR numeroMedidasRecomendadas = 0 THEN 0 			  " +
            "ELSE 1 END as valido			  " +
            "FROM riscosResultado  as rsc_res			  " +
            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidasExistentes  FROM medidasResultado " +
            "WHERE origem = "+ Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS +" GROUP BY id) as med_existentes " +
            "ON rsc_res.id = med_existentes.id " +
            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidasRecomendadas  FROM medidasResultado " +
            "WHERE origem = "+ Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS +" GROUP BY id) as med_recomendadas " +
            "ON rsc_res.id = med_recomendadas.id			  " +
            ") as resultado " +
            "GROUP BY idLevantamento " +
            ") as vald_riscos ON lv_riscos_res.id = vald_riscos.idLevantamento	" +

            "LEFT JOIN( " +
            "SELECT idRegisto as idLevantamento, CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido_categorias_prof " +
            "FROM( " +
            "SELECT idRegisto, CASE WHEN (homens = 0 AND mulheres = 0) OR (homens IS NULL AND mulheres IS NULL) THEN 0 ELSE 1 END as valido " +
            "FROM categoriasProfissionaisResultado " +
            "WHERE origem = " + Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS + " " +
            ")" +
            "GROUP BY idRegisto   " +
            ") as vald_categorias_prof ON lv_riscos_res.id = vald_categorias_prof.idLevantamento " +


            ") " +
            "GROUP BY idAtividade " +
            ") as vld_avaliacao_riscos ON atp.id = vld_avaliacao_riscos.idAtividade	    " +







//
//    query += "OUTER LEFT JOIN (    ";
//    query += "SELECT idAtividade, CASE WHEN COUNT(lv_rsc_valido) = SUM(lv_rsc_valido) AND COUNT(lv_rsc_valido) > 0 THEN 1 ELSE 0 END lv_rsc_valido ";
//    query += "FROM( ";
//
//    query += "SELECT id as idAtividade, CASE WHEN (ct_pro_valido + rsc_valido) = 2 THEN 1 ELSE 0 END as lv_rsc_valido            ";
//    query += "FROM levantamentosRisco_resultado as lv_rsc_res     ";
//    query += "OUTER LEFT JOIN(    ";
//    query += "SELECT CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END ct_pro_valido, idLevantamento    ";
//    query += "FROM(    ";
//    query += "SELECT CASE WHEN (homens = 0 AND mulheres = 0) OR (homens IS NULL AND mulheres IS NULL) THEN 0 ELSE 1 END as valido, id as idLevantamento, origem    ";
//    query += "FROM categoriasProfissionais_resultado     ";
//    query += ") as resultado    ";
//    query += "WHERE origem = ?     ";
//    query += "GROUP BY idLevantamento    ";
//    query += ") as ct_pro ON lv_rsc_res.idLevantamento = ct_pro.idLevantamento    ";
//    query += "OUTER LEFT JOIN(    ";
//    query += "SELECT CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END rsc_valido, idLevantamento    ";
//    query += "FROM (    ";
//    query += "SELECT idLevantamento,    ";
//    query += "CASE WHEN nd IS NULL THEN 0    ";
//    query += "WHEN numeroMedidasExistentes IS NULL AND numeroMedidasRecomendadas IS NULL THEN 0      ";
//    query += "WHEN numeroMedidasExistentes = 0 OR numeroMedidasRecomendadas = 0 THEN 0     ";
//    query += "ELSE 1 END as valido    ";
//    query += "FROM riscos_resultado  as rsc_res    ";
//    query += "OUTER LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidasExistentes  FROM medidas_resultado WHERE origem = ? GROUP BY id) as med_existentes ON  rsc_res.idRegisto = med_existentes.id     ";
//    query += "OUTER LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidasRecomendadas  FROM medidas_resultado WHERE origem = ? GROUP BY id) as med_recomendadas ON  rsc_res.idRegisto = med_recomendadas.id    ";
//    query += ") as resultado    ";
//    query += "GROUP BY idLevantamento    ";
//    query += ") as rsc ON lv_rsc_res.idLevantamento = rsc.idLevantamento    ";
//
//    //query += "GROUP BY id    ";
//    query += ") ";
//    query += "GROUP BY idAtividade ";
//    query += ") as vld_avaliacao_riscos ON atp.id = vld_avaliacao_riscos.idAtividade	    ";





            "WHERE atp.idTarefa = :idTarefa")
    abstract public Observable<List<AtividadePendenteRegisto>> obterAtividades(int idTarefa);


//
//
//    String query = "SELECT atp.id as id, servID || ' - ' || descricao as descricao,  dataProg, anuidade,    ";
//
//    query += "CASE WHEN relatorioIluminacao = 1          		THEN " + IdentificadoresIF.ID_RELATORIO_ILUMINACAO + " ";
//    query += "WHEN relatorioTemperaturaHumidade = 1 			THEN " + IdentificadoresIF.ID_RELATORIO_TEMPERATURA_HUMIDADE + " ";
//    query += "WHEN verificacao = 'auditoria'        			THEN " + IdentificadoresIF.ID_RELATORIO_AUDITORIA + " ";
//    query += "WHEN verificacao = 'arpt'             			THEN " + IdentificadoresIF.ID_RELATORIO_AVALIACAO_RISCOS + "   ";
//    query += "WHEN avaliacaoRiscos = 1              			THEN " + IdentificadoresIF.ID_AVALIACAO_RISCOS + " ";
//    query += "WHEN formacao = 1              					THEN " + IdentificadoresIF.ID_RELATORIO_FORMACAO + "   ";
//    query += "ELSE -1 END as idRelatorio,   ";
//
//    query += "CASE WHEN relatorioIluminacao = 1          		THEN 'Relat-rio de iluminação'   ";
//    query += "WHEN relatorioTemperaturaHumidade = 1 			THEN 'Relat-rio de temperatura e humidade'   ";
//    query += "WHEN verificacao = 'auditoria'        			THEN 'Relat-rio de Auditoria'   ";
//    query += "WHEN verificacao = 'arpt'             			THEN '" + SintaxeIF.RELATORIO_AVALIACAO_RISCOS + "'   ";
//    query += "WHEN avaliacaoRiscos = 1              			THEN 'AVR'   ";
//    query += "WHEN formacao = 1              					THEN '" + SintaxeIF.RELATORIO_FORMACAO + "'   ";
//    query += "ELSE 0 END as relatorio,   ";
//
//    query += "CASE WHEN relatorioIluminacao = 1    			 AND IFNULL(rel_amb_valido, 0) = 1 	AND IFNULL(rel_ilm_avl_valido, 0) = 1	  			THEN 1  ";
//    query += "WHEN relatorioTemperaturaHumidade = 1 	 AND IFNULL(rel_amb_valido, 0) = 1 	AND IFNULL(rel_tmp_hum_avl_valido, 0) = 1 	THEN 1   ";
//    query += "WHEN verificacao = 'auditoria'        			 				THEN 0   ";
//    query += "WHEN verificacao = 'arpt' AND rel_avr_validade = 1   THEN 1   ";
//    query += " WHEN avaliacaoRiscos = 1  AND processoProdutivo_validade = 1 AND		IFNULL(ct_equipamentos, 0) > 0 AND		IFNULL(ct_plano_acao, 0) > 0  AND		lv_rsc_valido = 1		AND IFNULL(chk_valido, 0) = 1 THEN 1 ";
//    query += "WHEN formacao = 1  AND IFNULL(formandos_valido, 0) = 1 AND IFNULL(acao_formacao_valido, 0) = 1 THEN 1 ";
//
//    query += "ELSE 0 END as validade_relatorio,   ";
//
//    query += "CASE WHEN idChecklist IS NOT NULL THEN idChecklist ELSE checklistAVR END as idChecklist,        ";
//    query += "IFNULL(estado, -1) as estado, tempoExecucao, dataExecucao, idAnomalia, observacao,   ";
//    query += "IFNULL(idRelatorioAmbiental, '') as idRelatorioAmbiental,   ";
//    query += "processoProdutivo_validade, IFNULL(tbr_vul_validade,0 ) as tbr_vul_validade, IFNULL(ct_equipamentos, 0) as ct_equipamentos, IFNULL(ct_plano_acao, 0) as ct_plano_acao, lv_rsc_valido, IFNULL(chk_valido, 0) as chk_valido,  ";
//    query += "CASE WHEN capa_Relatorio > 0 THEN 1 ELSE 0 END as capa_Relatorio_validade ";
//
//    query += "FROM atividadesPendentes as atp   ";
//
//    query += "OUTER LEFT JOIN (SELECT id, estado, tempoExecucao, dataExecucao, idAnomalia, observacao FROM atividadesPendentes_resultado) as atp_res ON atp.id = atp_res.id   ";
//    query += "OUTER LEFT JOIN (SELECT DISTINCT idAtividadePendente, idChecklist FROM areas_checklist_resultado) as ar_chk_res ON atp.id = ar_chk_res.idAtividadePendente  ";
//    query += "OUTER LEFT JOIN (SELECT id, CASE WHEN descricao IS NULL THEN 0 ELSE 1 END as processoProdutivo_validade FROM processoProdutivo_resultado) as pp_res ON atp.id = pp_res.id   ";
//
//    query += "OUTER LEFT JOIN (SELECT idTarefa, COUNT(idRegisto) as capa_Relatorio FROM imagens_resultado WHERE capaRelatorio IS NOT NULL GROUP BY idTarefa) as cp_rel ON atp.idTarefa = cp_rel.idTarefa   ";
//
//
//    query += "OUTER LEFT JOIN (SELECT id, idRelatorio as idRelatorioAmbiental FROM relatorioAmbiental_resultado) as rel_amb_res ON atp.id = rel_amb_res.id   ";
//
//    query += "OUTER LEFT JOIN(   ";
//    query += "SELECT idTarefa, CASE WHEN ct_medidas_implementadas = ct_medidas THEN 1 ELSE 0 END as rel_avr_validade   ";
//    query += "FROM(   ";
//    query += "SELECT rel_avr_rsc.idTarefa, SUM (ct_medidas_implementadas) as ct_medidas_implementadas, SUM(ct_medidas) as ct_medidas  ";
//    query += "FROM relatorioAvaliacaoRiscos as rel_avr_rsc   ";
//    query += "OUTER LEFT JOIN (SELECT idRelatorio, COUNT(idMedida) as ct_medidas FROM relatorioAvaliacaoRiscosMedidas GROUP by idRelatorio) as rel_avr_rsc_med ON rel_avr_rsc.idRelatorio = rel_avr_rsc_med.idRelatorio   ";
//    query += "OUTER LEFT JOIN (SELECT idTarefa, idRelatorio, COUNT(medida) as ct_medidas_implementadas FROM relatorioAveriguacao_resultado GROUP by idTarefa, idRelatorio) as rel_avg_res    ";
//    query += "ON rel_avr_rsc.idTarefa = rel_avg_res.idTarefa AND rel_avr_rsc_med.idRelatorio = rel_avg_res.idRelatorio   ";
//    query += "GROUP BY rel_avr_rsc.idTarefa ";
//    query += " )  as validade ";
//
//    query += "GROUP BY idTarefa   ";
//
//    query += ") as rel_avr_res ON atp.idTarefa = rel_avr_res.idTarefa   ";
//
//    query += "OUTER LEFT JOIN(   ";
//    query += "SELECT tb_vul_res.id, CASE WHEN COUNT(idRegisto) > 0 THEN 1 ELSE 0 END as tbr_vul_validade    ";
//    query += "FROM trabalhadoresVulneraveis_resultado as tb_vul_res     ";
//    query += "OUTER LEFT JOIN (SELECT id, homens  FROM categoriasProfissionais_resultado WHERE origem = 13 GROUP BY id ) as ctPro_homens ON  tb_vul_res.idRegisto = ctPro_homens.id    ";
//    query += "OUTER LEFT JOIN (SELECT id, mulheres  FROM categoriasProfissionais_resultado WHERE origem = 14 GROUP BY id) as ctPro_mulheres ON  tb_vul_res.idRegisto = ctPro_mulheres.id   ";
//    query += "WHERE IFNULL(homens,0) != 0 OR IFNULL(mulheres,0) != 0   ";
//    query += ") as vld_tbr_vul ON atp.id = vld_tbr_vul.id		   ";
//
//
//    query += "OUTER LEFT JOIN (    ";
//    query += "SELECT id, COUNT (idEquipamento) as ct_equipamentos     ";
//    query += "FROM verificacaoEquipamentos_resultado     ";
//    query += "GROUP by id    ";
//    query += ")as vld_eqp ON atp.id = vld_eqp.id	    ";
//
//    query += "OUTER LEFT JOIN (    ";
//    query += "SELECT id as idAtividade, COUNT(idPlano) as ct_plano_acao FROM planoAcaoAVR_resultado WHERE selecionado = 1 GROUP by id    ";
//    query += ")as vld_plano_acao ON atp.id = vld_plano_acao.idAtividade		    ";
//
//    //levantamento de risco
//
//    query += "OUTER LEFT JOIN (    ";
//
//
//    query += "SELECT idAtividade, CASE WHEN COUNT(lv_rsc_valido) = SUM(lv_rsc_valido) AND COUNT(lv_rsc_valido) > 0 THEN 1 ELSE 0 END lv_rsc_valido ";
//    query += "FROM( ";
//
//    query += "SELECT id as idAtividade, CASE WHEN (ct_pro_valido + rsc_valido) = 2 THEN 1 ELSE 0 END as lv_rsc_valido            ";
//    query += "FROM levantamentosRisco_resultado as lv_rsc_res     ";
//    query += "OUTER LEFT JOIN(    ";
//    query += "SELECT CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END ct_pro_valido, idLevantamento    ";
//    query += "FROM(    ";
//    query += "SELECT CASE WHEN (homens = 0 AND mulheres = 0) OR (homens IS NULL AND mulheres IS NULL) THEN 0 ELSE 1 END as valido, id as idLevantamento, origem    ";
//    query += "FROM categoriasProfissionais_resultado     ";
//    query += ") as resultado    ";
//    query += "WHERE origem = ?     ";
//    query += "GROUP BY idLevantamento    ";
//    query += ") as ct_pro ON lv_rsc_res.idLevantamento = ct_pro.idLevantamento    ";
//    query += "OUTER LEFT JOIN(    ";
//    query += "SELECT CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END rsc_valido, idLevantamento    ";
//    query += "FROM (    ";
//    query += "SELECT idLevantamento,    ";
//    query += "CASE WHEN nd IS NULL THEN 0    ";
//    query += "WHEN numeroMedidasExistentes IS NULL AND numeroMedidasRecomendadas IS NULL THEN 0      ";
//    query += "WHEN numeroMedidasExistentes = 0 OR numeroMedidasRecomendadas = 0 THEN 0     ";
//    query += "ELSE 1 END as valido    ";
//    query += "FROM riscos_resultado  as rsc_res    ";
//    query += "OUTER LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidasExistentes  FROM medidas_resultado WHERE origem = ? GROUP BY id) as med_existentes ON  rsc_res.idRegisto = med_existentes.id     ";
//    query += "OUTER LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidasRecomendadas  FROM medidas_resultado WHERE origem = ? GROUP BY id) as med_recomendadas ON  rsc_res.idRegisto = med_recomendadas.id    ";
//    query += ") as resultado    ";
//    query += "GROUP BY idLevantamento    ";
//    query += ") as rsc ON lv_rsc_res.idLevantamento = rsc.idLevantamento    ";
//
//    //query += "GROUP BY id    ";
//    query += ") ";
//    query += "GROUP BY idAtividade ";
//    query += ") as vld_avaliacao_riscos ON atp.id = vld_avaliacao_riscos.idAtividade	    ";
//
//    query += "OUTER LEFT JOIN (    ";
//    query += "SELECT idAtividadePendente,  CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END chk_valido    ";
//    query += "FROM (    ";
//    query += "SELECT idAtividadePendente, ar_chk_res.idRegisto as idRegisto, chk_scs.idSeccao as idSeccao, seccao, chk_scs.tipo as tipo, IFNULL(numeroRespostas,0) as numeroRespostas,  total,    ";
//    query += "CASE WHEN itens.tipo = 'q' AND IFNULL(numeroRespostas,0) = 0 THEN 0    ";
//    query += "WHEN itens.tipo = 'q' AND IFNULL(numeroRespostas,0) != total THEN 0    ";
//    query += "ELSE 1 END as valido    ";
//    query += "FROM areas_checklist_resultado as ar_chk_res    ";
//    query += "OUTER LEFT JOIN (SELECT idChecklist, idArea, idSeccao, seccao, tipo  FROM seccoes_checklist) as chk_scs ON ar_chk_res.idChecklist = chk_scs.idChecklist AND ar_chk_res.idArea = chk_scs.idArea    ";
//    query += "OUTER LEFT JOIN (    ";
//    query += "SELECT   COUNT(tipo)as total, idChecklist, idArea, idSeccao, tipo FROM itens_checklist    ";
//    query += "WHERE    tipo = 'q'     ";
//    query += "GROUP BY idChecklist, idArea , idSeccao    ";
//    query += ") as itens     ";
//    query += "ON ar_chk_res.idChecklist = itens.idChecklist AND ar_chk_res.idArea = itens.idArea AND chk_scs.idSeccao = itens.idSeccao    ";
//    query += "OUTER LEFT JOIN (    ";
//    query += "SELECT idRegistoArea, idSeccao, COUNT(idItem) as numeroRespostas FROM questionario_checklist_resultado    ";
//    query += "WHERE  tipo = 'q'    ";
//    query += "GROUP BY idRegistoArea, idSeccao    ";
//    query += ") as qst_res ON ar_chk_res.idRegisto = qst_res.idRegistoArea AND chk_scs.idSeccao = qst_res.idSeccao    ";
//    query += " )T1    ";
//    query += " GROUP BY idAtividadePendente    ";
//    query += ")as vld_checklist ON atp.id = vld_checklist.idAtividadePendente		    ";
//
//
//    query += "OUTER LEFT JOIN (	    ";
//    query += "SELECT id as idAtividade, idRelatorio, CASE WHEN  marca IS NULL OR nSerie IS NULL OR data IS NULL THEN 0 ELSE 1 END as rel_amb_valido FROM relatorioAmbiental_resultado 	    ";
//    query += ")as vld_rel_ambiental ON atp.id = vld_rel_ambiental.idAtividade	    ";
//
//    query += "OUTER LEFT JOIN (	    ";
//    query += "SELECT idRelatorio, CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END rel_ilm_avl_valido 	    ";
//    query += "FROM(  	    ";
//    query += "SELECT  idRelatorio ,  	    ";
//    query += "CASE WHEN CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) AND IFNULL(numeroMedidas, 0) = 0 THEN 0   	    ";
//    query += "ELSE 1 END as valido      ";
//    query += "FROM avaliacaoAmbiental_resultado as av_amb_res  	    ";
//    query += "OUTER LEFT JOIN (SELECT id, COUNT(idMEdida) as numeroMedidas FROM medidas_resultado WHERE origem = 2 GROUP BY id) as ct_medidas ON av_amb_res.idAvaliacao = ct_medidas.id  	    ";
//    query += ") as validacao 	    ";
//    query += "GROUP BY 	idRelatorio    ";
//    query += ")as vld_rel_ilm_avl ON vld_rel_ambiental.idRelatorio = vld_rel_ilm_avl.idRelatorio   			    ";
//
//    //temperatura e humidade
//
//    query += "OUTER LEFT JOIN ( 	    ";
//    query += "SELECT idRelatorio, CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END rel_tmp_hum_avl_valido	   	    ";
//    query += "FROM(	   	    ";
//    query += "SELECT  idRelatorio,	  	    ";
//    query += "CASE WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22) AND  IFNULL(numeroMedidas, 0)  = 0 THEN 0	 	    ";
//    query += "WHEN  (CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70) AND  IFNULL(numeroMedidas, 0)  = 0 THEN 0  	    ";
//    query += "WHEN IFNULL(numeroCategorias, 0)  = 0 THEN 0 ";
//    query += "ELSE 1 END as valido  	 	    ";
//    query += "FROM avaliacaoAmbiental_resultado as av_amb_res   	    ";
//    query += "OUTER LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas FROM medidas_resultado WHERE origem = 3 GROUP BY id) as ct_medidas ON av_amb_res.idAvaliacao = ct_medidas.id 	    ";
//
//    query += "OUTER LEFT JOIN ( ";
//    query += "SELECT id, COUNT(idCategoria) as numeroCategorias FROM categoriasProfissionais_resultado WHERE origem = 3 AND idCategoria NOT NULL AND idCategoria != '' GROUP BY id) ";
//    query += "as ct_categorias_profissionais ON av_amb_res.idAvaliacao = ct_categorias_profissionais.id	  ";
//
//    query += " ) as validacao 	    ";
//    query += "GROUP BY idRelatorio	  	    ";
//    query += ")as vld_rel_tmp_hum_avl ON vld_rel_ambiental.idRelatorio = vld_rel_tmp_hum_avl.idRelatorio   	    ";
//
//    //formacao
//
//    query += "OUTER LEFT JOIN ( 	    ";
//    query += "SELECT idAtividadePendente, CASE WHEN COUNT(id)  > 0 THEN 1 ELSE 0 END formandos_valido ";
//    query += "FROM formandos ";
//    query += "WHERE selecionado = 1 ";
//    query += "GROUP BY idAtividadePendente ";
//    query += ") as vld_formacao ON atp.id = vld_formacao.idAtividadePendente 	    ";
//
//
//    query += "OUTER LEFT JOIN ( 	    ";
//    query += "SELECT idAtividadePendente, CASE WHEN COUNT(idAtividadePendente)  > 0 THEN 1 ELSE 0 END acao_formacao_valido ";
//    query += "FROM acaoFormacao_resultado ";
//    query += "GROUP BY idAtividadePendente ";
//    query += ") as vld_acao_formacao ON atp.id = vld_acao_formacao.idAtividadePendente 	    ";
//
//
//    query += "WHERE atp.idTarefa = ? 		   ";
//
//    String argumentos [] = {
//            IdentificadoresIF.ORIGEM_LEVANTAMENTO_RISCO + "", IdentificadoresIF.ORIGEM_RISCO_MEDIDAS_EXISTENTES + "" , IdentificadoresIF.ORIGEM_RISCO_MEDIDAS_RECOMENDADAS + "",
//            idTarefa
//    };













    @Transaction
    @Query("SELECT *, " +

            "CASE WHEN idRelatorio = " + ID_RELATORIO_FORMACAO + " THEN  '" + FORMACAO + "' " +
            "WHEN idRelatorio = " + ID_RELATORIO_ILUMINACAO + " THEN  '" + ILUMINACAO + "' "+
            "WHEN idRelatorio = " + ID_RELATORIO_TEMPERATURA_HUMIDADE + " THEN  '" + TEMPERATURA_E_HUMIDADE + "' "+
            "WHEN idRelatorio = " + ID_RELATORIO_AVALIACAO_RISCO + " THEN  '" + AVALIACAO_RISCO + "' "+
            "WHEN idRelatorio = " + ID_RELATORIO_CERTIFICADO_TRATAMENTO + " THEN  '" + CERTIFICADO_TRATAMENTO + "' "+
            "WHEN ct_averiguacao > 0 AND tipo = " + ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO + " THEN  '" + AVERIGUACAO_AVALIACAO_RISCO + "' "+
            "WHEN ct_averiguacao > 0 AND tipo = " + ID_RELATORIO_AVERIGUACAO_AUDITORIA + " THEN  '" + AVERIGUACAO_AUDITORIA + "' "+
            "ELSE '' END as nomeRelatorio, " +

            "CASE WHEN idRelatorio > 0 THEN  1 " +
            "WHEN ct_averiguacao > 0 AND tipo = " + ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO + " THEN  1 "+
            "WHEN ct_averiguacao > 0 AND tipo = " + ID_RELATORIO_AVERIGUACAO_AUDITORIA + " THEN  1 "+
            "ELSE 0 END as possuiRelatorio, " +

            "CASE " +
            "WHEN idRelatorio = " + ID_RELATORIO_FORMACAO + " AND IFNULL(ct_formando, 0) > 0 THEN  1 " +
            "WHEN idRelatorio = " + ID_RELATORIO_CERTIFICADO_TRATAMENTO + " AND validade_certificado_tratamento THEN  1 " +
            "ELSE 0 END as relatorioCompleto, " +

            "0 as validade_processo_produtivo, 0 as validade_trabalhadores_vulneraveis, 0 as validade_equipamentos, 0 as validade_proposta_plano_acao, 0 as validade_checklist, 0 as validade_avaliacao_riscos, " +
            "0 as capa_Relatorio " +

            "FROM atividadesPendentes as atp " +

            //relatorios

            "LEFT JOIN( " +
            "SELECT idTarefa, IFNULL(COUNT(*), 0) as ct_averiguacao, tipo FROM relatorioAveriguacao GROUP BY idTarefa " +
            ") as rel_averiguacao " +


            //certificado tratamento

            "LEFT JOIN ( " +
            "SELECT idAtividade, sincronizacao as validade_certificado_tratamento FROM certificadoTratamentoResultado " +
            ") as cert_tratamento ON atp.id = cert_tratamento.idAtividade  " +

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
