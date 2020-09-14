package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Observable;

@Dao
abstract public class CategoriaProfissionalDao implements BaseDao<CategoriaProfissionalResultado> {


    @Transaction
    @Query("SELECT * "+
            "FROM categoriasProfissionaisResultado cat_prof_res " +
            "LEFT JOIN (SELECT id, descricao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS + "' AND ativo = 1) as tp_profissao " +
            "ON tp_profissao.id = cat_prof_res.id " +
            "WHERE idRegisto = :id AND origem = :origem ")
    abstract public Observable<List<CategoriaProfissionalResultado>> obterCategoriasProfissionais(int id, int origem);

}
