package com.vvm.sh.api;

import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.api.modelos.pedido.IUtilizadorListagem;
import com.vvm.sh.api.modelos.VersaoApp;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SegurancaAlimentarApi {

    String URL_BASE = "http://www.vivamais.com/tablet_hsa_ws/service.asmx/";

    Map<String, String> HEADER  = new HashMap<String, String>() {{
        put(Sintaxe.API.API, Identificadores.App.APP_SA + "");
        put(Sintaxe.API.NOME_API, SegurancaAlimentarApi.class.getName());
    }};

    Map<String, String> HEADER_TIPO  = new HashMap<String, String>() {{
        put(Sintaxe.API.API, Identificadores.App.APP_SA + "");
        put(Sintaxe.API.NOME_API, SegurancaAlimentarApi.class.getName());
        put(Sintaxe.API.METODO_INTERNO, "tipo");
    }};


    @GET("{metodo}?dataT=")
    Single<ITipoListagem> obterTipo(@HeaderMap Map<String, String> headers, @Path("metodo") String metodo);


    @GET("{metodo}")
    Single<ITipoListagem> obterTipo(@HeaderMap Map<String, String> headers, @Path("metodo") String metodo, @Query("dataT") String seloTemporal);


    @GET("Obter_Actualizacoes")
    Single<VersaoApp> obterAtualizacao(@HeaderMap Map<String, String> headers);


    @GET("GetUtilizadores?dataT=")
    Single<IUtilizadorListagem> obterUtilizadores(@HeaderMap Map<String, String> headers);


    @GET("GetDados")
    Single<ISessao> obterTrabalho(@HeaderMap Map<String, String> headers, @Query("strUser") String idUtilizador);


    @GET("GetDadosDia")
    Single<ISessao> obterTrabalho(@HeaderMap Map<String, String> headers, @Query("strUser") String idUtilizador, @Query("strDia") String data);


    @FormUrlEncoded
    @POST("ProcessWorkUpdate")
    Single<Codigo> submeterDados(
            @HeaderMap Map<String, String> headers,
            @Field("strJsonString") String dados,
            @Field("user") String idUtilizador,
            @Field("idUnico") String id,
            @Field("MessageDigest") String messageDigest
    );


    @FormUrlEncoded
    @POST("ProcessarFotos")
    Single<Codigo> submeterImagens(
            @HeaderMap Map<String, String> headers,
            @Field("strJsonString") String dados,
            @Field("user") String idUtilizador,
            @Field("idUnico") String id,
            @Field("numeroFicheiro") String numeroFicheiro,
            @Field("MessageDigest") String messageDigest
    );



//    @POST("ProcessWorkUpdate")
//    Single<Codigo> submeterDados(@Query("strJsonString") String dados, @Query("user") String idUtilizador,
//                                 @Query("idUnico") String id, @Query("MessageDigest") String messageDigest);

//    @POST("ProcessarFotos")
//    Single<Codigo> submeterImagens(@Query("strJsonString") String dados, @Query("user") String idUtilizador,
//                                   @Query("idUnico") String id, @Query("numeroFicheiro") String numeroFicheiro, @Query("MessageDigest") String messageDigest);
}
