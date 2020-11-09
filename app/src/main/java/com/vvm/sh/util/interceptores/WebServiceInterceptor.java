package com.vvm.sh.util.interceptores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.api.modelos.pedido.ISessao;
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
        String responseString = "";

        if(metodo.equals("GetDados") == true || metodo.equals("GetDadosDia") == true){
            //String responseString_1 = resposta.peekBody(2048).string();
            responseString = corpo.string();

            String lolo = ",\"AvaliacaoRiscosAnterior\":{\"processoProdutivo\":null,\"equipamentos\":null,\"levantamentosRisco\":null,\"Vulnerabilidades\":null,\"Checklist\":null,\"capaRelatorio\":null,\"RelatorioPlanoAcao\":null,\"RelatorioPlanoAcaoAVR\":null,\"RelatorioPlanoAcaoST\":null}";
            String lolo2 = ",\"AuditoriaAnterior\":{\"idChecklist\":null,\"versaoChecklist\":null,\"dadosChecklist\":null}";
            if(responseString.contains(lolo) == true){

                responseString = responseString.replace(lolo, "");
            }
            if(responseString.contains(lolo2) == true){

                responseString = responseString.replace(lolo2, "");
            }
        }
        else{
            responseString = corpo.string();
        }


        MediaType contentType = corpo.contentType();
        ResponseBody body = ResponseBody.create(obterJSON(/*corpo.string()*/responseString, metodo, api),contentType);
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

            validarConteudo(gson, conteudo);


            JSONObject resposta = new JSONObject(conteudo);
            resposta.put("metodo", metodo);
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
     * Metodo que permite validar o conteudo da resposta do web service
     * @param gson
     * @param conteudo os dados a validar
     * @throws RespostaWsInvalidaException
     */
    private void validarConteudo(Gson gson, String conteudo) throws RespostaWsInvalidaException {

        ISessao sessao = gson.fromJson(conteudo, ISessao.class);

        if(sessao.sessoes != null){
            if(sessao.sessoes.get(0).trabalho.size() == 0){
                throw new RespostaWsInvalidaException(gson.toJson(Codigo_101, Codigo.class));
            }
        }

        Codigo codigo = gson.fromJson(conteudo, Codigo.class);


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
