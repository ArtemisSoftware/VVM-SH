package com.vvm.sh.di.atividadesPendentes.averiguacao;

import android.app.Application;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.AveriguacaoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.di.atividadesPendentes.AtividadesPendentesScope;
import com.vvm.sh.repositorios.AveriguacaoRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AveriguacaoModule {

    @AveriguacaoScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }

    @AveriguacaoScope
    @Provides
    static AveriguacaoDao provideAveriguacaoDao(VvmshBaseDados vvmshBaseDados){

        AveriguacaoDao dao = vvmshBaseDados.obterAveriguacaoDao();
        return dao;
    }


    @AveriguacaoScope
    @Provides
    AveriguacaoRepositorio provideAveriguacaoRepositorio(@Named("idApi_") int idApi, AveriguacaoDao averiguacaoDao, ResultadoDao resultadoDao) {

        AveriguacaoRepositorio repositorio = new AveriguacaoRepositorio(idApi, averiguacaoDao, resultadoDao);
        return repositorio;
    }

}
