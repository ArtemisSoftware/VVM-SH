package com.vvm.sh.servicos;

import android.os.Handler;

import com.google.gson.Gson;
import com.vvm.sh.util.constantes.WebService;

public class ServicoVersaoApp extends ServicoComunicacao {

    public ServicoVersaoApp(Handler handler) {
        super(handler);
    }

    @Override
    protected Datagrama obterDatagrama() {
        return new Datagrama(WebService.METODO_Obter_Actualizacoes);
    }

    @Override
    protected void terminarExecucao() {

        //Gson gson = new Gson();
        //VersaoApp versaoApp = gson.fromJson(this.resposta, VersaoApp.class);

    }
}
