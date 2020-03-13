package com.vvm.sh.servicos;

import com.google.gson.annotations.SerializedName;

public class VersaoApp {

    //{"versaoTeste":true,"versaoApp":"1.42.27","utilizadoresTeste":["500005"],"Texto":"Existe uma nova versão da aplicação.++Bug:++-++","versaoProducao":"1.42.22","textoProducao":"Existe uma nova versão da aplicação.++Bug:++-++"}

    @SerializedName("versaoApp")
    private String versaoTeste;


    public VersaoApp(String versaoTeste/*ArrayList<String> idsUtilizadoresTeste, , String versaoAppTeste, String textoTeste,
                  String versaoAppProducao, String textoProducao,
                  String versaoAtualApp*/){


        this.versaoTeste = versaoTeste;
        /*
        this.idsUtilizadoresTeste = idsUtilizadoresTeste;
        this.VERSAO_APP_TESTE = Boolean.parseBoolean(versaoTeste);

        this.textoTeste = textoTeste;

        this.versaoAppProducao = versaoAppProducao;
        this.textoProducao = textoProducao;

        this.versaoAtualApp = versaoAtualApp;
        */
    }

}
