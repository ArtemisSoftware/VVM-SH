package com.vvm.sh.util.interceptores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vvm.sh.api.modelos.Codigo;
import com.vvm.sh.baseDados.entidades.Tarefa;

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



        try {

            int inicio = respostaWS.indexOf(">[");

            if(inicio == -1){
                inicio = respostaWS.indexOf( '{' );
            }

            int fim = respostaWS.indexOf( "</string>" );
            String conteudo = respostaWS.substring(inicio, fim);

            Gson gson = new GsonBuilder().create();
            Codigo codigo = gson.fromJson(conteudo, Codigo.class);

            validarCodigo(codigo);


            JSONObject resposta = new JSONObject(conteudo);
            resposta.put("metodo", metodo);
            return resposta.toString();

        }
        catch (JSONException e) {
            e.printStackTrace();
        }


        return "respostaWS.substring(inicio, fim)";

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

    private void validarCodigo(Codigo codigo) {


        throw new NullPointerException("lolo");
    }


    /**
     * Metodo que permite descodificar erros n?o declarados
     * @param respostaWS a mensagem recebida de uma comunica??o
     * @return um codigo de erro n?o declarado
     */
    private static JSONObject descodificarErro(String respostaWS){

        JSONObject resposta = new JSONObject();

//        try {
//
//            if(respostaWS.equals(WebServiceComIF.MSG_501) == true){ //Connection reset by peer
//                resposta.put(JsonIF.codigo, WebServiceComIF.CODIGO_501);
//            }
//
//            else if(respostaWS.contains("Connect to") == true){ //Connect to ip timed out
//                resposta.put(JsonIF.codigo, WebServiceComIF.CODIGO_502);
//            }
//
//            else if(respostaWS.contains("HTTP Error 503. The service is unavailable.") == true){ //servidor indispon?vel
//                resposta.put(JsonIF.codigo, WebServiceComIF.CODIGO_505);
//            }
//
//            else if(respostaWS.contains("<html>") == true || respostaWS.contains("<!DOCTYPE HTML>") == true){ //html
//                resposta.put(JsonIF.codigo, WebServiceComIF.CODIGO_503);
//            }
//
//
//            else if(respostaWS.equals("null")){ //null
//                resposta.put(JsonIF.codigo, WebServiceComIF.CODIGO_504);
//            }
//
//
//            else{ //n?o identificado
//                resposta.put(JsonIF.codigo, WebServiceComIF.CODIGO_500);
//            }
//        }
//        catch (JSONException e) {
//            try {
//                resposta.put(JsonIF.codigo, WebServiceComIF.CODIGO_500);
//            }
//            catch (JSONException e1) {
//                e1.printStackTrace();
//            }
//        }
//        catch (NullPointerException e) {
//            try {
//                resposta.put(JsonIF.codigo, WebServiceComIF.CODIGO_500);
//            }
//            catch (JSONException e1) {
//                e1.printStackTrace();
//            }
//        }

        return resposta;
    }


}
