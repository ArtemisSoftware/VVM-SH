package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class MedidaDao implements BaseDao<MedidaResultado> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract public Single<List<Long>> inserir(List<MedidaResultado> entity);


    @Query("SELECT * FROM medidasResultado WHERE id = :id AND origem = :origem")
    abstract public Observable<List<MedidaResultado>> obterMedidas(int id, int origem);



    @Transaction
    @Query("SELECT tp.* "+
            "FROM tipos as tp  " +
            "LEFT JOIN (SELECT id, idMedida, origem FROM medidasResultado) as med " +
            "ON tp.id = med.idMedida " +
            "WHERE tipo = :tipo AND ativo = 1 AND med.id = :id AND origem = :origem  ")
    abstract public Maybe<List<Tipo>> obterTipoMedidas(int id, String tipo, int origem);







//    query = "INSERT INTO medidas_resultado (id, origem, idMedida)  ";
//    query += "SELECT rsc_res.idRegisto, " + IdentificadoresIF.ORIGEM_RISCO_MEDIDAS_EXISTENTES + " as origem, rsc_modelo.idMedida   ";
//
//    query += "FROM riscosMedidas_modelo as rsc_modelo   ";
//
//    /**/query += "OUTER LEFT JOIN (SELECT id, activo FROM tipos WHERE tipo = 'MedidasPrevencaoAdoptadas' AND activo = 1) as tp_med_recom ON tp_med_recom.id = rsc_modelo.idMedida   ";
//    query += "OUTER LEFT JOIN (SELECT idTipoRisco, idRegisto, idLevantamento FROM riscos_resultado WHERE idTipoRisco IS NOT null) as rsc_res ON cast(rsc_modelo.id as text)= rsc_res.idTipoRisco  ";
//    query += "WHERE   origem = ? AND  ";
//    /**///query += " activo = 1 AND  ";
//    query += "rsc_res.idLevantamento IN (SELECT idLevantamento FROM levantamentosRisco_resultado WHERE id = ? AND idModelo= ?)  ";
//    query += " AND activo = 1 ";
//
//    argumentos = new String []{
//        IdentificadoresIF.ORIGEM_MEDIDAS_EXISTENTES + "", idAtividade, idModelo
//    };



    @Query("INSERT INTO medidasResultado (id, origem, idMedida) " +
            "SELECT rsc_res.id, :origemMedidas as origem, rsc_modelo.idMedida " +
            "FROM tiposTemplatesAVRMedidasRisco as rsc_modelo " +

            "LEFT JOIN (SELECT id FROM tipos WHERE tipo = :tipo AND ativo = 1 AND api = :api) as tp_med_recom " +
            "ON tp_med_recom.id = rsc_modelo.idMedida " +

            "LEFT JOIN (SELECT idTipoRisco, id, idLevantamento FROM riscosResultado WHERE idTipoRisco > 0) as rsc_res " +
            "ON rsc_modelo.id = rsc_res.idTipoRisco " +

            "WHERE origem = :origemModelo " +
            "AND rsc_res.idLevantamento IN (SELECT idLevantamento FROM levantamentosRiscoResultado WHERE idAtividade = :idAtividade AND idModelo= :idModelo) " +
            "")
    abstract public Completable inserirMedidasRisco(int idAtividade, int idModelo, String tipo, int origemMedidas, int origemModelo, int api);




    //remover


    @Query("DELETE FROM medidasResultado WHERE id = :id AND origem = :origem")
    abstract public Single<Integer> remover(int id, int origem);


    @Query("DELETE FROM medidasResultado " +
            "WHERE id IN(SELECT id FROM riscosResultado WHERE idLevantamento = :idLevantamento) " +
            "AND (origem = " + Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS + " OR origem =" + Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS + ")")
    abstract public Single<Integer> removerMedidasRisco_Levantamento(int idLevantamento);


    @Query("DELETE FROM medidasResultado " +
            "WHERE id =:idRisco " +
            "AND (origem = " + Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS + " OR origem =" + Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS + ")")
    abstract public Single<Integer> removerMedidasRisco(int idRisco);
}
