package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.TiposConstantes;
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
}
