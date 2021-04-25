package com.vvm.sh.di.atividadesPendentes.avaliacaoAmbiental;

import android.app.Application;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AvaliacaoAmbientalDao;
import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.MedidaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.di.atividadesPendentes.averiguacao.AveriguacaoScope;
import com.vvm.sh.repositorios.AvaliacaoAmbientalRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AvaliacaoAmbientalModule {

    @AvaliacaoAmbientalScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }

    @AvaliacaoAmbientalScope
    @Provides
    static AvaliacaoAmbientalDao provideAvaliacaoAmbientalDao(VvmshBaseDados vvmshBaseDados){

        AvaliacaoAmbientalDao dao = vvmshBaseDados.obterAvaliacaoAmbientalDao();
        return dao;
    }



    @AvaliacaoAmbientalScope
    @Provides
    static CategoriaProfissionalDao provideCategoriaProfissionalDao(VvmshBaseDados vvmshBaseDados){

        CategoriaProfissionalDao dao = vvmshBaseDados.obterCategoriaProfissionalDao();
        return dao;
    }


    @AvaliacaoAmbientalScope
    @Provides
    static MedidaDao provideMedidaDao(VvmshBaseDados vvmshBaseDados){

        MedidaDao dao = vvmshBaseDados.obterMedidaDao();
        return dao;
    }


    @AvaliacaoAmbientalScope
    @Provides
    AvaliacaoAmbientalRepositorio provideAtividadePendenteRepositorio(@Named("idApi_") int idApi, AvaliacaoAmbientalDao avaliacaoAmbientalDao, CategoriaProfissionalDao categoriaProfissionalDao,
                                                                      MedidaDao medidaDao,
                                                                      TipoDao tipoDao, ResultadoDao resultadoDao) {

        AvaliacaoAmbientalRepositorio repositorio = new AvaliacaoAmbientalRepositorio(idApi, avaliacaoAmbientalDao, categoriaProfissionalDao, medidaDao, tipoDao, resultadoDao);
        return repositorio;
    }

}
