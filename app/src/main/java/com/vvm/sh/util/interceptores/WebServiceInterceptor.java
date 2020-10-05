package com.vvm.sh.util.interceptores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.excepcoes.MetodoWsInvalidoException;
import com.vvm.sh.util.excepcoes.RespostaWsInvalidaException;
import com.vvm.sh.util.metodos.TiposUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import static com.vvm.sh.util.constantes.Identificadores.CodigosWs.*;

public class WebServiceInterceptor implements Interceptor {


    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request pedido = chain.request();

        String metodo = TiposUtil.obterMetodo(pedido.url().pathSegments(), pedido.header(Sintaxe.API.METODO_INTERNO));



        if(pedido.header(Sintaxe.API.API) == null) {

            Gson gson = new GsonBuilder().create();
            Codigo codigo = new Codigo(Identificadores.CodigosWs.ID_601, Identificadores.CodigosWs.MSG_601);
            throw new MetodoWsInvalidoException(gson.toJson(codigo, Codigo.class));
        }

        int api = Integer.parseInt(pedido.header(Sintaxe.API.API));

        Response resposta = chain.proceed(pedido);
        ResponseBody corpo = resposta.body();

        MediaType contentType = corpo.contentType();
        ResponseBody body = ResponseBody.create(obterJSON(corpo.string(), metodo, api),contentType);
        return resposta.newBuilder().body(body).build();
    }


    /**
     * Metodo que obtem o json da resposta do web service
     * @param respostaWS resposta recebida do web service
     * @param metodo o metodo associado ao pedido
     * @param api o identificador da api
     * @return  um objecto com os dados
     * @throws RespostaWsInvalidaException
     */
    private String obterJSON(String respostaWS, String metodo, int api) throws RespostaWsInvalidaException {

        Gson gson = new GsonBuilder().create();
        String dados = null;

        try {

            int inicio = respostaWS.indexOf(">[");
            int fim = 0;

            if(inicio == -1){
                inicio = respostaWS.indexOf( '{' );
                fim = respostaWS.indexOf( "</string>" );
            }
            else{
                inicio += 2;
                fim = respostaWS.indexOf( "]</string>" );
            }

            String conteudo = respostaWS.substring(inicio, fim);

            Codigo codigo = gson.fromJson(conteudo, Codigo.class);

            validarCodigo(codigo);


            JSONObject resposta = new JSONObject(conteudo);
            resposta.put("metodo", metodo);
            resposta.put("api", api);
            resposta.put("api", api);
            return resposta.toString();

        }
        catch (JSONException e) {
            Codigo codigo = new Codigo(ID_500, e.getMessage());
            throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));
        }
        catch (NullPointerException | StringIndexOutOfBoundsException e) {
            descodificarErro(respostaWS, e.getMessage());
        }

        return dados;
    }


    /**
     * Metodo que permite validar o código da resposta do web service
     * @param codigo codigo a validar
     * @throws RespostaWsInvalidaException
     */
    private void validarCodigo(Codigo codigo) throws RespostaWsInvalidaException {

        switch (codigo.codigo){

//            case ID_100:
//
//                codigo.mensagem = MSG_100;
//                break;

            case CODIGO_101:

                codigo.mensagem = MSG_101;
                break;

            case CODIGO_400:

                codigo.mensagem = MSG_400;
                break;

            case ID_402:

                codigo.mensagem = MSG_402;
                break;

            default:
                break;
        }

        if(codigo.mensagem != null) {

            Gson gson = new GsonBuilder().create();
            throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));
        }
    }



    /**
     * Metodo que permite descodificar erros não declarados
     * @param respostaWS a mensagem recebida de uma comunicacão
     * @param subMensagem mensagem extra a figurar
     * @throws RespostaWsInvalidaException
     */
    private static void descodificarErro(String respostaWS, String subMensagem) throws RespostaWsInvalidaException {

        Gson gson = new GsonBuilder().create();
        Codigo codigo = new Codigo();

        try {

            if(respostaWS.equals(MSG_501) == true){ //Connection reset by peer
                codigo = new Codigo(ID_501, MSG_501);
            }

            else if(respostaWS.contains("Connect to") == true){ //Connect to ip timed out
                codigo = new Codigo(ID_502, MSG_502);
            }

            else if(respostaWS.contains("HTTP Error 503. The service is unavailable.") == true){ //servidor indisponivel
                codigo = new Codigo(ID_503, MSG_503);
            }

            else if(respostaWS.contains("<html>") == true || respostaWS.contains("<!DOCTYPE HTML>") == true){ //html
                codigo = new Codigo(ID_503, MSG_503);
            }

            else if(respostaWS.equals("null")){ //null
                codigo = new Codigo(ID_504, MSG_504);
            }

            else{ //nao identificado
                codigo = new Codigo(ID_500, MSG_500 + "\n " + subMensagem);
            }

            throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));

        }
        catch (NullPointerException e) {
            codigo = new Codigo(ID_500, MSG_500 + "\n " + e.getMessage());
            throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));
        }

    }


}
