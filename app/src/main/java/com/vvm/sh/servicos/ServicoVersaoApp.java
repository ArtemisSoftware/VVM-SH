package com.vvm.sh.servicos;

import android.os.Handler;

import com.google.gson.Gson;
import com.vvm.sh.util.Notificacao;
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

        notificacao.atualizarUI(Notificacao.Codigo.CONCLUIR_PEDIDO_VERSAO_APP, resposta);

    }
}
