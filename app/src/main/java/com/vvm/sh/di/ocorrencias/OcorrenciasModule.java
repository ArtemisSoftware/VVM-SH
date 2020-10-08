package com.vvm.sh.di.ocorrencias;

import com.vvm.sh.baseDados.dao.OcorrenciaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.OcorrenciaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class OcorrenciasModule {

    @OcorrenciasScope
    @Provides
    static OcorrenciaDao provideOcorrenciaResultado(VvmshBaseDados vvmshBaseDados){

        OcorrenciaDao dao = vvmshBaseDados.obterOcorrenciaDao();
        return dao;
    }


    @OcorrenciasScope
    @Provides
    OcorrenciaRepositorio provideOcorrenciaRepositorio(int idApi,OcorrenciaDao ocorrenciaDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        OcorrenciaRepositorio repositorio = new OcorrenciaRepositorio(idApi, ocorrenciaDao, tipoDao, resultadoDao);
        return repositorio;
    }
}
