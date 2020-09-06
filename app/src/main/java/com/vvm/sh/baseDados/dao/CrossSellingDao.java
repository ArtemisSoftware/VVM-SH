package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.ui.crossSelling.modelos.CrossSelling;
import com.vvm.sh.util.constantes.TiposConstantes;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
abstract public class CrossSellingDao implements BaseDao<CrossSellingResultado> {



    @Transaction
    @Query("SELECT tipos.*, idTarefa, idAreaRecomendacao, idDimensaoSinaletica, dimensaoSinaletica, idTipoSinaletica, tipoSinaletica," +
            "CASE WHEN  dimensaoSinaletica IS NULL AND tipoSinaletica IS NULL THEN 0 ELSE 1 END AS possuiSinaletica, " +
            "CASE WHEN  idAreaRecomendacao IS NULL THEN 0 ELSE 1 END AS selecionado "+
            "FROM tipos  " +
            "LEFT JOIN (SELECT idTarefa, id, idAreaRecomendacao, idDimensao as idDimensaoSinaletica, idTipo as idTipoSinaletica FROM crossSellingResultado WHERE idTarefa = :idTarefa) as crs ON tipos.id = crs.id " +
            "LEFT JOIN (SELECT id, descricao as dimensaoSinaletica FROM tipos WHERE tipo = '" + TiposConstantes.MetodosTipos.CROSS_SELLING_DIMENSAO + "') as tp_dimensao ON crs.idDimensaoSinaletica = tp_dimensao.id " +
            "LEFT JOIN (SELECT id as ida, descricao as tipoSinaletica FROM tipos WHERE tipo = '" + TiposConstantes.MetodosTipos.CROSS_SELLING_TIPO + "') as tp_tipo ON crs.idTipoSinaletica = tp_tipo.ida " +
            "WHERE tipos.tipo = '"+ TiposConstantes.MetodosTipos.CROSS_SELLING_PRODUTOS + "' AND idPai = :idProduto AND ativo = 1 ")
    abstract public Flowable<List<CrossSelling>> obterCrossSelling(int idTarefa, String idProduto);




    @Query("DELETE FROM crossSellingResultado WHERE idTarefa = :idTarefa AND id = :id")
    abstract public Single<Integer> remover(int idTarefa, int id);
}
