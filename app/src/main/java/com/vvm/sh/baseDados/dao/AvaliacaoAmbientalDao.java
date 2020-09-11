package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;

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

}
