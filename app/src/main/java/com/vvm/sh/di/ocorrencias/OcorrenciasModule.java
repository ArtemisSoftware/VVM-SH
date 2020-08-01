package com.vvm.sh.di.ocorrencias;

import com.vvm.sh.baseDados.AtividadePendenteDao;
import com.vvm.sh.baseDados.OcorrenciaDao;
import com.vvm.sh.baseDados.OcorrenciaHistoricoDao;
import com.vvm.sh.baseDados.OcorrenciaResultadoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.OcorrenciaRepositorio;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaResultado;

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
    static OcorrenciaHistoricoDao provideOcorrenciaHistoricoDao(VvmshBaseDados vvmshBaseDados){

        OcorrenciaHistoricoDao dao = vvmshBaseDados.obterOcorrenciaHistoricoDao();

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
    OcorrenciaRepositorio provideOcorrenciaRepositorio(OcorrenciaDao ocorrenciaDao, OcorrenciaHistoricoDao ocorrenciaHistoricoDao,
                                                       OcorrenciaResultadoDao ocorrenciaResultadoDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        OcorrenciaRepositorio repositorio = new OcorrenciaRepositorio(ocorrenciaDao, ocorrenciaHistoricoDao, ocorrenciaResultadoDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
