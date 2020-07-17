package com.vvm.sh;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.facebook.stetho.Stetho;
import com.vvm.sh.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {

    public static final int ATUALIZACAO_APP_ID = 1;
    public static final String CANAL_ATUALIZACAO_APP_ID = "Atualização app";


    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        criarCanaisNotificacao();
    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        //Timber.d("AndroidInjector... ");

        return DaggerAppComponent.builder().application(this).build();
    }



    /**
     * Metodo que permite criar os canais de atualizacaoUI
     */
    private void criarCanaisNotificacao() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel canalAtualizacao = new NotificationChannel(CANAL_ATUALIZACAO_APP_ID, "Canal atualização app", NotificationManager.IMPORTANCE_HIGH);
            canalAtualizacao.setDescription("Canal de notificações para a atualização da aplicação");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(canalAtualizacao);
        }
    }
}
