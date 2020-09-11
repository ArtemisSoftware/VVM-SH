package com.vvm.sh.di;

import android.app.Application;

import androidx.room.Room;

import com.vvm.sh.baseDados.BaseDadosContantes;
import com.vvm.sh.baseDados.Migracao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.util.metodos.PreferenciasUtil;

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
    static ImagemDao provideImagemResultadoDao(VvmshBaseDados vvmshBaseDados){

        ImagemDao dao = vvmshBaseDados.obterImagemDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @Singleton
    @Provides
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());

        //Timber.d("Providing NoteDao: " + dao);
        return idApi;
    }
}
