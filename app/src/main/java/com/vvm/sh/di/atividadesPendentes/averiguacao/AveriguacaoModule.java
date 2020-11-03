package com.vvm.sh.di.atividadesPendentes.averiguacao;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.AveriguacaoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.di.atividadesPendentes.AtividadesPendentesScope;
import com.vvm.sh.repositorios.AveriguacaoRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AveriguacaoModule {

    @AveriguacaoScope
    @Provides
    static AveriguacaoDao provideAveriguacaoDao(VvmshBaseDados vvmshBaseDados){

        AveriguacaoDao dao = vvmshBaseDados.obterAveriguacaoDao();
        return dao;
    }


    @AveriguacaoScope
    @Provides
    AveriguacaoRepositorio provideAveriguacaoRepositorio(int idApi, AveriguacaoDao averiguacaoDao, ResultadoDao resultadoDao) {

        AveriguacaoRepositorio repositorio = new AveriguacaoRepositorio(idApi, averiguacaoDao, resultadoDao);
        return repositorio;
    }

}
