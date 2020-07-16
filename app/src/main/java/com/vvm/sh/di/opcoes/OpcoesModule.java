package com.vvm.sh.di.opcoes;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.VersaoAppRepositorio;

import dagger.Module;
import dagger.Provides;


@Module
public class OpcoesModule {


    @OpcoesScope
    @Provides
    VersaoAppRepositorio providePokemonRepositorio(SegurancaAlimentarApi segurancaAlimentarApi) {

        VersaoAppRepositorio repositorio = new VersaoAppRepositorio(segurancaAlimentarApi);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }


    @OpcoesScope
    @Provides
    TiposRepositorio provideTiposRepositorio(SegurancaAlimentarApi segurancaAlimentarApi) {

        TiposRepositorio repositorio = new TiposRepositorio(segurancaAlimentarApi);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
