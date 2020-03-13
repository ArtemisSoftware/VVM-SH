package com.vvm.sh.util.metodos;

import android.os.Environment;

import java.io.File;

public class Diretorias {

    public static final String DIRETORIA = "vvmsh";
    public static final String DIRETORIA_DOWNLOAD = DIRETORIA + "/" + "Download";
    public static final String DIRETORIA_BASE_DADOS = DIRETORIA + "/" + "BaseDados";
    public static final String DIRETORIA_IMAGENS = DIRETORIA + "/" + "Imagens";
    public static final String DIRETORIA_PDF = DIRETORIA + "/" + "Pdf";
    public static final String DIRETORIA_LOG = DIRETORIA + "/" + "Logs";

    private static String DIRETORIAS []= {DIRETORIA_DOWNLOAD/*, DIRETORIA_IMAGENS, DIRETORIA_PDF, DIRETORIA_BASE_DADOS, DIRETORIA_LOG*/};


    /**
     * Metodo que inicia diretorias necessárias ao funcionamento da aplicação
     */
    public static void criarDirectorias(){

        for (String diretoria : DIRETORIAS) {
            criarDirectoria(diretoria);
        }
    }


    /**
     * Metodo que cria uma directoria
     * @param nomeDiretoria nome da directoria que se pretende criar
     */
    private static void criarDirectoria(String nomeDiretoria){

        File diretoria = new File(Environment.getExternalStorageDirectory(), nomeDiretoria);

        String mensagemLog;

        if (diretoria.exists() == false) { // se a directoria não existe deve ser criada

            boolean resultado = diretoria.mkdirs();
            mensagemLog = "Directoria :<" + diretoria.getPath() + "> não existe. Criada: " + resultado;
        }
        else{
            mensagemLog = "Directoria :<" + diretoria.getPath() + "> já existe.";
        }

        //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto(mensagemLog);
    }


}
