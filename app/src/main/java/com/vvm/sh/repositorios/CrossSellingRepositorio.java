package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.TipoDao;

public class CrossSellingRepositorio {


    private final TipoDao tipoDao;

    public CrossSellingRepositorio(@NonNull TipoDao tipoDao) {
        this.tipoDao = tipoDao;
    }

}
