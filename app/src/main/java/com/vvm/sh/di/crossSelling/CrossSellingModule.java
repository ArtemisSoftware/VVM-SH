package com.vvm.sh.di.crossSelling;

import android.app.Application;

import com.vvm.sh.baseDados.dao.CrossSellingDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.di.anomalias.AnomaliasScope;
import com.vvm.sh.repositorios.CrossSellingRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class CrossSellingModule {


    @CrossSellingScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }

    @CrossSellingScope
    @Provides
    static CrossSellingDao provideCrossSellingDao(VvmshBaseDados vvmshBaseDados){

        CrossSellingDao dao = vvmshBaseDados.obterCrossSellingDao();
        return dao;
    }


    @CrossSellingScope
    @Provides
    CrossSellingRepositorio provideCrossSellingRepositorio(@Named("idApi_") int idApi, CrossSellingDao crossSellingDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        CrossSellingRepositorio repositorio = new CrossSellingRepositorio(idApi, crossSellingDao, tipoDao, resultadoDao);
        return repositorio;
    }

}
