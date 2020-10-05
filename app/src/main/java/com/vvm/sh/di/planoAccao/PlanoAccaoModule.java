package com.vvm.sh.di.planoAccao;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.PlanoAccaoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.repositorios.PlanoAccaoRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class PlanoAccaoModule {

    @PlanoAccaoScope
    @Provides
    static PlanoAccaoDao providePlanoAccaoDao(VvmshBaseDados vvmshBaseDados){

        PlanoAccaoDao dao = vvmshBaseDados.obterPlanoAccaoDao();
        return dao;
    }




    @PlanoAccaoScope
    @Provides
    PlanoAccaoRepositorio providePlanoAccaoRepositorio(PlanoAccaoDao planoAccaoDao, ResultadoDao resultadoDao) {

        PlanoAccaoRepositorio repositorio = new PlanoAccaoRepositorio(planoAccaoDao, resultadoDao);
        return repositorio;
    }

}
