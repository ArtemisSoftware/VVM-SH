package com.vvm.sh.servicos.ftp;

import android.content.Context;
import android.os.AsyncTask;

import com.vvm.sh.util.constantes.FtpConfig;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.MensagensUtil;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class EnvioFtpAsyncTask extends AsyncTask<String, String, Void> {

    private boolean resultado = false;


    public String erro = null;
    private MensagensUtil dialogo;


    public EnvioFtpAsyncTask(Context contexto) {
        dialogo = new MensagensUtil(contexto);
    }



    @Override
    protected void onPreExecute() {
        dialogo.progresso(true, Sintaxe.Frases.ENVIAR_FICHEIRO_FTP);
    }





    @Override
    protected Void doInBackground(String... strings) {

        String caminho = strings[0];
        String idUtilizador = strings[1];

        String nomeFicheiro = caminho.split("/")[caminho.split("/").length - 1];
        nomeFicheiro = nomeFicheiro.replace(".", "__tecnico_"+ idUtilizador + "_" + DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_FTP) + ".");


        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(FtpConfig.SERVIDOR, FtpConfig.PORTO);
            ftpClient.login(FtpConfig.UTILIZADOR, FtpConfig.PALAVRA_CHAVE);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(caminho);

            String firstRemoteFile = nomeFicheiro;
            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                resultado = true;
                System.out.println("The first file is uploaded successfully.");
            }

            inputStream.close();

            // APPROACH #2: uploads second file using an OutputStream
//            File secondLocalFile = new File("E:/Test/Report.doc");
//            String secondRemoteFile = "test/Report.doc";
//            inputStream = new FileInputStream(secondLocalFile);
//
//            System.out.println("Start uploading second file");
//            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
//            byte[] bytesIn = new byte[4096];
//            int read = 0;
//
//            while ((read = inputStream.read(bytesIn)) != -1) {
//                outputStream.write(bytesIn, 0, read);
//            }
//            inputStream.close();
//            outputStream.close();

//            boolean completed = ftpClient.completePendingCommand();
//            if (completed) {
//                System.out.println("The second file is uploaded successfully.");
//            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        dialogo.terminarProgresso();

        if(erro != null){
            dialogo.erro(Sintaxe.Alertas.PROBLEMA_EMAIL  + erro);
        }
        else{
            dialogo.sucesso(Sintaxe.Frases.DADOS_ENVIADOS_SUCESSO);
        }
    }

}
