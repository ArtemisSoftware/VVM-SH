package com.vvm.sh.di;

import android.app.Application;

import androidx.room.Room;

import com.vvm.sh.baseDados.BaseDadosContantes;
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
                //.addMigrations(MigrationDb.getMigrations())
                .build();
    }



}
