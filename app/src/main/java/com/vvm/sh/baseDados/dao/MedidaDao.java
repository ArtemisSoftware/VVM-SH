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



    @Query("DELETE FROM medidasResultado WHERE id = :id AND origem = :origem")
    abstract public Single<Integer> remover(int id, int origem);


    @Query("DELETE FROM medidasResultado " +
            "WHERE id IN(SELECT id FROM riscosResultado WHERE idLevantamento = :idLevantamento) " +
            "AND (origem = " + Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS + " OR origem =" + Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS + ")")
    abstract public Single<Integer> removerMedidasRisco(int idLevantamento);
}
