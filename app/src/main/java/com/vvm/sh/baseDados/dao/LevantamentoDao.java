package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
abstract public class LevantamentoDao implements BaseDao<LevantamentoRiscoResultado> {


    @Query("SELECT * FROM levantamentosRiscoResultado  WHERE idAtividade = :idAtividade")
    abstract public Observable<List<LevantamentoRiscoResultado>> obterLevantamentos(int idAtividade);


    @Query("SELECT * FROM levantamentosRiscoResultado  WHERE id = :id")
    abstract public Maybe<LevantamentoRiscoResultado> obterLevantamento(int id);


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


}
