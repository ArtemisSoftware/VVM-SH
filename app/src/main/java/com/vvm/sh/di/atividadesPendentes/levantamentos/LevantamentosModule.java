package com.vvm.sh.di.atividadesPendentes.levantamentos;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.LevantamentoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.RiscoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.di.atividadesPendentes.avaliacaoAmbiental.AvaliacaoAmbientalScope;
import com.vvm.sh.repositorios.LevantamentoRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class LevantamentosModule {

    @LevantamentosScope
    @Provides
    static CategoriaProfissionalDao provideCategoriaProfissionalDao(VvmshBaseDados vvmshBaseDados){

        CategoriaProfissionalDao dao = vvmshBaseDados.obterCategoriaProfissionalDao();
        return dao;
    }


    @LevantamentosScope
    @Provides
    static LevantamentoDao provideProvidesLevantamentoDao(VvmshBaseDados vvmshBaseDados){

        LevantamentoDao dao = vvmshBaseDados.obterLevantamentoDao();
        return dao;
    }


    @LevantamentosScope
    @Provides
    static RiscoDao provideProvidesRiscoDao(VvmshBaseDados vvmshBaseDados){

        RiscoDao dao = vvmshBaseDados.obterRiscoDao();
        return dao;
    }


    @LevantamentosScope
    @Provides
    LevantamentoRepositorio provideLevantamentoRepositorio(int idApi, LevantamentoDao levantamentoDao, CategoriaProfissionalDao categoriaProfissionalDao,
                                                           RiscoDao riscoDao,
                                                           TipoDao tipoDao, ResultadoDao resultadoDao) {

        LevantamentoRepositorio repositorio = new LevantamentoRepositorio(idApi, levantamentoDao, categoriaProfissionalDao, riscoDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
