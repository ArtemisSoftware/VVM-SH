package com.vvm.sh.di;

import android.app.Application;

import androidx.room.Room;

import com.vvm.sh.baseDados.BaseDadosContantes;
import com.vvm.sh.baseDados.Migracao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;

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


}
