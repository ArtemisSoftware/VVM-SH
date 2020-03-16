package com.vvm.sh.servicos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;

import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.metodos.Diretorias;

public class ServicoInstalacaoApk extends Servico {

    private Context contexto;
    private Uri uri;

    public ServicoInstalacaoApk(Context contexto, Handler handler, VersaoApp versaoApp) {
        super(handler);

        this.contexto = contexto;

        //File ficheiro = new File(Environment.getExternalStorageDirectory().toString() + "/" + Diretorias.DIRETORIA_DOWNLOAD, nomeFicheiro);
        //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Caminho para o novo apk: "+ file.getAbsolutePath());

        uri = Diretorias.obterUri(contexto, versaoApp.obterFicheiro());
    }


    @Override
    protected void executar() {

        //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Download da nova versão da aplicacao completo. A iniciar a instalação....");

        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, "Instalacão", 50, 100);

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
    }


    //----------------------
    //
    //----------------------

    @Override
    protected void terminarExecucao() {

    }
}
