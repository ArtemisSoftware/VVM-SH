package com.vvm.sh.di.opcoes;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.repositorios.VersaoAppRepositorio;

import dagger.Module;
import dagger.Provides;


@Module
public class OpcoesModule {


    //@PokemonScope
    @Provides
    VersaoAppRepositorio providePokemonRepository(SegurancaAlimentarApi segurancaAlimentarApi) {

        VersaoAppRepositorio repositorio = new VersaoAppRepositorio(segurancaAlimentarApi);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
