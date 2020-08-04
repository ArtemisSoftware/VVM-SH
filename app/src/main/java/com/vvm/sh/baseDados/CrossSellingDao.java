package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.ui.crossSelling.modelos.CrossSelling;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.ui.crossSelling.modelos.CrossSelling_;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
abstract public class CrossSellingDao implements BaseDao<CrossSellingResultado>{



    @Transaction
    @Query("SELECT tipos.* " + //tp.* , crs.*, dimensao, tipoSinaletica
            "FROM tipos  " +
            "LEFT JOIN (SELECT idTarefa, id, idAreaRecomendacao, idDimensao, idTipo FROM crossSellingResultado) as crs ON tipos.id = crs.id " +
//           "LEFT JOIN (SELECT id as idDimensaoSinaletica, descricao as dimensao FROM tipos WHERE tipo = :tipoDimensao) as tp_dimensao ON crs.idDimensao = tp_dimensao.idDimensaoSinaletica " +
//            "LEFT JOIN (SELECT id, descricao as tipoSinaletica FROM tipos WHERE tipo = :tipoTipo) as tp_tipo ON crs.idTipo = tp_tipo.id " +
            "WHERE tipos.tipo = :tipoProduto AND idPai = :idProduto AND ativo = 1")
    abstract public Flowable<List<CrossSelling_>> obterCrossSelling_(String tipoProduto/*, String tipoDimensao, String tipoTipo*/, String idProduto);


    @Query("SELECT idTarefa, tp.id as id, descricao, idAreaRecomendacao, idDimensao, dimensao, idTipo, tp_tipo.tipo as tipo " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT idTarefa, id, idAreaRecomendacao, idDimensao, idTipo FROM crossSellingResultado) as crs ON tp.id = crs.id " +
            "LEFT JOIN (SELECT id, descricao as dimensao FROM tipos WHERE tipo = :tipoDimensao) as tp_dimensao ON crs.idDimensao = tp_dimensao.id " +
            "LEFT JOIN (SELECT id, descricao as tipo FROM tipos WHERE tipo = :tipoTipo) as tp_tipo ON crs.idTipo = tp_tipo.id " +
            "WHERE tp.tipo = :tipoProduto AND idPai = :idProduto AND ativo = 1")
    abstract public Flowable<List<CrossSelling>> obterCrossSelling(String tipoProduto, String tipoDimensao, String tipoTipo, String idProduto);


    @Query("DELETE FROM crossSellingResultado WHERE idTarefa = :idTarefa AND id = :id")
    abstract public Single<Integer> remover(int idTarefa, int id);
}
