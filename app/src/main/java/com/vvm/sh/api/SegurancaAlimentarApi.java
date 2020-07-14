package com.vvm.sh.api;

import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.api.modelos.VersaoApp;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface SegurancaAlimentarApi {

    String URL_BASE = "http://www.vivamais.com/tablet_hsa_ws/service.asmx/";


    @GET("GetCrossSellingProdutos?dataT=")
    Single<TipoResposta> obterCrossSellingProdutos();


    @GET("Obter_Actualizacoes")
    Single<VersaoApp> obterAtualizacao();

}
