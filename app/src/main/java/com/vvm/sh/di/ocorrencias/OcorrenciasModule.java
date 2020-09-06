package com.vvm.sh.di.ocorrencias;

import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.OcorrenciaResultadoDao;
import com.vvm.sh.repositorios.OcorrenciaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class OcorrenciasModule {




    @OcorrenciasScope
    @Provides
    static OcorrenciaResultadoDao provideOcorrenciaResultado(VvmshBaseDados vvmshBaseDados){

        OcorrenciaResultadoDao dao = vvmshBaseDados.obterOcorrenciaResultadoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @OcorrenciasScope
    @Provides
    OcorrenciaRepositorio provideOcorrenciaRepositorio(OcorrenciaResultadoDao ocorrenciaResultadoDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        OcorrenciaRepositorio repositorio = new OcorrenciaRepositorio(ocorrenciaResultadoDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
