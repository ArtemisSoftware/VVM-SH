package com.vvm.sh.di.autenticacao;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.baseDados.dao.UtilizadorDao;
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
    AutenticacaoRepositorio provideAutenticacaoRepositorio(SegurancaAlimentarApi segurancaAlimentarApi, SegurancaTrabalhoApi segurancaTrabalhoApi, UtilizadorDao utilizadorDao) {

        AutenticacaoRepositorio repositorio = new AutenticacaoRepositorio(segurancaAlimentarApi, segurancaTrabalhoApi, utilizadorDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
