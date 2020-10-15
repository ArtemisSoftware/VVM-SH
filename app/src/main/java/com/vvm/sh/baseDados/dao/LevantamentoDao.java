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





    @Query("SELECT *, 'lolo' as modelo, count_categorias as categoriasProfissionais, count_riscos as riscos, " +
            "CASE WHEN (tarefa != '' AND perigo != '' AND valido_categorias_prof = 1 AND valido_riscos = 1) THEN 1  ELSE 0 END as valido, " +
            "0 as validadePerigoTarefa  " +
            "FROM levantamentosRiscoResultado as lv_riscos_res " +


            //riscos

            "LEFT JOIN(" +
            "SELECT idLevantamento, " +
            "CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido_riscos   " +
            "FROM (			  " +
            "SELECT idLevantamento,			  " +
            "CASE WHEN nd IS NULL THEN 0  			  " +
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
            "GROUP BY idLevantamento" +
            ") as vald_riscos ON lv_riscos_res.id = vald_riscos.idLevantamento	" +

            //categorias

            "LEFT JOIN( " +
            "SELECT idRegisto as idLevantamento, CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido_categorias_prof " +
            "FROM( " +
            "SELECT idRegisto, CASE WHEN (homens = 0 AND mulheres = 0) OR (homens IS NULL AND mulheres IS NULL) THEN 0 ELSE 1 END as valido " +
            "FROM categoriasProfissionaisResultado " +
            "WHERE origem = " + Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS + " " +
            ")" +
            "GROUP BY idRegisto   " +
            ") as vald_categorias_prof ON lv_riscos_res.id = vald_categorias_prof.idLevantamento " +



            "LEFT JOIN (SELECT idRegisto, COUNT(*) as count_categorias FROM categoriasProfissionaisResultado WHERE origem = " + Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS + " GROUP BY idRegisto) " +
            "as ct_categorias ON lv_riscos_res.id = ct_categorias.idRegisto " +

            "LEFT JOIN (SELECT idLevantamento, COUNT(*) as count_riscos FROM riscosResultado GROUP BY idLevantamento) " +
            "as ct_riscos ON lv_riscos_res.id = ct_riscos.idLevantamento " +


            "WHERE lv_riscos_res.idAtividade = :idAtividade")
    abstract public Observable<List<Levantamento>> obterLevantamentos(int idAtividade);



    @Query("SELECT id, " +
            "CASE WHEN tarefa IS NULL OR perigo IS NULL THEN 0  ELSE 1 END as validadePerigoTarefa, " +
            "vald_categorias_prof.valido as validadeCategoriasProfissionais, " +
            "vald_riscos.valido as validadeRiscos," +
            "CASE WHEN vald_categorias_prof.valido = 1 AND vald_riscos.valido = 1 THEN 1 ELSE 0 END as valido, " +
            "count_categorias as numeroCategoriasProfissionais, count_riscos as numeroRiscos " +
            "FROM levantamentosRiscoResultado as lv_risco_res " +

            //riscos

            "LEFT JOIN(" +
            "SELECT idLevantamento, " +
            "CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido   " +
            "FROM (			  " +
            "SELECT idLevantamento,			  " +
            "CASE WHEN nd IS NULL THEN 0  			  " +
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
            "GROUP BY idLevantamento" +
            ") as vald_riscos ON lv_risco_res.id = vald_riscos.idLevantamento	" +

            //categorias

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

            "LEFT JOIN (SELECT idLevantamento, COUNT(*) as count_riscos FROM riscosResultado GROUP BY idLevantamento) " +
            "as ct_riscos ON lv_risco_res.id = ct_riscos.idLevantamento " +

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
