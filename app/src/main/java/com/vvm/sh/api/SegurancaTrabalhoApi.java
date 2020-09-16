package com.vvm.sh.api;

import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SegurancaTrabalhoApi {


    String URL_BASE = "http://www.vivamais.com/tablet_ws/service.asmx/";


    Map<String, String> HEADER  = new HashMap<String, String>() {{
        put(Sintaxe.API.API, Identificadores.App.APP_ST + "");
        put(Sintaxe.API.NOME_API, SegurancaTrabalhoApi.class.getName());
    }};

    Map<String, String> HEADER_TIPO  = new HashMap<String, String>() {{
        put(Sintaxe.API.API, Identificadores.App.APP_ST + "");
        put(Sintaxe.API.NOME_API, SegurancaTrabalhoApi.class.getName());
        put(Sintaxe.API.METODO_INTERNO, "tipo");
    }};


    @GET("{metodo}?dataT=")
    Single<ITipoListagem> obterTipo(@HeaderMap Map<String, String> headers, @Path("metodo") String metodo);


    @GET("{metodo}")
    Single<ITipoListagem> obterTipo(@HeaderMap Map<String, String> headers, @Path("metodo") String metodo, @Query("dataT") String seloTemporal);

}
