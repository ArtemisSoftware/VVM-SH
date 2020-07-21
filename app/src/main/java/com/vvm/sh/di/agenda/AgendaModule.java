package com.vvm.sh.di.agenda;


import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.baseDados.AtividadeExecutadaDao;
import com.vvm.sh.baseDados.TarefaDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AgendaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AgendaModule {



    @AgendaScope
    @Provides
    static TarefaDao provideTarefaDao(VvmshBaseDados vvmshBaseDados){

        TarefaDao dao = vvmshBaseDados.obterTarefaDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AgendaScope
    @Provides
    static AtividadeExecutadaDao provideAtividadeExecutadaDao(VvmshBaseDados vvmshBaseDados){

        AtividadeExecutadaDao dao = vvmshBaseDados.obterAtividadeExecutadaDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AgendaScope
    @Provides
    AgendaRepositorio provideAgendaRepositorio(SegurancaAlimentarApi segurancaAlimentarApi, TarefaDao tarefaDao, AtividadeExecutadaDao atividadeExecutadaDao) {

        AgendaRepositorio repositorio = new AgendaRepositorio(segurancaAlimentarApi, tarefaDao, atividadeExecutadaDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
