package com.vvm.sh.di.tarefa;

import com.vvm.sh.baseDados.dao.EmailDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.SinistralidadeDao;
import com.vvm.sh.baseDados.dao.TarefaDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TarefaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class TarefaModule {



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
    static SinistralidadeDao provideSinistralidadeDao(VvmshBaseDados vvmshBaseDados){

        SinistralidadeDao dao = vvmshBaseDados.obterSinistralidadeDao();
        return dao;
    }


    @TarefaScope
    @Provides
    TarefaRepositorio provideTarefaRepositorio(TarefaDao tarefaDao, EmailDao emailDao, SinistralidadeDao sinistralidadeDao, ResultadoDao resultadoDao) {

        TarefaRepositorio repositorio = new TarefaRepositorio(tarefaDao, emailDao, sinistralidadeDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }


}
