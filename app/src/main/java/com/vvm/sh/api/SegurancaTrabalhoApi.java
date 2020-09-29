package com.vvm.sh.api;

import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.api.modelos.pedido.ITipoChecklist;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.pedido.IUtilizadorListagem;
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


    @GET("GetActivPlaneaveis?dataT=")
    Single<ITipoListagem> obterTipoAtividadesPlaneaveis(@HeaderMap Map<String, String> headers);

    @GET("GetActivPlaneaveis")
    Single<ITipoListagem> obterTipoAtividadesPlaneaveis(@HeaderMap Map<String, String> headers, @Query("dataT") String seloTemporal);



    @GET("GetUtilizadores?dataT=")
    Single<IUtilizadorListagem> obterTipo(@HeaderMap Map<String, String> headers);



    @GET("GetDados")
    Single<ISessao> obterTrabalho(@HeaderMap Map<String, String> headers, @Query("strUser") String idUtilizador);


    @GET("GetDadosDia")
    Single<ISessao> obterTrabalho(@HeaderMap Map<String, String> headers, @Query("strUser") String idUtilizador, @Query("strDia") String data);



    @GET("GetCheckListNovo")
    Single<ITipoChecklist> obterChecklist(@HeaderMap Map<String, String> headers, @Query("IdTipoCheckList") String id);

}
