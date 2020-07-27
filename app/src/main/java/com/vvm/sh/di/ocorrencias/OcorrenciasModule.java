package com.vvm.sh.di.ocorrencias;

import com.vvm.sh.baseDados.AtividadePendenteDao;
import com.vvm.sh.baseDados.OcorrenciaDao;
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
    OcorrenciaRepositorio provideOcorrenciaRepositorio(OcorrenciaDao ocorrenciaDao, TipoDao tipoDao) {

        OcorrenciaRepositorio repositorio = new OcorrenciaRepositorio(ocorrenciaDao, tipoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
