package com.vvm.sh.di.agenda;


import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.baseDados.AnomaliaDao;
import com.vvm.sh.baseDados.AtividadeExecutadaDao;
import com.vvm.sh.baseDados.ClienteDao;
import com.vvm.sh.baseDados.TarefaDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AgendaRepositorio;
import com.vvm.sh.ui.anomalias.modelos.Anomalia;

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
    static ClienteDao provideClienteDao(VvmshBaseDados vvmshBaseDados){

        ClienteDao dao = vvmshBaseDados.obterClienteDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AgendaScope
    @Provides
    static AnomaliaDao provideAnomaliaDao(VvmshBaseDados vvmshBaseDados){

        AnomaliaDao dao = vvmshBaseDados.obterAnomaliaDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AgendaScope
    @Provides
    AgendaRepositorio provideAgendaRepositorio(SegurancaAlimentarApi segurancaAlimentarApi,
                                               TarefaDao tarefaDao, AtividadeExecutadaDao atividadeExecutadaDao, ClienteDao clienteDao, AnomaliaDao anomaliaDao) {

        AgendaRepositorio repositorio = new AgendaRepositorio(segurancaAlimentarApi, tarefaDao, atividadeExecutadaDao, clienteDao, anomaliaDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
