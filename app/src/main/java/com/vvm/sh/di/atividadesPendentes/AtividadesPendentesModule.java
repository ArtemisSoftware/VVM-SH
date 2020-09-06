package com.vvm.sh.di.atividadesPendentes;

import com.vvm.sh.baseDados.dao.AtividadePendenteResultadoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AtividadePendenteRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AtividadesPendentesModule {


    @AtividadesPendentesScope
    @Provides
    static AtividadePendenteResultadoDao provideAtividadePendenteResultadoDao(VvmshBaseDados vvmshBaseDados){

        AtividadePendenteResultadoDao dao = vvmshBaseDados.obterAtividadePendenteResultadoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AtividadesPendentesScope
    @Provides
    AtividadePendenteRepositorio provideAtividadePendenteRepositorio(AtividadePendenteResultadoDao atividadePendenteResultadoDao,
                                                                     TipoDao tipoDao, ResultadoDao resultadoDao) {

        AtividadePendenteRepositorio repositorio = new AtividadePendenteRepositorio(atividadePendenteResultadoDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }


}
