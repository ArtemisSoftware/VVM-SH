package com.vvm.sh.servicos.instalacaoApp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.IVersaoApp;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.metodos.DiretoriasUtil;

import static com.vvm.sh.util.AtualizacaoUI_.Estado.ERRO_INSTALACAO_ATUALIZACAO_APP;
import static com.vvm.sh.util.AtualizacaoUI_.Estado.PROCESSAMENTO_INSTALACAO_ATUALIZACAO_APP;

public class InstalarApkAsyncTask extends AsyncTask<IVersaoApp, Void, Void> {


    private Context contexto;
    private String erro = null;
    private OnTransferenciaListener listener;

    public InstalarApkAsyncTask(OnTransferenciaListener listener, Context contexto) {

        this.contexto = contexto;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(IVersaoApp... versaoApps) {


        if(versaoApps[0] == null)
            return null;

        IVersaoApp versaoApp = versaoApps[0];

        listener.atualizarTransferencia(new AtualizacaoUI_(PROCESSAMENTO_INSTALACAO_ATUALIZACAO_APP, 50, 100, "Instalacão"));


        Uri uri = DiretoriasUtil.obterUri(contexto, versaoApp.ficheiro);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, AppConfig.MIME_VERSAO_APP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            contexto.startActivity(intent);
        }
        catch(Exception e){

            listener.atualizarTransferencia(new AtualizacaoUI_(ERRO_INSTALACAO_ATUALIZACAO_APP, e.getMessage()));
        }



        return null;
    }
}
