package com.vvm.sh.di.agenda;


import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AgendaDao;
import com.vvm.sh.baseDados.dao.UtilizadorDao;
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
    static UtilizadorDao provideUtilizadorDao(VvmshBaseDados vvmshBaseDados){

        UtilizadorDao dao = vvmshBaseDados.obterUtilizadorDao();
        return dao;
    }

    @AgendaScope
    @Provides
    AgendaRepositorio provideAgendaRepositorio(AgendaDao agendaDao, UtilizadorDao utilizadorDao) {

        AgendaRepositorio repositorio = new AgendaRepositorio(agendaDao, utilizadorDao);
        return repositorio;
    }
}
