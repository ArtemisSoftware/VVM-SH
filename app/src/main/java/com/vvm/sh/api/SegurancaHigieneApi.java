package com.vvm.sh.api;

import com.vvm.sh.api.modelos.pedido.IVersaoApp;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface SegurancaHigieneApi {

    String URL_BASE = "http://www.vivamais.com/VVM_SH/service.asmx/";

    Map<String, String> HEADER  = new HashMap<String, String>() {{
        put(Sintaxe.API.API, Identificadores.App.APP_SH + "");
        put(Sintaxe.API.NOME_API, SegurancaHigieneApi.class.getName());
    }};


    @GET("Obter_Actualizacoes")
    Single<IVersaoApp> obterAtualizacao(@HeaderMap Map<String, String> headers);

}
