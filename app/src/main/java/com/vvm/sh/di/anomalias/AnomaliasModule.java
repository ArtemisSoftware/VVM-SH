package com.vvm.sh.di.anomalias;

import android.app.Application;

import com.vvm.sh.baseDados.dao.AnomaliaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.di.atividadesPendentes.levantamentos.LevantamentosScope;
import com.vvm.sh.repositorios.AnomaliaRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AnomaliasModule {

    @AnomaliasScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }

    @AnomaliasScope
    @Provides
    static AnomaliaDao provideAnomaliaResultadoDao(VvmshBaseDados vvmshBaseDados){

        AnomaliaDao dao = vvmshBaseDados.obterAnomaliaDao();
        return dao;
    }


    @AnomaliasScope
    @Provides
    AnomaliaRepositorio provideAnomaliaRepositorioo(@Named("idApi_") int idApi, AnomaliaDao anomaliaDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        AnomaliaRepositorio repositorio = new AnomaliaRepositorio(idApi, anomaliaDao, tipoDao, resultadoDao);
        return repositorio;
    }

}
