package com.vvm.sh.di.crossSelling;

import com.vvm.sh.baseDados.dao.CrossSellingDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.CrossSellingRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class CrossSellingModule {


    @CrossSellingScope
    @Provides
    static CrossSellingDao provideCrossSellingDao(VvmshBaseDados vvmshBaseDados){

        CrossSellingDao dao = vvmshBaseDados.obterCrossSellingDao();
        return dao;
    }


    @CrossSellingScope
    @Provides
    CrossSellingRepositorio provideCrossSellingRepositorio(int idApi, CrossSellingDao crossSellingDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        CrossSellingRepositorio repositorio = new CrossSellingRepositorio(idApi, crossSellingDao, tipoDao, resultadoDao);
        return repositorio;
    }

}
