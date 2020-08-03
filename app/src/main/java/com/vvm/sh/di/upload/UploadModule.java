package com.vvm.sh.di.upload;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.UploadDao;
import com.vvm.sh.repositorios.UploadRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class UploadModule {



    @UploadScope
    @Provides
    static UploadDao provideUploadDao(VvmshBaseDados vvmshBaseDados){

        UploadDao dao = vvmshBaseDados.obterUploadDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @UploadScope
    @Provides
    UploadRepositorio provideUploadRepositorio(SegurancaAlimentarApi api, UploadDao uploadDao) {

        UploadRepositorio repositorio = new UploadRepositorio(api, uploadDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
