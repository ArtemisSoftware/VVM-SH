package com.vvm.sh.util.metodos;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.vvm.sh.R;
import static com.vvm.sh.util.constantes.Identificadores.Notificacoes.*;

public class NotificacaoUtil {


    /**
     * Metodo que permite gerar a notificacao da atualizacao da aplicacao
     * @param contexto
     * @param versao a versao da nova atualizacao
     */
    public static void notificarAtualizacaoApp(Context contexto, String versao) {

        NotificationManagerCompat gestor = NotificationManagerCompat.from(contexto);

        Notification notificacao = new NotificationCompat.Builder(contexto, CANAL_ATUALIZACAO_APP)
                .setSmallIcon(R.drawable.ic_lorem_ipsum_24dp)
                .setContentTitle(contexto.getString(R.string.atualizacao_sh))
                .setContentText(contexto.getString(R.string.nova_versao_disponivel_versao_) + versao)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .build();

        gestor.notify(ID_ATUALIZACAO_APP, notificacao);
    }


    /**
     * Metodo que permite obter o canal de notificacao para a atualizacao da app
     * @return um canal
     */
    public static NotificationChannel obterCanalAtualizacao(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel canal = new NotificationChannel(CANAL_ATUALIZACAO_APP, "Canal atualização app", NotificationManager.IMPORTANCE_HIGH);
            canal.setDescription("Canal de notificações para a atualização da aplicação");
            return canal;
        }

        return null;
    }


}
