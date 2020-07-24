package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.List;

import io.reactivex.Flowable;

public class CrossSellingRepositorio {


    private final TipoDao tipoDao;

    public CrossSellingRepositorio(@NonNull TipoDao tipoDao) {
        this.tipoDao = tipoDao;
    }



    public Flowable<List<Tipo>> obterProdutos(){
        return tipoDao.obterCrossSellingProdutos(TiposConstantes.CROSS_SELLING_PRODUTOS);
    }


    public Flowable<List<Tipo>> obterCrossSelling(String idProduto){
        return tipoDao.obterCrossSelling(TiposConstantes.CROSS_SELLING_PRODUTOS, idProduto);
    }


    public Flowable<List<Tipo>> obterDimensoes(){
        return tipoDao.obterTipos(TiposConstantes.CROSS_SELLING_DIMENSAO);
    }

    public Flowable<List<Tipo>> obterTipos(){
        return tipoDao.obterTipos(TiposConstantes.CROSS_SELLING_TIPO);
    }

}
