package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class AvaliacaoAmbientalDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract public Single<Long> inserir(RelatorioAmbientalResultado registo);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract public Single<Integer> atualizar(RelatorioAmbientalResultado registo);

    @Query("SELECT * FROM relatorioAmbientalResultado WHERE idAtividade = :idAtividade AND tipo = :tipo")
    abstract public Maybe<RelatorioAmbientalResultado> obterGeral(int idAtividade, int tipo);





    @Query("SELECT * FROM avaliacoesAmbientaisResultado WHERE idRelatorio = :idRelatorio")
    abstract public Observable<List<AvaliacaoAmbientalResultado>> obterAvaliacoes(int idRelatorio);


    @Query("SELECT * FROM avaliacoesAmbientaisResultado WHERE id = :id")
    abstract public Maybe<AvaliacaoAmbientalResultado> obterAvaliacao(int id);




    //--------------
    //Validacao
    //--------------

    @Query("SELECT id FROM relatorioAmbientalResultado WHERE idAtividade = :idAtividade AND tipo = :tipo")
    abstract public Maybe<Integer> obterIdRelatorio(int idAtividade, int tipo);

    @Query("SELECT " +
            "CASE WHEN marca IS NULL OR numeroSerie IS NULL OR data IS NULL THEN 0 ELSE 1 " +
            "END as valido " +
            "FROM relatorioAmbientalResultado " +
            "WHERE  idAtividade = :idAtividade AND tipo = :tipo")
    abstract public Maybe<Boolean> obterValidadeGeral(int idAtividade, int tipo);




    @Query("SELECT CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido " +
            "FROM(   " +
            "SELECT idRelatorio,   " +
            "CASE WHEN CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) AND IFNULL(numeroMedidas, 0) = 0 THEN 0  " +
            "ELSE 1 END as valido  " +
            "FROM avaliacoesAmbientaisResultado as av_amb_res  " +
            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas FROM medidasResultado WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO + " GROUP BY id) as ct_medidas " +
            "ON av_amb_res.id = ct_medidas.id  " +
            ") as validacao " +
            "WHERE idRelatorio = (SELECT id FROM relatorioAmbientalResultado WHERE idAtividade = :idAtividade AND tipo = :tipo) ")
    abstract public Maybe<Boolean> obterValidadeAvaliacoesIluminacao(int idAtividade, int tipo);



    @Query("SELECT medida " +
            "FROM ( " +
            "SELECT  " +
            "CASE WHEN SUM(VALIDO) > 0 AND COUNT(VALIDO) > 0 THEN 2 ELSE 1 END as idMedidaRecomendada, " +
            "idRelatorio    " +
            "FROM (" +
            "SELECT " +
            "CASE WHEN  CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) THEN 1 ELSE 0 END as VALIDO, " +
            "idRelatorio     " +
            "FROM avaliacoesAmbientaisResultado) as medidas GROUP BY idRelatorio) as recm    " +
            "LEFT JOIN (SELECT id, descricao as medida FROM tipos WHERE tipo = 'ConclusaoMedidasRecomendadas' AND codigo = 'iluminacao') as ccl " +
            "ON recm.idMedidaRecomendada = ccl.id  " +
            "WHERE idRelatorio = (SELECT id FROM relatorioAmbientalResultado WHERE idAtividade = :idAtividade AND tipo = :tipo) LIMIT 1 ")
    abstract public Observable<String> obterMedidaRecomendadaIluminacao(int idAtividade, int tipo);




/*
    @Query("SELECT CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido  " +
            "FROM(  " +
            "SELECT  idRelatorio, " +
            "CASE WHEN CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) AND IFNULL(numeroMedidas, 0) = 0 THEN 0  " +
            "ELSE 1 END as valido   " +
            "FROM avaliacoesAmbientaisResultado as av_amb_res " +
            "LEFT JOIN (" +
            "SELECT id, COUNT(idMEdida) as numeroMedidas FROM medidas_resultado WHERE origem = ? GROUP BY id) as ct_medidas " +
            "ON av_amb_res.idAvaliacao = ct_medidas.id  " +
            ") as validacao " +
            "WHERE idRelatorio IN (SELECT * FROM relatorioAmbientalResultado WHERE idAtividade = :idAtividade AND tipo = :tipo) ")
    abstract public Maybe<Boolean> obterValidadeIluminacao(int idAtividade, int tipo);
*/


/*
        query = "SELECT medida   ";
        query += "FROM (   ";
        query += "SELECT CASE WHEN SUM(VALIDO) > 0 AND COUNT(VALIDO) > 0 THEN 3 ELSE 4 END as  idMedidaRecomendada, idRelatorio   ";
        query += "FROM (   ";
        query += "SELECT CASE WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22) OR CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70 THEN 0    ";
        query += "ELSE 1 END as VALIDO, idRelatorio   ";
        query += "FROM avaliacaoAmbiental_resultado   ";
        query += ") as medidas    ";
        query += "GROUP BY idRelatorio   ";
        query += ") as recm   ";
        query += "OUTER LEFT JOIN (SELECT id, descricao as medida FROM tipos WHERE tipo = 'ConclusaoMedidasRecomendadas' AND codigo = 'termico') as ccl ON recm.idMedidaRecomendada = ccl.id   ";
        query += "WHERE idRelatorio = ?	   ";
        query += "   ";
    abstract public Observable<String> obterMedidaRecomendadaTermico(int idRelatorio);
    */


}
