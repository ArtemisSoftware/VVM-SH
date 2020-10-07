package com.vvm.sh.api.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vvm.sh.BuildConfig;
import com.vvm.sh.util.constantes.Url;

import java.io.File;
import java.util.Arrays;

public class VersaoApp {


    @SerializedName("emTeste")
    private boolean atualizacaoTeste;


    @SerializedName("versaoTeste")
    private String versaoTeste;

    @SerializedName("textoTeste")
    private String [] textoTeste;

    @SerializedName("utilizadoresTeste")
    private String [] utilizadoresTeste;



    @SerializedName("versaoProducao")
    private String versaoProducao;

    @SerializedName("textoProducao")
    private String [] textoProducao;






    @Expose(serialize = false)
    public String versao;

    @Expose(serialize = false)
    public String [] texto;

    @Expose(serialize = false)
    public boolean atualizar;


    @Expose(serialize = false)
    public String urlDownload;

    @Expose(serialize = false)
    public File ficheiro;


    /**
     * Metodo que permite fixar o identificador  utilizador
     * <br>
     * A versão da aplicação pode mudar consuante o utilizador
     * @param idUtilizador o identificador do utilizador
     */
    public void fixarUtilizador(String idUtilizador){

        atualizar = false;

        if(idUtilizador != null) {

            if (Arrays.asList(utilizadoresTeste).contains(idUtilizador) == true & atualizacaoTeste == true) {

                versao = versaoTeste + " Teste";
                texto = textoTeste;
                urlDownload = Url.URL_DOWNLOAD_APP_TESTE;
            }
            else {
                versao = versaoProducao;
                texto = textoProducao;
                urlDownload = Url.URL_DOWNLOAD_APP_PRODUCAO;
            }
        }
        else{
            versao = versaoProducao;
            texto = textoProducao;
            urlDownload = Url.URL_DOWNLOAD_APP_PRODUCAO;
        }

        try{

            int versaoInt = Integer.parseInt(versao.replace( " Teste", "").replace(".", ""));
            int versaoAtual = Integer.parseInt(BuildConfig.VERSION_NAME.replace(".", ""));

            if(versaoInt > versaoAtual){
                atualizar = true;
            }
            else{
                texto = new String[]{ "A aplicação já se encontra atualizada."};
            }
        }
        catch(NumberFormatException e){	}
    }





}
