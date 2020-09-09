package com.vvm.sh.di.quadroPessoal;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.QuadroPessoalDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;

import dagger.Module;
import dagger.Provides;

@Module
public class QuadroPessoalModule {

    @QuadroPessoalScope
    @Provides
    static QuadroPessoalDao provideQuadroPessoalDao(VvmshBaseDados vvmshBaseDados){

        QuadroPessoalDao dao = vvmshBaseDados.obterQuadroPessoalDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }



//    @QuadroPessoalScope
//    @Provides
//    QuadroPessoalRepositorio provideTarefaRepositorio(QuadroPessoalDao quadroPessoalDao, ResultadoDao resultadoDao) {
//
//        QuadroPessoalRepositorio repositorio = new QuadroPessoalRepositorio(quadroPessoalDao, resultadoDao);
//
//        //Timber.d("Providing PokemonRepository: " + repository);
//        return repositorio;
//    }

}
