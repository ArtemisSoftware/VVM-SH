package com.vvm.sh.di.atividadesPendentes;

import com.vvm.sh.baseDados.AnomaliaDao;
import com.vvm.sh.baseDados.AtividadePendenteDao;
import com.vvm.sh.baseDados.AtividadePendenteResultadoDao;
import com.vvm.sh.baseDados.FormandoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AtividadePendenteRepositorio;
import com.vvm.sh.repositorios.FormacaoRepositorio;

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
    static AtividadePendenteResultadoDao provideAtividadePendenteResultadoDao(VvmshBaseDados vvmshBaseDados){

        AtividadePendenteResultadoDao dao = vvmshBaseDados.obterAtividadePendenteResultadoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AtividadesPendentesScope
    @Provides
    AtividadePendenteRepositorio provideAtividadePendenteRepositorio(AtividadePendenteDao atividadePendenteDao, AtividadePendenteResultadoDao atividadePendenteResultadoDao,
                                                                     TipoDao tipoDao, ResultadoDao resultadoDao) {

        AtividadePendenteRepositorio repositorio = new AtividadePendenteRepositorio(atividadePendenteDao, atividadePendenteResultadoDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }


}
