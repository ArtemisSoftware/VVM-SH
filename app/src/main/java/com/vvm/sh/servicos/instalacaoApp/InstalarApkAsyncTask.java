package com.vvm.sh.servicos.instalacaoApp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.VersaoApp;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.metodos.DiretoriasUtil;

public class InstalarApkAsyncTask extends AsyncTask<VersaoApp, Void, Void> {


    private Context contexto;

    /**
     * Permite enviar mensagens para fora do servico
     */
    private AtualizacaoUI atualizacaoUI;



    public InstalarApkAsyncTask(Context contexto, Handler handler) {

        this.contexto = contexto;
        atualizacaoUI = new AtualizacaoUI(handler);
    }

    @Override
    protected Void doInBackground(VersaoApp... versaoApps) {


        if(versaoApps[0] == null)
            return null;

        VersaoApp versaoApp = versaoApps[0];


        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, "Instalacão", 50, 100);


        Uri uri = DiretoriasUtil.obterUri(contexto, versaoApp.ficheiro);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, AppConfig.MIME_VERSAO_APP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            contexto.startActivity(intent);
        }
        catch(Exception e){
            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.ERRO_INSTALACAO_APK, e.getMessage());
        }



        return null;
    }
}
