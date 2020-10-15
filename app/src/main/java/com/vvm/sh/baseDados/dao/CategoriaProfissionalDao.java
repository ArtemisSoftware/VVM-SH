package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.CategoriaProfissional;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class CategoriaProfissionalDao implements BaseDao<CategoriaProfissionalResultado> {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract public Single<List<Long>> inserir(List<CategoriaProfissionalResultado> entity);


    @Query("DELETE FROM categoriasProfissionaisResultado WHERE idRegisto = :idRegisto AND origem = :origem")
    abstract public Single<Integer> remover(int idRegisto, int origem);



    @Transaction
    @Query("SELECT *, descricao, " +
            "CASE WHEN (homens = 0 AND mulheres = 0) OR (homens IS NULL AND mulheres IS NULL) THEN 0 ELSE 1 END as valido  "+
            "FROM categoriasProfissionaisResultado as cat_prof_res " +
            "LEFT JOIN (SELECT id, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS + "' AND api = :idApi) as tp_categoria " +
            "ON cat_prof_res.idCategoriaProfissional = tp_categoria.id " +
            "WHERE idRegisto = :id AND origem = :origem ")
    abstract public Observable<List<CategoriaProfissional>> obterCategoriasProfissionais(int id, int idApi, int origem);



    @Transaction
    @Query("SELECT tp.* "+
            "FROM tipos as tp  " + //as cat_prof_res
            "LEFT JOIN (SELECT idRegisto, idCategoriaProfissional, origem FROM categoriasProfissionaisResultado) as cat_prof " +
            "ON tp.id = cat_prof.idCategoriaProfissional " +
            "WHERE tipo = '" + TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS + "' AND ativo = 1 AND idRegisto = :id AND origem = :origem  ")
    abstract public Maybe<List<Tipo>> obterTipoCategoriasProfissionais(int id, int origem);


    @Query("SELECT * FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS + "' AND api = :api AND id IN (:registos) AND ativo = 1")
    abstract public Single<List<Tipo>> obterTiposCategorias(List<Integer> registos, int api);



}
