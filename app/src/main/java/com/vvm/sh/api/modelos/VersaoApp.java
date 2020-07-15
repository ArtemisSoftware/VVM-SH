package com.vvm.sh.api.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vvm.sh.BuildConfig;
import com.vvm.sh.util.constantes.Url;

import java.io.File;
import java.util.Arrays;

public class VersaoApp {

    @SerializedName("versaoApp")
    private String versaoTeste;

    @SerializedName("Texto")
    private String textoTeste;

    @SerializedName("versaoProducao")
    private String versaoProducao;

    @SerializedName("textoProducao")
    private String textoProducao;


    @SerializedName("versaoTeste")
    private boolean atualizacaoTeste;

    @SerializedName("utilizadoresTeste")
    private String [] utilizadoresTeste;


    @Expose(serialize = false)
    public String versao;

    @Expose(serialize = false)
    public String texto;

    @Expose(serialize = false)
    public boolean atualizar;


    @Expose(serialize = false)
    private String urlDownload;

    private File ficheiro;


    public VersaoApp(String versaoTeste, String textoTeste, String versaoProducao, String textoProducao,
                     String [] utilizadoresTeste, boolean atualizacaoTeste){


        this.versaoTeste = versaoTeste;
        this.textoTeste = textoTeste;

        this.versaoProducao = versaoProducao;
        this.textoProducao = textoProducao;

        this.utilizadoresTeste = utilizadoresTeste;
        this.atualizacaoTeste = atualizacaoTeste;
        //this.VERSAO_APP_TESTE = Boolean.parseBoolean(versaoTeste);

    }


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
                texto = "A aplicação já se encontra atualizada.";
            }
        }
        catch(NumberFormatException e){	}
    }


    /**
     * Metodo que permite obter a versão para a qual a aplicação será atualizada
     * @return a versao
     */
    public String obterVersao(){
        return versao;
    }


    /**
     * Metodo que permite obter o texto de atualização
     * @return o texto
     */
    public String obterTexto(){
        return texto;
    }


    /**
     * Metodo que indica se a aplicação pode atualizar
     * @return true caso possa atualizar ou false caso contrario
     */
    public boolean atualizar(){
        return atualizar;
    }


    /**
     * Metodo que permite obter o url para download da versao
     * @return o url
     */
    public String obterUrlDownload(){
        return urlDownload;
    }


    /**
     * Metodo que permite fixar o ficheiro da versao da app
     * @param ficheiro o ficheiro da versao da app
     */
    public void fixarFicheiro(File ficheiro){
        this.ficheiro = ficheiro;
    }

    /**
     * Metodo que permite obter o ficheiro da versao da app
     * @return um ficheiro
     */
    public File obterFicheiro(){
        return ficheiro;
    }

}
