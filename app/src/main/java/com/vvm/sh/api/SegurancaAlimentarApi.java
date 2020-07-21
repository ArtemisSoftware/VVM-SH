package com.vvm.sh.api;

import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.api.modelos.UtilizadorResposta;
import com.vvm.sh.api.modelos.VersaoApp;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SegurancaAlimentarApi {

    String URL_BASE = "http://www.vivamais.com/tablet_hsa_ws/service.asmx/";



    @GET("{metodo}?dataT=")
    Single<TipoResposta> obterTipo(@Path("metodo") String metodo);

    @GET("Obter_Actualizacoes")
    Single<VersaoApp> obterAtualizacao();


    @GET("GetUtilizadores?dataT=")
    Single<UtilizadorResposta> obterUtilizadores();


    @GET("GetDados")
    Single<SessaoResposta[]> obterTrabalho(@Query("strUser") String idUtilizador);

}
