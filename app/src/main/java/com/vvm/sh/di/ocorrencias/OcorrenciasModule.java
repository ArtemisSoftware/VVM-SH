package com.vvm.sh.di.ocorrencias;

import android.app.Application;

import com.vvm.sh.baseDados.dao.OcorrenciaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.OcorrenciaRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class OcorrenciasModule {


    @OcorrenciasScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }

    @OcorrenciasScope
    @Provides
    static OcorrenciaDao provideOcorrenciaResultado(VvmshBaseDados vvmshBaseDados){

        OcorrenciaDao dao = vvmshBaseDados.obterOcorrenciaDao();
        return dao;
    }


    @OcorrenciasScope
    @Provides
    OcorrenciaRepositorio provideOcorrenciaRepositorio(@Named("idApi_") int idApi,OcorrenciaDao ocorrenciaDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        OcorrenciaRepositorio repositorio = new OcorrenciaRepositorio(idApi, ocorrenciaDao, tipoDao, resultadoDao);
        return repositorio;
    }
}
