package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.ui.crossSelling.modelos.CrossSelling;
import com.vvm.sh.ui.crossSelling.modelos.CrossSellingResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.List;

import io.reactivex.Flowable;

@Dao
abstract public class CrossSellingDao implements BaseDao<CrossSellingResultado>{


    @Query("SELECT idTarefa, tp.id as id, descricao, idAreaRecomendacao, idDimensao, idTipo " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT idTarefa, id, idAreaRecomendacao, idDimensao, idTipo FROM crossSellingResultado) as crs ON tp.id = crs.id " +
            "WHERE tipo = :tipo AND idPai = :idProduto AND ativo = 1")
    abstract public Flowable<List<CrossSelling>> obterCrossSelling(String tipo, String idProduto);
}
