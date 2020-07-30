package com.vvm.sh.di.anomalias;

import com.vvm.sh.baseDados.AnomaliaDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AnomaliaRepositorio;
import com.vvm.sh.ui.agenda.modelos.Resultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

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
    AnomaliaRepositorio provideAnomaliaRepositorioo(AnomaliaDao anomaliaDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        AnomaliaRepositorio repositorio = new AnomaliaRepositorio(anomaliaDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
