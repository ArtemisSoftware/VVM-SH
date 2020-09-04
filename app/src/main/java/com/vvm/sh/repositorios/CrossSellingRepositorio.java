package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.CrossSellingDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.ui.crossSelling.modelos.CrossSelling;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.baseDados.entidades.Tipo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class CrossSellingRepositorio {


    private final CrossSellingDao crossSellingDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public CrossSellingRepositorio(@NonNull CrossSellingDao crossSellingDao, @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {
        this.crossSellingDao = crossSellingDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }


    /**
     * Metodo que permite obter o cross selling
     * @param idTarefa o identificador da tarefa
     * @param idProduto o identificador do produto
     * @return uma lista de registos
     */
    public Flowable<List<CrossSelling>> obterCrossSelling(int idTarefa, String idProduto){
        return crossSellingDao.obterCrossSelling(idTarefa, idProduto);
    }


    public Single<Long> inserir(CrossSellingResultado registo){
        return crossSellingDao.inserir(registo);
    }

    public Single<Integer> remover(CrossSelling registo){
        return crossSellingDao.remover(registo.idTarefa, registo.tipo.id);
    }

    public Single<List<Tipo>> obterProdutos(){
        return tipoDao.obterCrossSellingProdutos(TiposConstantes.MetodosTipos.CROSS_SELLING_PRODUTOS);
    }

    public Flowable<List<Tipo>> obterDimensoes(){
        return tipoDao.obterTipos(TiposConstantes.MetodosTipos.CROSS_SELLING_DIMENSAO);
    }

    public Flowable<List<Tipo>> obterTipos(){
        return tipoDao.obterTipos(TiposConstantes.MetodosTipos.CROSS_SELLING_TIPO);
    }

}
