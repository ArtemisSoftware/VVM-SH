package com.vvm.sh.di.opcoes;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.VersaoAppRepositorio;

import dagger.Module;
import dagger.Provides;


@Module
public class OpcoesModule {


    @OpcoesScope
    @Provides
    VersaoAppRepositorio provideVersaoAppRepositorio(SegurancaAlimentarApi segurancaAlimentarApi) {

        VersaoAppRepositorio repositorio = new VersaoAppRepositorio(segurancaAlimentarApi);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }



    @OpcoesScope
    @Provides
    static AtualizacaoDao provideAtualizacaoDao(VvmshBaseDados vvmshBaseDados){

        AtualizacaoDao dao = vvmshBaseDados.obterAtualizacaoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }




    @OpcoesScope
    @Provides
    TiposRepositorio provideTiposRepositorio(SegurancaAlimentarApi segurancaAlimentarApi, SegurancaTrabalhoApi segurancaTrabalhoApi,
                                             AtualizacaoDao atualizacaoDao, TipoDao tipoDao) {

        TiposRepositorio repositorio = new TiposRepositorio(segurancaAlimentarApi, segurancaTrabalhoApi, atualizacaoDao, tipoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}
