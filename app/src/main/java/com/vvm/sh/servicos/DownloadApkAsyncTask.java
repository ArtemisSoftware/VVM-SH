package com.vvm.sh.servicos;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.R;
import com.vvm.sh.api.modelos.VersaoApp;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.DiretoriasUtil;

import java.io.File;

public class DownloadApkAsyncTask extends AsyncTask<VersaoApp, Void, Void> {


    private DownloadManager downloadManager;
    private long idDownload;
    private boolean downloadCompleto;


    /**
     * Permite enviar mensagens para fora do servico
     */
    private AtualizacaoUI atualizacaoUI;

    private Context contexto;


    public DownloadApkAsyncTask(Context contexto, Handler handler) {

        atualizacaoUI = new AtualizacaoUI(handler);
        this.contexto = contexto;
    }

    @Override
    protected Void doInBackground(VersaoApp... versaoApps) {


        if(versaoApps[0] == null)
            return null;

        VersaoApp versaoApp = versaoApps[0];

        initVariaveisDownload(versaoApp);
        executarDownload();

        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (downloadCompleto == true) {
            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.CONCLUIR_DOWNLOAD_APK);
        }
        else {
            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.ERRO_DOWNLOAD_APK);
        }
    }


    //-------------------------------
    //Metodos locais
    //-------------------------------



    /**
     * Metodo que permite inicar as variaveis necessarias ao download do novo apk
     * @param versaoApp os dados da versao da app
     */
    private void initVariaveisDownload(VersaoApp versaoApp) {

        downloadCompleto = false;

        Uri Download_Uri = Uri.parse(versaoApp.urlDownload);
        String nomeFicheiro = Download_Uri.toString().split("/")[Download_Uri.toString().split("/").length - 1];
        versaoApp.ficheiro = new File(DiretoriasUtil.obterCaminho(DiretoriasUtil.DOWNLOAD), nomeFicheiro);


        if (versaoApp.ficheiro.exists()) {
            versaoApp.ficheiro.delete();
        }

        DownloadManager.Request pedido = new DownloadManager.Request(Uri.parse(versaoApp.urlDownload));
        pedido.setDescription(versaoApp.texto);
        pedido.setTitle(contexto.getString(R.string.atualizacao_v) + versaoApp.versao);
        pedido.setDestinationInExternalPublicDir("/" + DiretoriasUtil.DOWNLOAD, nomeFicheiro);
        pedido.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        pedido.setAllowedOverRoaming(false);

        downloadManager = (DownloadManager) contexto.getSystemService(Context.DOWNLOAD_SERVICE);
        idDownload = downloadManager.enqueue(pedido);
    }


    /**
     * Metodo que permite executar o download do apk
     */
    private void executarDownload() {

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

            if (estado == DownloadManager.STATUS_FAILED) {

                //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Download falhou. Razao: " + cursor.getColumnIndex(DownloadManager.COLUMN_REASON));
                break;
            }

            if (totalBytes != -1 & bytesDescarregados != 0) {

                //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Download: " + bytesDescarregados + " / " + totalBytes);
                atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, obterEstado(cursor), bytesDescarregados, totalBytes);
            }

            cursor.close();
        }

    }




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

                msg = Sintaxe.Frases.DOWNLOAD_FALHOU;
                //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Download falhou. Razao: " + razao);
                break;

            case DownloadManager.STATUS_PAUSED:

                msg = Sintaxe.Frases.DOWNLOAD_PARADO;
                //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Download parado. Razao: " + razao);
                break;

            case DownloadManager.STATUS_PENDING:

                msg = Sintaxe.Frases.DOWNLOAD_PENDENTE;
                break;

            case DownloadManager.STATUS_RUNNING:

                msg = Sintaxe.Frases.DOWNLOAD_EM_CURSO;
                break;

            case DownloadManager.STATUS_SUCCESSFUL:

                msg = Sintaxe.Frases.DOWNLOAD_COMPLETO;
                break;

            default:
                msg = Sintaxe.Frases.DOWNLOAD_INDEFINIDO;
                break;
        }

        return msg;
    }

}
