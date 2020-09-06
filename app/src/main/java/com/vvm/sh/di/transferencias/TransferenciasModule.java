package com.vvm.sh.di.transferencias;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.TransferenciasDao;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.TransferenciasRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class TransferenciasModule {



    @TransferenciasScope
    @Provides
    static TransferenciasDao provideUploadDao(VvmshBaseDados vvmshBaseDados){

        TransferenciasDao dao = vvmshBaseDados.obterTransferenciasDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }

    @TransferenciasScope
    @Provides
    static AtualizacaoDao provideAtualizacaoDao(VvmshBaseDados vvmshBaseDados){

        AtualizacaoDao dao = vvmshBaseDados.obterAtualizacaoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @TransferenciasScope
    @Provides
    TransferenciasRepositorio provideUploadRepositorio(SegurancaAlimentarApi api, TransferenciasDao transferenciasDao) {

        TransferenciasRepositorio repositorio = new TransferenciasRepositorio(api, transferenciasDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }


    @TransferenciasScope
    @Provides
    TiposRepositorio provideTiposRepositorio(SegurancaAlimentarApi segurancaAlimentarApi, AtualizacaoDao atualizacaoDao, TipoDao tipoDao) {

        TiposRepositorio repositorio = new TiposRepositorio(segurancaAlimentarApi, atualizacaoDao, tipoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }
}