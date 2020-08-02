package com.vvm.sh.di.atividadesPendentes.formacao;

import com.vvm.sh.baseDados.FormandoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.FormacaoRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class FormacaoModule {



    @FormacaoScope
    @Provides
    static FormandoDao provideFormandoDao(VvmshBaseDados vvmshBaseDados){

        FormandoDao dao = vvmshBaseDados.obterFormandoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @FormacaoScope
    @Provides
    FormacaoRepositorio provideFormacaoRepositorio(FormandoDao formandoDao) {

        FormacaoRepositorio repositorio = new FormacaoRepositorio(formandoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
