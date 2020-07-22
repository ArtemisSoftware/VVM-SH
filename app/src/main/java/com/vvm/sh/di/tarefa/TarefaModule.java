package com.vvm.sh.di.tarefa;

import com.vvm.sh.baseDados.AtividadeExecutadaDao;
import com.vvm.sh.baseDados.ClienteDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TarefaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class TarefaModule {

    @TarefaScope
    @Provides
    static AtividadeExecutadaDao provideAtividadeExecutadaDao(VvmshBaseDados vvmshBaseDados){

        AtividadeExecutadaDao dao = vvmshBaseDados.obterAtividadeExecutadaDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @TarefaScope
    @Provides
    static ClienteDao provideClienteDao(VvmshBaseDados vvmshBaseDados){

        ClienteDao dao = vvmshBaseDados.obterClienteDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @TarefaScope
    @Provides
    TarefaRepositorio provideTarefaRepositorio(AtividadeExecutadaDao atividadeExecutadaDao, ClienteDao clienteDao) {

        TarefaRepositorio repositorio = new TarefaRepositorio(clienteDao, atividadeExecutadaDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }


}
