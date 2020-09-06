package com.vvm.sh.di;

import android.app.Application;

import androidx.room.Room;

import com.vvm.sh.baseDados.BaseDadosContantes;
import com.vvm.sh.baseDados.Migracao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.ImagemResultadoDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseDadosModule {

    @Singleton
    @Provides
    static VvmshBaseDados provideVvmshBaseDados(Application application){
        return Room.databaseBuilder(application, VvmshBaseDados.class, BaseDadosContantes.NOME)
                .addMigrations(Migracao.obterMigracoes())
                .build();
    }


    @Singleton
    @Provides
    static TipoDao provideTipoDao(VvmshBaseDados vvmshBaseDados){

        TipoDao dao = vvmshBaseDados.obterTipoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @Singleton
    @Provides
    static ResultadoDao provideResultadoDao(VvmshBaseDados vvmshBaseDados){

        ResultadoDao dao = vvmshBaseDados.obterResultadoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @Singleton
    @Provides
    static ImagemResultadoDao provideImagemResultadoDao(VvmshBaseDados vvmshBaseDados){

        ImagemResultadoDao dao = vvmshBaseDados.obterImagemResultadoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }

}
