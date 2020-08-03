package com.vvm.sh.di.anomalias;

import com.vvm.sh.baseDados.AnomaliaDao;
import com.vvm.sh.baseDados.AnomaliaResultadoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AnomaliaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AnomaliasModule {

    @AnomaliasScope
    @Provides
    static AnomaliaDao provideAnomaliaDao(VvmshBaseDados vvmshBaseDados){

        AnomaliaDao dao = vvmshBaseDados.obterAnomaliaDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AnomaliasScope
    @Provides
    static AnomaliaResultadoDao provideAnomaliaResultadoDao(VvmshBaseDados vvmshBaseDados){

        AnomaliaResultadoDao dao = vvmshBaseDados.obterAnomaliaResultadoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AnomaliasScope
    @Provides
    AnomaliaRepositorio provideAnomaliaRepositorioo(AnomaliaDao anomaliaDao, AnomaliaResultadoDao anomaliaResultadoDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        AnomaliaRepositorio repositorio = new AnomaliaRepositorio(anomaliaDao, anomaliaResultadoDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
