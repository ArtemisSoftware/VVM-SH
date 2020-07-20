package com.vvm.sh.di.autenticacao;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.repositorios.AutenticacaoRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AutenticacaoModule {

    @AutenticacaoScope
    @Provides
    AutenticacaoRepositorio provideAutenticacaoRepositorio(SegurancaAlimentarApi segurancaAlimentarApi) {

        AutenticacaoRepositorio repositorio = new AutenticacaoRepositorio(segurancaAlimentarApi);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
