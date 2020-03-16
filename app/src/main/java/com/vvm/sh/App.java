package com.vvm.sh;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CANAL_ATUALIZACAO_APP_ID = "Atualização app";


    @Override
    public void onCreate() {
        super.onCreate();

        criarCanaisNotificacao();
    }


    /**
     * Metodo que permite criar os canais de notificacao
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
