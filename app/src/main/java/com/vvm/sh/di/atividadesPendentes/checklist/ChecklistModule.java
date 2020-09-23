package com.vvm.sh.di.atividadesPendentes.checklist;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AreaChecklistDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;

import dagger.Provides;

public class ChecklistModule {


    @ChecklistScope
    @Provides
    static AreaChecklistDao provideAreaChecklistDao(VvmshBaseDados vvmshBaseDados){

        AreaChecklistDao dao = vvmshBaseDados.obterAreaChecklistDao();
        return dao;
    }


//
//    @ChecklistScope
//    @Provides
//    ChecklistRepositorio provideChecklistRepositorio(int idApi, AreaChecklistDao areaChecklistDao,
//                                                                     TipoDao tipoDao, ResultadoDao resultadoDao) {
//
//        ChecklistRepositorio repositorio = new ChecklistRepositorio(idApi, areaChecklistDao, tipoDao, resultadoDao);
//
//        //Timber.d("Providing PokemonRepository: " + repository);
//        return repositorio;
//    }

}
