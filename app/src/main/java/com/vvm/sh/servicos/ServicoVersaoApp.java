package com.vvm.sh.servicos;

import com.vvm.sh.util.constantes.WebService;

public class ServicoVersaoApp extends ServicoComunicacao {

    @Override
    protected Datagrama obterDatagrama() {
        return new Datagrama(WebService.METODO_Obter_Actualizacoes);
    }
}
