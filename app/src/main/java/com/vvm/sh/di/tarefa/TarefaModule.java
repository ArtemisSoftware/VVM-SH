package com.vvm.sh.di.tarefa;

import com.vvm.sh.baseDados.AtividadeExecutadaDao;
import com.vvm.sh.baseDados.ClienteDao;
import com.vvm.sh.baseDados.EmailDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TarefaDao;
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
    static TarefaDao provideTarefaDao(VvmshBaseDados vvmshBaseDados){

        TarefaDao dao = vvmshBaseDados.obterTarefaDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @TarefaScope
    @Provides
    static EmailDao provideEmailDao(VvmshBaseDados vvmshBaseDados){

        EmailDao dao = vvmshBaseDados.obterEmailDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @TarefaScope
    @Provides
    TarefaRepositorio provideTarefaRepositorio(AtividadeExecutadaDao atividadeExecutadaDao, TarefaDao tarefaDao, EmailDao emailDao, ResultadoDao resultadoDao) {

        TarefaRepositorio repositorio = new TarefaRepositorio(tarefaDao, atividadeExecutadaDao, emailDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }


}
