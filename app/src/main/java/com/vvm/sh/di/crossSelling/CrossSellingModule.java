package com.vvm.sh.di.crossSelling;

import com.vvm.sh.baseDados.CrossSellingDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.CrossSellingRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class CrossSellingModule {


    @CrossSellingScope
    @Provides
    static CrossSellingDao provideCrossSellingDao(VvmshBaseDados vvmshBaseDados){

        CrossSellingDao dao = vvmshBaseDados.obterCrossSellingDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @CrossSellingScope
    @Provides
    CrossSellingRepositorio provideCrossSellingRepositorio(CrossSellingDao crossSellingDao, TipoDao tipoDao, ResultadoDao resultadoDao) {

        CrossSellingRepositorio repositorio = new CrossSellingRepositorio(crossSellingDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
