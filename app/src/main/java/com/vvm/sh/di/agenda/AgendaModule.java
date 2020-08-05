package com.vvm.sh.di.agenda;


import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.baseDados.AtividadePendenteDao;
import com.vvm.sh.baseDados.ClienteDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AgendaDao;
import com.vvm.sh.baseDados.dao.DownloadTrabalhoDao;
import com.vvm.sh.repositorios.AgendaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AgendaModule {


    @AgendaScope
    @Provides
    static DownloadTrabalhoDao provideDownloadTrabalhoDao(VvmshBaseDados vvmshBaseDados){

        DownloadTrabalhoDao dao = vvmshBaseDados.obterDownloadTrabalhoDao();
        return dao;
    }


    @AgendaScope
    @Provides
    static AgendaDao provideAgendaDao(VvmshBaseDados vvmshBaseDados){

        AgendaDao dao = vvmshBaseDados.obterAgendaDao();
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
    static AtividadePendenteDao provideAtividadePendenteDao(VvmshBaseDados vvmshBaseDados){

        AtividadePendenteDao dao = vvmshBaseDados.obterAtividadePendenteDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }






    @AgendaScope
    @Provides
    AgendaRepositorio provideAgendaRepositorio(SegurancaAlimentarApi segurancaAlimentarApi, DownloadTrabalhoDao downloadTrabalhoDao,
                                               AgendaDao agendaDao,
                                               ClienteDao clienteDao, AtividadePendenteDao atividadePendenteDao) {

        AgendaRepositorio repositorio = new AgendaRepositorio(segurancaAlimentarApi, downloadTrabalhoDao, agendaDao, clienteDao, atividadePendenteDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
