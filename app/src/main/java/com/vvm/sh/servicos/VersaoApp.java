package com.vvm.sh.servicos;

import com.google.gson.annotations.SerializedName;

public class VersaoApp {

    //{"versaoTeste":true,"utilizadoresTeste":["500005"]"}

    @SerializedName("versaoApp")
    private String versaoTeste;

    @SerializedName("Texto")
    private String textoTeste;

    @SerializedName("versaoProducao")
    private String versaoProducao;

    @SerializedName("textoProducao")
    private String textoProducao;


    public VersaoApp(String versaoTeste, String textoTeste, String versaoProducao, String textoProducao
                /*ArrayList<String> idsUtilizadoresTeste, , String versaoAppTeste,
                  ,
                  String versaoAtualApp*/){


        this.versaoTeste = versaoTeste;
        this.textoTeste = textoTeste;

        this.versaoProducao = versaoProducao;
        this.textoProducao = textoProducao;
        /*
        this.idsUtilizadoresTeste = idsUtilizadoresTeste;
        this.VERSAO_APP_TESTE = Boolean.parseBoolean(versaoTeste);






        this.versaoAtualApp = versaoAtualApp;
        */
    }

}
