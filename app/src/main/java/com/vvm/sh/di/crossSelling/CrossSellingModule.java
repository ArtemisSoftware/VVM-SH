package com.vvm.sh.di.crossSelling;

import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.repositorios.CrossSellingRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class CrossSellingModule {


    @CrossSellingScope
    @Provides
    CrossSellingRepositorio provideCrossSellingRepositorio(TipoDao tipoDao) {

        CrossSellingRepositorio repositorio = new CrossSellingRepositorio(tipoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
