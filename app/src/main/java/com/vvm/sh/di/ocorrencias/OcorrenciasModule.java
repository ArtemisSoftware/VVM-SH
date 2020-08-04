package com.vvm.sh.di.ocorrencias;

import com.vvm.sh.baseDados.OcorrenciaDao;
import com.vvm.sh.baseDados.OcorrenciaResultadoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.OcorrenciaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class OcorrenciasModule {

    @OcorrenciasScope
    @Provides
    static OcorrenciaDao provideOcorrenciaDao(VvmshBaseDados vvmshBaseDados){

        OcorrenciaDao dao = vvmshBaseDados.obterOcorrenciaDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }



    @OcorrenciasScope
    @Provides
    static OcorrenciaResultadoDao provideOcorrenciaResultado(VvmshBaseDados vvmshBaseDados){

        OcorrenciaResultadoDao dao = vvmshBaseDados.obterOcorrenciaResultadoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @OcorrenciasScope
    @Provides
    OcorrenciaRepositorio provideOcorrenciaRepositorio(OcorrenciaDao ocorrenciaDao,
                                                       OcorrenciaResultadoDao ocorrenciaResultadoDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        OcorrenciaRepositorio repositorio = new OcorrenciaRepositorio(ocorrenciaDao, ocorrenciaResultadoDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
