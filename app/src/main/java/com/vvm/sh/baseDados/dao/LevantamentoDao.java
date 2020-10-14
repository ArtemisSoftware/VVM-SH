package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.RelatorioLevantamento;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class LevantamentoDao implements BaseDao<LevantamentoRiscoResultado> {





    @Query("SELECT *, 'lolo' as modelo, count_categorias as categoriasProfissionais, 0 as riscos, " +
            "CASE WHEN tarefa IS NULL OR perigo IS NULL THEN 0  ELSE 1 END as validadePerigoTarefa, " +
            //"CASE WHEN validadePerigoTarefa = 1 THEN 1 ELSE 0 END as valido " +
            "0 as valido " +
            "FROM levantamentosRiscoResultado as lv_riscos_res " +


            "LEFT JOIN (SELECT idRegisto, COUNT(*) as count_categorias FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS + " GROUP BY idRegisto) " +
            "as ct_categorias ON lv_riscos_res.id = ct_categorias.idRegisto " +


//            "LEFT JOIN (" +
//            "SELECT id, " +
//            "CASE WHEN tarefa IS NULL OR perigo IS NULL THEN 0  ELSE 1 END as validadePerigoTarefa " +
//            "FROM levantamentosRiscoResultado " +
//            ") as validacao_levantamento " +
//            "ON lv_riscos_res.id = validacao_levantamento.id " +
            "WHERE lv_riscos_res.idAtividade = :idAtividade")
    abstract public Observable<List<Levantamento>> obterLevantamentos(int idAtividade);



    @Query("SELECT id, 0 as numeroRiscos, " +
            "CASE WHEN tarefa IS NULL OR perigo IS NULL THEN 0  ELSE 1 END as validadePerigoTarefa, " +
            "vald_categorias_prof.valido as validadeCategoriasProfissionais, count_categorias as numeroCategoriasProfissionais " +
            "FROM levantamentosRiscoResultado as lv_risco_res " +

            "" +
            "LEFT JOIN( " +
            "SELECT idRegisto, CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido " +
            "FROM( " +
            "SELECT idRegisto, CASE WHEN (homens = 0 AND mulheres = 0) OR (homens IS NULL AND mulheres IS NULL) THEN 0 ELSE 1 END as valido " +
            "FROM categoriasProfissionaisResultado " +
            "WHERE origem = " + Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS + " AND idRegisto = :id " +
            ")" +
            "GROUP BY idRegisto   " +
            ") as vald_categorias_prof ON lv_risco_res.id = vald_categorias_prof.idRegisto " +

            "LEFT JOIN (SELECT idRegisto, COUNT(*) as count_categorias FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS + " GROUP BY idRegisto) " +
            "as ct_categorias ON lv_risco_res.id = ct_categorias.idRegisto " +
            "" +
            "WHERE lv_risco_res.id = :id")
    abstract public Observable<RelatorioLevantamento> obterRelatorio(int id);


    @Query("SELECT * FROM levantamentosRiscoResultado  WHERE id = :id")
    abstract public Maybe<LevantamentoRiscoResultado> obterLevantamento(int id);



//------validacao


//    String query = " SELECT  ";
//
//    query += "CASE WHEN tarefa IS NULL OR perigo IS NULL THEN 0 ";
//    query += " ELSE 1 END as valido  ";
//
//    query += "FROM levantamentosRisco_resultado  as lvr_res ";
//
//    query += "WHERE lvr_res.idLevantamento = ?   ";



//    String query = "SELECT CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido   ";
//    query += "FROM(          ";
//    query += "SELECT CASE WHEN (homens = 0 AND mulheres = 0) OR (homens IS NULL AND mulheres IS NULL) THEN 0 ELSE 1 END as valido, id, origem          ";
//    query += "FROM categoriasProfissionais_resultado            ";
//    query += ") as resultado          ";
//    query += "WHERE origem = ? AND id = ?		          ";
//
//    String argumentos [] = {
//            IdentificadoresIF.ORIGEM_LEVANTAMENTO_RISCO + "", idLevantamento
//    };


//    String query = "SELECT CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido   ";
//    query += "FROM (			  ";
//    query += "SELECT idLevantamento,			  ";
//    query += "CASE WHEN nd IS NULL THEN 0  			  ";
//    query += "WHEN numeroMedidasExistentes IS NULL AND numeroMedidasRecomendadas IS NULL THEN 0  			  ";
//    query += "WHEN numeroMedidasExistentes = 0 OR numeroMedidasRecomendadas = 0 THEN 0 			  ";
//    query += "ELSE 1 END as valido			  ";
//    query += "FROM riscos_resultado  as rsc_res			  ";
//    query += "OUTER LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidasExistentes  FROM medidas_resultado WHERE origem = ? GROUP BY id) as med_existentes ON  rsc_res.idRegisto = med_existentes.id			  ";
//    query += "OUTER LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidasRecomendadas  FROM medidas_resultado WHERE origem = ? GROUP BY id) as med_recomendadas ON  rsc_res.idRegisto = med_recomendadas.id			  ";
//    query += ") as resultado			  ";
//    query += "WHERE idLevantamento = ?		  ";
//
//    String argumentos [] = {
//            IdentificadoresIF.ORIGEM_RISCO_MEDIDAS_EXISTENTES + "", IdentificadoresIF.ORIGEM_RISCO_MEDIDAS_RECOMENDADAS + "", idRelatorio
//    };



    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND ativo = 1 AND id NOT IN (SELECT id FROM levantamentosRiscoResultado WHERE idAtividade = :idAtividade)")
    abstract public Single<List<Tipo>> obterModelos(int idAtividade, String tipo, int api);

}
