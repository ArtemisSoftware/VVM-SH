package com.vvm.sh.api;

import com.vvm.sh.api.modelos.envio.InfoSSTBody;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.api.modelos.pedido.ICodigoEquipamento;
import com.vvm.sh.api.modelos.pedido.IContagemTipoMaquina;
import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavelListagem;
import com.vvm.sh.api.modelos.pedido.ITipoChecklist;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrLevantamentoListagem;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRisco;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRiscoListagem;
import com.vvm.sh.api.modelos.pedido.IUtilizadorListagem;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
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
        put(Sintaxe.API.METODO_INTERNO, Sintaxe.API.DESCRICAO_METODO_INTERNO_TIPO);
    }};

    Map<String, String> HEADER_EQUIPAMENTO  = new HashMap<String, String>() {{
        put(Sintaxe.API.API, Identificadores.App.APP_ST + "");
        put(Sintaxe.API.NOME_API, SegurancaTrabalhoApi.class.getName());
        put(Sintaxe.API.METODO_INTERNO, Sintaxe.API.DESCRICAO_METODO_INTERNO_EQUIPAMENTO);
    }};


    @GET("{metodo}?dataT=")
    Single<ITipoListagem> obterTipo(@HeaderMap Map<String, String> headers, @Path("metodo") String metodo);


    @GET("{metodo}")
    Single<ITipoListagem> obterTipo(@HeaderMap Map<String, String> headers, @Path("metodo") String metodo, @Query("dataT") String seloTemporal);





    @GET("GetActivPlaneaveis?dataT=")
    Single<ITipoAtividadePlaneavelListagem> obterTipoAtividadesPlaneaveis(@HeaderMap Map<String, String> headers);

    @GET("GetActivPlaneaveis")
    Single<ITipoAtividadePlaneavelListagem> obterTipoAtividadesPlaneaveis(@HeaderMap Map<String, String> headers, @Query("dataT") String seloTemporal);





    @GET("ObterTemplatesAVR_Levantamentos?dataT=")
    Single<ITipoTemplateAvrLevantamentoListagem> obterTipoTemplatesAVR_Levantamentos(@HeaderMap Map<String, String> headers);

    @GET("ObterTemplatesAVR_Levantamentos")
    Single<ITipoTemplateAvrLevantamentoListagem> obterTipoTemplatesAVR_Levantamentos(@HeaderMap Map<String, String> headers, @Query("dataT") String seloTemporal);

    @GET("ObterTemplatesAVR_Riscos?dataT=")
    Single<ITipoTemplateAvrRiscoListagem> obterTipoTemplatesAVR_Riscos(@HeaderMap Map<String, String> headers);

    @GET("ObterTemplatesAVR_Riscos")
    Single<ITipoTemplateAvrLevantamentoListagem> obterTipoTemplatesAVR_Riscos(@HeaderMap Map<String, String> headers, @Query("dataT") String seloTemporal);




    @GET("GetUtilizadores?dataT=")
    Single<IUtilizadorListagem> obterUtilizadores(@HeaderMap Map<String, String> headers);



    @GET("GetDados")
    Single<ISessao> obterTrabalho(@HeaderMap Map<String, String> headers, @Query("strUser") String idUtilizador);


    @GET("GetDadosDia")
    Single<ISessao> obterTrabalho(@HeaderMap Map<String, String> headers, @Query("strUser") String idUtilizador, @Query("strDia") String data);



    @GET("GetCheckListNovo")
    Single<ITipoChecklist> obterChecklist(@HeaderMap Map<String, String> headers, @Query("IdTipoCheckList") String id);


    @GET("ObterContagemTiposMaquina")
    Single<IContagemTipoMaquina> obterContagemTiposMaquinas(@HeaderMap Map<String, String> headers, @Query("utilizador") String id);


    @GET("ObterEstadoEquipamento")
    Single<ICodigoEquipamento> obterEstadoEquipamento(@HeaderMap Map<String, String> headers, @Query("descricao") String descricao);


    @FormUrlEncoded
    @POST("ProcessarDados")
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


    @FormUrlEncoded
    @POST("upLoadInfoSST")
    Single<Codigo> submeterInfoSST(@HeaderMap Map<String, String> headers,
                                    @Field("strJsonString") String dados,
                                    @Field("ordem") String ordem,
                                    @Field("marca") String marca);


}
