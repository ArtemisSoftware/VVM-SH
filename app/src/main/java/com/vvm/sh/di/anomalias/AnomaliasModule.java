package com.vvm.sh.di.anomalias;

import com.vvm.sh.baseDados.dao.AnomaliaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AnomaliaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AnomaliasModule {


    @AnomaliasScope
    @Provides
    static AnomaliaDao provideAnomaliaResultadoDao(VvmshBaseDados vvmshBaseDados){

        AnomaliaDao dao = vvmshBaseDados.obterAnomaliaDao();
        return dao;
    }


    @AnomaliasScope
    @Provides
    AnomaliaRepositorio provideAnomaliaRepositorioo(int idApi, AnomaliaDao anomaliaDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        AnomaliaRepositorio repositorio = new AnomaliaRepositorio(idApi, anomaliaDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
