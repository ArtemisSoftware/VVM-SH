package com.vvm.sh;

import android.app.NotificationManager;
import android.os.Build;

//import com.facebook.stetho.Stetho;
import com.facebook.stetho.Stetho;
import com.vvm.sh.di.DaggerAppComponent;
import com.vvm.sh.util.metodos.NotificacaoUtil;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {


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

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(NotificacaoUtil.obterCanalAtualizacao());
        }
    }
}
