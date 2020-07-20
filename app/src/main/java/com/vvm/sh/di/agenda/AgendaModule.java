package com.vvm.sh.di.agenda;


import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.repositorios.AgendaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AgendaModule {


    @AgendaScope
    @Provides
    AgendaRepositorio provideAgendaRepositorio(SegurancaAlimentarApi segurancaAlimentarApi) {

        AgendaRepositorio repositorio = new AgendaRepositorio(segurancaAlimentarApi);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
