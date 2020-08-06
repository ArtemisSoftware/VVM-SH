package com.vvm.sh.util.interceptores;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WebServiceInterceptor implements Interceptor {


    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {


        Request pedido   = chain.request();

        String metodo = "";
        try {

            metodo  = pedido.url().pathSegments().get(2);
        }
        catch(IndexOutOfBoundsException e){}

        Response resposta = chain.proceed(pedido);
        ResponseBody corpo = resposta.body();





        MediaType contentType = corpo.contentType();
        ResponseBody body = ResponseBody.create(obterJSON(corpo.string(), metodo),contentType);
        return resposta.newBuilder().body(body).build();
    }



    /**
     * Metodo que obtem o json da resposta do web service
     * @param respostaWS resposta recebida do web service
     * @return um objecto com os dados
     */
    private String obterJSON(String respostaWS, String metodo){


//        try {

            int inicio = respostaWS.indexOf(">[");

            if(inicio == -1){
                inicio = respostaWS.indexOf( '{' );
            }


            int fim = respostaWS.indexOf( "</string>" );
            //return respostaWS.substring(inicio + 1, fim - 1);


        try {
            JSONObject resposta = new JSONObject(respostaWS.substring(inicio, fim));
            resposta.put("metodo", metodo);
            return resposta.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return respostaWS.substring(inicio, fim);

//        }
//
//        catch (NullPointerException e) {
//            dados = descodificarErro(respostaWS);
//        }
//        catch (StringIndexOutOfBoundsException e) {
//            dados = descodificarErro(respostaWS);
//        }
//
//
//        return dados;
    }




}
