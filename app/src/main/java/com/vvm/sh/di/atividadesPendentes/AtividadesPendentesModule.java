package com.vvm.sh.di.atividadesPendentes;

import com.vvm.sh.baseDados.AnomaliaDao;
import com.vvm.sh.baseDados.AtividadePendenteDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AtividadePendenteRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AtividadesPendentesModule {

    @AtividadesPendentesScope
    @Provides
    static AtividadePendenteDao provideAtividadePendenteDao(VvmshBaseDados vvmshBaseDados){

        AtividadePendenteDao dao = vvmshBaseDados.obterAtividadePendenteDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AtividadesPendentesScope
    @Provides
    AtividadePendenteRepositorio provideAtividadePendenteRepositorio(AtividadePendenteDao atividadePendenteDao) {

        AtividadePendenteRepositorio repositorio = new AtividadePendenteRepositorio(atividadePendenteDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
