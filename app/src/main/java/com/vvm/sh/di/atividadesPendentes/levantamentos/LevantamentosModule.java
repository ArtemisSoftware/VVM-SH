package com.vvm.sh.di.atividadesPendentes.levantamentos;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.LevantamentoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
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

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @LevantamentosScope
    @Provides
    static LevantamentoDao provideProvides(VvmshBaseDados vvmshBaseDados){

        LevantamentoDao dao = vvmshBaseDados.obterLevantamentoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @LevantamentosScope
    @Provides
    LevantamentoRepositorio provideLevantamentoRepositorio(int idApi, LevantamentoDao levantamentoDao, CategoriaProfissionalDao categoriaProfissionalDao,
                                                           TipoDao tipoDao, ResultadoDao resultadoDao) {

        LevantamentoRepositorio repositorio = new LevantamentoRepositorio(idApi, levantamentoDao, categoriaProfissionalDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
