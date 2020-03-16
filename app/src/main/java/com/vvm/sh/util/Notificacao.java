package com.vvm.sh.util;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.vvm.sh.App;
import com.vvm.sh.R;

public class Notificacao {


    /**
     * Metodo que permite gerar a notificacao da atualizacao da aplicacao
     * @param contexto
     * @param versao a versao da nova atualizacao
     */
    public static void notificarAtualizacaoApp(Context contexto, String versao) {

        NotificationManagerCompat gestor = NotificationManagerCompat.from(contexto);

        Notification notificacao = new NotificationCompat.Builder(contexto, App.CANAL_ATUALIZACAO_APP_ID)
                .setSmallIcon(R.drawable.ic_lorem_ipsum_24dp)
                .setContentTitle(contexto.getString(R.string.atualizacao_sh))
                .setContentText(contexto.getString(R.string.versao_) + versao)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .build();

        gestor.notify(App.ATUALIZACAO_APP_ID, notificacao);
    }

}
