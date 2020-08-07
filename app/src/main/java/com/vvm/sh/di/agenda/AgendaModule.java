package com.vvm.sh.di.agenda;


import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AgendaDao;
import com.vvm.sh.repositorios.AgendaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AgendaModule {


    @AgendaScope
    @Provides
    static AgendaDao provideAgendaDao(VvmshBaseDados vvmshBaseDados){

        AgendaDao dao = vvmshBaseDados.obterAgendaDao();
        return dao;
    }


    @AgendaScope
    @Provides
    AgendaRepositorio provideAgendaRepositorio(AgendaDao agendaDao) {

        AgendaRepositorio repositorio = new AgendaRepositorio(agendaDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
