package com.vvm.sh.di.agenda;


import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.baseDados.AtividadeExecutadaDao;
import com.vvm.sh.baseDados.AtividadePendenteDao;
import com.vvm.sh.baseDados.ClienteDao;
import com.vvm.sh.baseDados.OcorrenciaDao;
import com.vvm.sh.baseDados.OcorrenciaHistoricoDao;
import com.vvm.sh.baseDados.TarefaDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.DownloadTrabalhoDao;
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
    static OcorrenciaDao provideOcorrenciaDao(VvmshBaseDados vvmshBaseDados){

        OcorrenciaDao dao = vvmshBaseDados.obterOcorrenciaDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AgendaScope
    @Provides
    static OcorrenciaHistoricoDao provideOcorrenciaHistoricoDao(VvmshBaseDados vvmshBaseDados){

        OcorrenciaHistoricoDao dao = vvmshBaseDados.obterOcorrenciaHistoricoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AgendaScope
    @Provides
    static DownloadTrabalhoDao provideDownloadTrabalhoDao(VvmshBaseDados vvmshBaseDados){

        DownloadTrabalhoDao dao = vvmshBaseDados.obterDownloadTrabalhoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AgendaScope
    @Provides
    AgendaRepositorio provideAgendaRepositorio(SegurancaAlimentarApi segurancaAlimentarApi, DownloadTrabalhoDao downloadTrabalhoDao,
                                               TarefaDao tarefaDao, AtividadeExecutadaDao atividadeExecutadaDao,
                                               ClienteDao clienteDao, AtividadePendenteDao atividadePendenteDao,
                                               OcorrenciaDao ocorrenciaDao, OcorrenciaHistoricoDao ocorrenciaHistoricoDao) {

        AgendaRepositorio repositorio = new AgendaRepositorio(segurancaAlimentarApi, downloadTrabalhoDao, tarefaDao, atividadeExecutadaDao, clienteDao, atividadePendenteDao, ocorrenciaDao, ocorrenciaHistoricoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
