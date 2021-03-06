package com.vvm.sh.util.metodos;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.core.content.FileProvider;

import com.vvm.sh.BuildConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DiretoriasUtil {

    public static final String DIRETORIA = "vvmsh";
    public static final String DOWNLOAD = DIRETORIA + "/" + "Download";
    public static final String BASE_DADOS = DIRETORIA + File.separator + "BaseDados";
    public static final String DIRETORIA_IMAGENS = DIRETORIA + "/" + "Imagens";
    public static final String DIRETORIA_PDF = DIRETORIA + "/" + "Pdf";
    public static final String DIRETORIA_LOG = DIRETORIA + "/" + "Logs";

    private static String DIRETORIAS []= {DOWNLOAD, DIRETORIA_IMAGENS, DIRETORIA_PDF, BASE_DADOS/*, DIRETORIA_LOG*/};


    /**
     * Metodo que inicia diretorias necessárias ao funcionamento da aplicação
     */
    public static void criarDirectorias(){

        for (String diretoria : DIRETORIAS) {
            criarDirectoria(diretoria);
        }
    }



    /**
     * Metodo que permite criar uma diretoria
     * @param nomeDiretoria nome da directoria que se pretende criar
     * @return true caso a diretoria seja criada com sucesso ou false caso contrario
     */
    public static boolean criarDirectoria(String nomeDiretoria){

        File diretoria = new File(obterCaminho(nomeDiretoria));

        //--LOG--String mensagemLog;
        boolean resultado = false;

        if (diretoria.exists() == false) { // se a directoria não existe deve ser criada

            resultado = diretoria.mkdirs();
            //--LOG--mensagemLog = "Directoria :<" + diretoria.getPath() + "> não existe. Criada: " + resultado;
        }
        else{
            resultado = true;
            //--LOG--mensagemLog = "Directoria :<" + diretoria.getPath() + "> já existe.";
        }

        //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto(mensagemLog);

        return resultado;
    }


    public static boolean criarDirectoria(Activity atividade, String nomeDiretoria){

        File diretoria = obterCaminho(atividade, nomeDiretoria);
        //--LOG--String mensagemLog;
        boolean resultado = false;

        if (diretoria.exists() == false) { // se a directoria não existe deve ser criada

            resultado = diretoria.mkdirs();
            //--LOG--mensagemLog = "Directoria :<" + diretoria.getPath() + "> não existe. Criada: " + resultado;
        }
        else{
            resultado = true;
            //--LOG--mensagemLog = "Directoria :<" + diretoria.getPath() + "> já existe.";
        }

        //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto(mensagemLog);

        return resultado;
    }

    /**
     * Metodo que permite obter o caminho completo para uma diretoria
     * @param diretoria a diretoria pretendida
     * @return um caminho
     */
    public static  File obterCaminho(Activity atividade, String diretoria){
        return atividade.getExternalFilesDir(diretoria);
    }







    /**
     * Metodo que verifica se uma diretoria já existe. Caso nao exista irá criá-la
     * @param ficheiro o nome do ficheiro
     * @return true caso a diretoria já exista false caso tenha ocorrido um erro ao criar a diretoria
     */
    public static boolean verificarDiretoria(File ficheiro){

        if(ficheiro.getParentFile().exists() == false){

            return criarDirectoria(ficheiro.getParentFile().getAbsolutePath());
        }

        return true;
    }


    /**
     * Metodo que permite obter o uri de um ficheiro
     * @param contexto
     * @param ficheiro o ficheiro
     * @return um uri
     */
    public static Uri obterUri(Context contexto, File ficheiro){

        Uri uri;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            uri = FileProvider.getUriForFile(contexto, BuildConfig.APPLICATION_ID + ".provider", ficheiro);
        }
        else{
            uri = Uri.fromFile(ficheiro);
        }

        return uri;
    }


    /**
     * Metodo que permite obter o caminho completo para uma diretoria
     * @param diretoria a diretoria pretendida
     * @return um caminho
     */
    public static  String obterCaminho(String diretoria){

        if(diretoria.contains("/storage/emulated/0/") == true){
            return diretoria;
        }
        return Environment.getExternalStorageDirectory().toString() + "/" + diretoria;
    }

    /**
     * Metodo que permite obter uma diretoria
     * @param diretoria o nome da diretoria
     * @return um File
     */
    public static File obterDiretoria(String diretoria){
        return new File(obterCaminho(diretoria));
    }



    /**
     *  Metodo que pesquisa uma diretoria e retorna uma lista das suas sub diretorias
     * @param diretoria
     * @return
     */
    public static List<String> getDirectoryPaths(String diretoria){
        List<String> pathArray = new ArrayList<>();
        File file = null;
        File[] listfiles = null;
        try{
            file = new File(diretoria);
            listfiles = file.listFiles();
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            for(int i = 0; i < listfiles.length; i++){
                if(listfiles[i].isDirectory()){
                    pathArray.add(listfiles[i].getAbsolutePath());
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return pathArray;
    }


    public static List<String> getFilePaths(String directory){
        List<String> pathArray = new ArrayList<>();
        File file = null;
        File[] listfiles = null;
        try{
            file = new File(directory);
            listfiles = file.listFiles();
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            for(int i = 0; i < listfiles.length; i++){
                if(listfiles[i].isFile()){
                    pathArray.add(listfiles[i].getAbsolutePath());
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return pathArray;
    }

}
