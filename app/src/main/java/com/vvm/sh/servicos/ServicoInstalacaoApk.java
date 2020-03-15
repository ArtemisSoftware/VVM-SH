package com.vvm.sh.servicos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;

import com.vvm.sh.util.Notificacao;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.metodos.Diretorias;

import java.io.File;

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

        notificacao.atualizarUI(Notificacao.Codigo.PROCESSAMENTO_DADOS, "Instalacão", 0, 1);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, AppConfig.MIME_VERSAO_APP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            contexto.startActivity(intent);
        }
        catch(Exception e){
            notificacao.atualizarUI(Notificacao.Codigo., e.getMessage());
        }
    }


    //----------------------
    //
    //----------------------

    @Override
    protected void terminarExecucao() {

    }
}
