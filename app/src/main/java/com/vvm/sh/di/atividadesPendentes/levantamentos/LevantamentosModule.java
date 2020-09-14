package com.vvm.sh.di.atividadesPendentes.levantamentos;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.LevantamentoDao;
import com.vvm.sh.di.atividadesPendentes.avaliacaoAmbiental.AvaliacaoAmbientalScope;

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

}
