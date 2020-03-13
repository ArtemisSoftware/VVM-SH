package com.vvm.sh.servicos;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;

import com.vvm.sh.util.Notificacao;
import com.vvm.sh.util.constantes.Sintaxe;

import java.io.File;

public class ServicoDownloadApk extends Servico {

    private DownloadManager downloadManager;
    private long idDownload;
    private String nomeFicheiro;
    private boolean downloadCompleto;


    public ServicoDownloadApk(Context contexto, Handler handler, VersaoApp versaoApp) {
        super(handler);

        initVariaveisDownload(contexto, versaoApp);
    }


    /**
     * Metodo que permite inicar as variaveis necessarias ao download do novo apk
     * @param contexto
     */
    private void initVariaveisDownload(Context contexto, VersaoApp versaoApp) {

        downloadCompleto = false;

        Uri Download_Uri = Uri.parse(versaoApp.obterUrlDownload());
        //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Uri:" + Download_Uri.toString());

        nomeFicheiro = Download_Uri.toString().split("/")[Download_Uri.toString().split("/").length - 1];
        //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Nome ficheiro:" + nomeFicheiro);

        File ficheiro = null;//--new File(Environment.getExternalStorageDirectory().toString() + "/" + AppConfigIF.DIRETORIA_DOWNLOAD, nomeFicheiro);

        if(ficheiro.exists()){

            //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Ficheiro:" + nomeFicheiro + " foi apagado para ser substituido por um ficheiro mais recente.");
            ficheiro.delete();
        }

        DownloadManager.Request pedido = new DownloadManager.Request(Uri.parse(versaoApp.obterUrlDownload()));
        pedido.setDescription(versaoApp.obterTexto());
        pedido.setTitle("Atualização v." + versaoApp.obterVersao());
        //--pedido.setDestinationInExternalPublicDir("/" + AppConfigIF.DIRETORIA_DOWNLOAD, nomeFicheiro);
        pedido.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        pedido.setAllowedOverRoaming(false);

        downloadManager = (DownloadManager) contexto.getSystemService(Context.DOWNLOAD_SERVICE);
        idDownload = downloadManager.enqueue(pedido);
    }



    @Override
    protected void executar() {

        boolean downloadEmCurso = true;

        while (downloadEmCurso) {

            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(idDownload);

            Cursor cursor = downloadManager.query(query);
            cursor.moveToFirst();

            int bytesDescarregados = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
            int totalBytes = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
            int estado = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));

            if (estado == DownloadManager.STATUS_SUCCESSFUL) {
                downloadEmCurso = false;
                downloadCompleto = true;
            }

            if(estado == DownloadManager.STATUS_FAILED){

                //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Download falhou. Razao: " + cursor.getColumnIndex(DownloadManager.COLUMN_REASON));
                break;
            }

            if(totalBytes != -1 & bytesDescarregados != 0){

                //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Download: " + bytesDescarregados + " / " + totalBytes);
                notificacao.atualizarUI(Notificacao.Codigo.PROCESSAMENTO_DADOS, obterEstado(cursor), bytesDescarregados, totalBytes);
            }

            cursor.close();
        }
    }


    //-------------------------------
    //Metodos locais
    //-------------------------------


    /**
     * Metodo que permite obter uma mensagem com o estado do download do apk
     * @param cursor cursor com os dados do download
     * @return uma mensagem de estado
     */
    private String obterEstado(Cursor cursor) {

        String msg = Sintaxe.SEM_TEXTO;

        int estado = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
        String razao = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_REASON));

        switch (estado) {

            case DownloadManager.STATUS_FAILED:

                msg = "Download falhou.";
                //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Download falhou. Razao: " + razao);
                break;

            case DownloadManager.STATUS_PAUSED:

                msg = "Download parado.";
                //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Download parado. Razao: " + razao);
                break;

            case DownloadManager.STATUS_PENDING:

                msg = "Download pendente";
                break;

            case DownloadManager.STATUS_RUNNING:

                msg = "Download em curso.";
                break;

            case DownloadManager.STATUS_SUCCESSFUL:

                msg = "Download completo";
                break;

            default:
                msg = "Download com estado indefinido";
                break;
        }

        return msg;
    }


    @Override
    protected void terminarExecucao() {

        if(downloadCompleto == true){
            notificacao.atualizarUI(Notificacao.Codigo.CONCLUIR_DOWNLOAD_APK, nomeFicheiro);
        }
        else{
            notificacao.atualizarUI(Notificacao.Codigo.ERRO_DOWNLOAD_APK);
        }
    }
}
