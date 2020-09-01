package com.vvm.sh.api;

import com.vvm.sh.api.modelos.Codigo;
import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.api.modelos.UtilizadorResposta;
import com.vvm.sh.api.modelos.VersaoApp;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SegurancaAlimentarApi {

    String URL_BASE = "http://www.vivamais.com/tablet_hsa_ws/service.asmx/";



    @GET("{metodo}?dataT=")
    Single<TipoResposta> obterTipo(@Path("metodo") String metodo);

    @GET("{metodo}")
    Single<TipoResposta> obterTipo(@Path("metodo") String metodo, @Query("dataT") String seloTemporal);

    @GET("Obter_Actualizacoes")
    Single<VersaoApp> obterAtualizacao();


    @GET("GetUtilizadores?dataT=")
    Single<UtilizadorResposta> obterUtilizadores();


    @GET("GetDados")
    Single<SessaoResposta> obterTrabalho(@Query("strUser") String idUtilizador);


    @GET("GetDadosDia")
    Single<SessaoResposta> obterTrabalho(@Query("strUser") String idUtilizador, @Query("strDia") String data);


    @POST("ProcessWorkUpdate")
    Single<Codigo> submeterDados(@Query("strJsonString") String dados, @Query("user") String idUtilizador,
                                 @Query("idUnico") String id, @Query("MessageDigest") String messageDigest);

    @POST("ProcessarFotos")
    Single<Codigo> submeterImagens(@Query("strJsonString") String dados, @Query("user") String idUtilizador,
                                   @Query("idUnico") String id, @Query("numeroFicheiro") String numeroFicheiro, @Query("MessageDigest") String messageDigest);
}
