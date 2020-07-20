package com.vvm.sh.di.autenticacao;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.baseDados.UtilizadorDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AutenticacaoRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AutenticacaoModule {


    @AutenticacaoScope
    @Provides
    static UtilizadorDao provideUtilizadorDao(VvmshBaseDados vvmshBaseDados){

        UtilizadorDao dao = vvmshBaseDados.obterUtilizadorDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }



    @AutenticacaoScope
    @Provides
    AutenticacaoRepositorio provideAutenticacaoRepositorio(SegurancaAlimentarApi segurancaAlimentarApi, UtilizadorDao utilizadorDao) {

        AutenticacaoRepositorio repositorio = new AutenticacaoRepositorio(segurancaAlimentarApi, utilizadorDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
