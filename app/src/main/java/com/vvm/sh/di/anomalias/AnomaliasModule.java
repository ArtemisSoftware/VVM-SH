package com.vvm.sh.di.anomalias;

import com.vvm.sh.baseDados.dao.AnomaliaResultadoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AnomaliaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AnomaliasModule {


    @AnomaliasScope
    @Provides
    static AnomaliaResultadoDao provideAnomaliaResultadoDao(VvmshBaseDados vvmshBaseDados){

        AnomaliaResultadoDao dao = vvmshBaseDados.obterAnomaliaResultadoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AnomaliasScope
    @Provides
    AnomaliaRepositorio provideAnomaliaRepositorioo(AnomaliaResultadoDao anomaliaResultadoDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        AnomaliaRepositorio repositorio = new AnomaliaRepositorio(anomaliaResultadoDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
