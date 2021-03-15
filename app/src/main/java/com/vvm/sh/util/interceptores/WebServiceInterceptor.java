package com.vvm.sh.util.interceptores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.excepcoes.MetodoWsInvalidoException;
import com.vvm.sh.util.excepcoes.RespostaWsInvalidaException;
import com.vvm.sh.util.metodos.TiposUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import static com.vvm.sh.util.constantes.Identificadores.CodigosWs.*;

public class WebServiceInterceptor implements Interceptor {


    private Gson gson = new GsonBuilder().create();

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request pedido = chain.request();

        if(pedido.header(Sintaxe.API.API) == null) {
            throw new MetodoWsInvalidoException(gson.toJson(Codigo_601, Codigo.class));
        }

        String metodo = TiposUtil.obterMetodo(pedido.url().pathSegments(), pedido.header(Sintaxe.API.METODO_INTERNO));
        int api = Integer.parseInt(pedido.header(Sintaxe.API.API));

        Response resposta = chain.proceed(pedido);
        ResponseBody corpo = resposta.body();


        MediaType contentType = corpo.contentType();
        ResponseBody body = ResponseBody.create(formatarResposta(corpo.string(), metodo, pedido.headers(), api),contentType);
        return resposta.newBuilder().body(body).build();
    }


    private String formatarResposta(String respostaWS, String metodo, Headers cabecalho, int api) throws RespostaWsInvalidaException {

        String conteudo = obterJson(respostaWS);

        int codigo = validarConteudo(conteudo);

        conteudo = formatarTrabalho(metodo, conteudo);
        conteudo = formatarEquipamentos(cabecalho, conteudo);


        try {

            JSONObject resposta = new JSONObject(conteudo);
            resposta.put("metodo", metodo);
            resposta.put("api", api);
            resposta.put("codigo", codigo);
            return resposta.toString();
        }
        catch (JSONException e) {
            throw new RespostaWsInvalidaException(gson.toJson(new Codigo(ID_500, e.getMessage()), Codigo.class));
        }
    }


    private String obterJson(String respostaWS) throws RespostaWsInvalidaException {

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
            validarConteudo(conteudo);

            return conteudo;

        }
        catch (NullPointerException | StringIndexOutOfBoundsException e) {
            descodificarErro(respostaWS, e.getMessage());
        }

        return dados;
    }


    /**
     * Metodo que permite validar o conteudo da resposta do web service
     * @param conteudo os dados a validar
     * @throws RespostaWsInvalidaException
     */
    private int validarConteudo(String conteudo) throws RespostaWsInvalidaException {

        ISessao sessao = gson.fromJson(conteudo, ISessao.class);

       if(sessao.sessoes != null){
            if(sessao.sessoes.get(0).trabalho.size() == 0){
                return ID_101;
            }
       }


        Codigo codigo = gson.fromJson(conteudo, Codigo.class);

        switch (codigo.codigo){

            case ID_400:

                codigo.mensagem = MSG_400;
                break;

            case ID_402:

                codigo.mensagem = MSG_402;
                break;

            default:
                return codigo.codigo;

        }

        if(codigo.mensagem != null) {
            throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));
        }

        return codigo.codigo;
    }


    private String formatarTrabalho(String metodo, String responseString) {

        if (metodo.equals("GetDados") == true || metodo.equals("GetDadosDia") == true) {

            String lolo = ",\"AvaliacaoRiscosAnterior\":{\"processoProdutivo\":null,\"equipamentos\":null,\"levantamentosRisco\":null,\"Vulnerabilidades\":null,\"Checklist\":null,\"capaRelatorio\":null,\"RelatorioPlanoAcao\":null,\"RelatorioPlanoAcaoAVR\":null,\"RelatorioPlanoAcaoST\":null}";
            String lolo2 = ",\"AuditoriaAnterior\":{\"idChecklist\":null,\"versaoChecklist\":null,\"dadosChecklist\":null}";
            if (responseString.contains(lolo) == true) {

                responseString = responseString.replace(lolo, "");
            }
            if (responseString.contains(lolo2) == true) {

                responseString = responseString.replace(lolo2, "");
            }

            try {
                Codigo codigo = gson.fromJson(responseString, Codigo.class);
                if(codigo.codigo != 0) {
                    responseString = (new JSONObject()).toString();
                }
            } catch (JsonSyntaxException e) {

            }
        }

        return responseString;
    }


    private String formatarEquipamentos(Headers cabecalho, String conteudo) {

        try {
            if (cabecalho.get(Sintaxe.API.METODO_INTERNO).equals(Sintaxe.API.DESCRICAO_METODO_INTERNO_EQUIPAMENTO) == true) {

                JSONObject resposta = new JSONObject(conteudo);
                resposta.put("idProvisorioEquipamento", cabecalho.get(Sintaxe.API.ID_EQUIPAMENTO_PROVISORIO));
                conteudo = resposta.toString();
            }
        }
        catch (NullPointerException | JSONException e){}

        return conteudo;
    }



    //    @NotNull
//    @Override
//    public Response intercept(@NotNull Chain chain) throws IOException {
//
//        Request pedido = chain.request();
//
//        if(pedido.header(Sintaxe.API.API) == null) {
//            throw new MetodoWsInvalidoException(gson.toJson(Codigo_601, Codigo.class));
//        }
//
//        String metodo = TiposUtil.obterMetodo(pedido.url().pathSegments(), pedido.header(Sintaxe.API.METODO_INTERNO));
//        int api = Integer.parseInt(pedido.header(Sintaxe.API.API));
//
//        Response resposta = chain.proceed(pedido);
//        ResponseBody corpo = resposta.body();
//        String responseString = formatarResposta(metodo, corpo.string());
//
//
//        MediaType contentType = corpo.contentType();
//        ResponseBody body = ResponseBody.create(obterJSON(responseString, metodo, pedido.headers(), api),contentType);
//        return resposta.newBuilder().body(body).build();
//    }
//
//
//    private String formatarResposta(String metodo, String responseString){
//
//        if(metodo.equals("GetDados") == true || metodo.equals("GetDadosDia") == true){
//
//            String lolo = ",\"AvaliacaoRiscosAnterior\":{\"processoProdutivo\":null,\"equipamentos\":null,\"levantamentosRisco\":null,\"Vulnerabilidades\":null,\"Checklist\":null,\"capaRelatorio\":null,\"RelatorioPlanoAcao\":null,\"RelatorioPlanoAcaoAVR\":null,\"RelatorioPlanoAcaoST\":null}";
//            String lolo2 = ",\"AuditoriaAnterior\":{\"idChecklist\":null,\"versaoChecklist\":null,\"dadosChecklist\":null}";
//            if(responseString.contains(lolo) == true){
//
//                responseString = responseString.replace(lolo, "");
//            }
//            if(responseString.contains(lolo2) == true){
//
//                responseString = responseString.replace(lolo2, "");
//            }
//        }
//
//        return responseString;
//    }
//
//
//    /**
//     * Metodo que obtem o json da resposta do web service
//     * @param respostaWS resposta recebida do web service
//     * @param metodo o metodo associado ao pedido
//     * @param api o identificador da api
//     * @return  um objecto com os dados
//     * @throws RespostaWsInvalidaException
//     */
//    private String obterJSON(String respostaWS, String metodo, Headers cabecalho, int api) throws RespostaWsInvalidaException {
//
//
//        String dados = null;
//
//        try {
//
//            int inicio = respostaWS.indexOf(">[");
//            int fim = 0;
//
//            if(inicio == -1){
//                inicio = respostaWS.indexOf( '{' );
//                fim = respostaWS.indexOf( "</string>" );
//            }
//            else{
//                inicio += 2;
//                fim = respostaWS.indexOf( "]</string>" );
//            }
//
//            String conteudo = respostaWS.substring(inicio, fim);
//
//
//            conteudo = formatarEquipamentos(cabecalho, conteudo);
//
//
//            int codigo = validarConteudo(conteudo);
//
//
//            JSONObject resposta = new JSONObject(conteudo);
//            resposta.put("metodo", metodo);
//            resposta.put("api", api);
//            resposta.put("codigo", codigo);
//            return resposta.toString();
//
//        }
//        catch (JSONException e) {
//            Codigo codigo = new Codigo(ID_500, e.getMessage());
//            throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));
//        }
//        catch (NullPointerException | StringIndexOutOfBoundsException e) {
//            descodificarErro(respostaWS, e.getMessage());
//        }
//
//        return dados;
//    }
//
//
//    private String formatarEquipamentos(Headers cabecalho, String conteudo) {
//
//        try {
//            if (cabecalho.get(Sintaxe.API.METODO_INTERNO).equals(Sintaxe.API.DESCRICAO_METODO_INTERNO_EQUIPAMENTO) == true) {
//
//                JSONObject resposta = new JSONObject(conteudo);
//                resposta.put("idProvisorioEquipamento", cabecalho.get(Sintaxe.API.ID_EQUIPAMENTO_PROVISORIO));
//                conteudo = resposta.toString();
//            }
//        }
//        catch (NullPointerException | JSONException e){}
//
//        return conteudo;
//    }
//
//
//
//    /**
//     * Metodo que permite validar o conteudo da resposta do web service
//     * @param conteudo os dados a validar
//     * @throws RespostaWsInvalidaException
//     */
//    private int validarConteudo(String conteudo) throws RespostaWsInvalidaException {
//
//
//        Codigo codigo = gson.fromJson(conteudo, Codigo.class);
//
//        if(codigo.codigo == ID_100){
//            return ID_100;
//        }
//
//
//        switch (codigo.codigo){
//
//            case ID_400:
//
//                codigo.mensagem = MSG_400;
//                break;
//
//            case ID_402:
//
//                codigo.mensagem = MSG_402;
//                break;
//
//            default:
//                break;
//        }
//
//        if(codigo.mensagem != null) {
//            throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));
//        }
//
//        return ID_100;
//    }
//
//

//
//    @NotNull
//    @Override
//    public Response intercept(@NotNull Chain chain) throws IOException {
//
//        Request pedido = chain.request();
//
//        String metodo = TiposUtil.obterMetodo(pedido.url().pathSegments(), pedido.header(Sintaxe.API.METODO_INTERNO));
//
//
//
//        if(pedido.header(Sintaxe.API.API) == null) {
//
//            Gson gson = new GsonBuilder().create();
//            throw new MetodoWsInvalidoException(gson.toJson(Codigo_601, Codigo.class));
//        }
//
//        int api = Integer.parseInt(pedido.header(Sintaxe.API.API));
//
//        Response resposta = chain.proceed(pedido);
//        ResponseBody corpo = resposta.body();
//        String responseString = corpo.string();
//
//        if(metodo.equals("GetDados") == true || metodo.equals("GetDadosDia") == true){
//            //String responseString_1 = resposta.peekBody(2048).string();
//            //responseString = corpo.string();
//
//            String lolo = ",\"AvaliacaoRiscosAnterior\":{\"processoProdutivo\":null,\"equipamentos\":null,\"levantamentosRisco\":null,\"Vulnerabilidades\":null,\"Checklist\":null,\"capaRelatorio\":null,\"RelatorioPlanoAcao\":null,\"RelatorioPlanoAcaoAVR\":null,\"RelatorioPlanoAcaoST\":null}";
//            String lolo2 = ",\"AuditoriaAnterior\":{\"idChecklist\":null,\"versaoChecklist\":null,\"dadosChecklist\":null}";
//            if(responseString.contains(lolo) == true){
//
//                responseString = responseString.replace(lolo, "");
//            }
//            if(responseString.contains(lolo2) == true){
//
//                responseString = responseString.replace(lolo2, "");
//            }
//        }
//
//        else{
//            //responseString = corpo.string();
//        }
//
//
//        MediaType contentType = corpo.contentType();
//        ResponseBody body = ResponseBody.create(obterJSON(/*corpo.string()*/responseString, metodo, pedido.headers(), api),contentType);
//        return resposta.newBuilder().body(body).build();
//    }
//
//
//    /**
//     * Metodo que obtem o json da resposta do web service
//     * @param respostaWS resposta recebida do web service
//     * @param metodo o metodo associado ao pedido
//     * @param api o identificador da api
//     * @return  um objecto com os dados
//     * @throws RespostaWsInvalidaException
//     */
//    private String obterJSON(String respostaWS, String metodo, Headers cabecalho, int api) throws RespostaWsInvalidaException {
//
//        Gson gson = new GsonBuilder().create();
//        String dados = null;
//
//        try {
//
//            int inicio = respostaWS.indexOf(">[");
//            int fim = 0;
//
//            if(inicio == -1){
//                inicio = respostaWS.indexOf( '{' );
//                fim = respostaWS.indexOf( "</string>" );
//            }
//            else{
//                inicio += 2;
//                fim = respostaWS.indexOf( "]</string>" );
//            }
//
//            String conteudo = respostaWS.substring(inicio, fim);
//
//
//            if(metodo.equals("ObterEstadoEquipamento") == true){
//
//                try {
//                    JSONObject respostagg = new JSONObject(conteudo);
//                    respostagg.put("idProvisorioEquipamento", cabecalho.get(Sintaxe.API.ID_EQUIPAMENTO_PROVISORIO));
//                    conteudo = respostagg.toString();
//
//                }
//                catch (JSONException e) {
//
//                }
//            }
//
//            validarConteudo(gson, conteudo);
//
//
//            JSONObject resposta = new JSONObject(conteudo);
//            resposta.put("metodo", metodo);
//            resposta.put("api", api);
//            return resposta.toString();
//
//        }
//        catch (JSONException e) {
//            Codigo codigo = new Codigo(ID_500, e.getMessage());
//            throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));
//        }
//        catch (NullPointerException | StringIndexOutOfBoundsException e) {
//            descodificarErro(respostaWS, e.getMessage());
//        }
//
//        return dados;
//    }
//
//
//
//    /**
//     * Metodo que permite validar o conteudo da resposta do web service
//     * @param gson
//     * @param conteudo os dados a validar
//     * @throws RespostaWsInvalidaException
//     */
//    private void validarConteudo(Gson gson, String conteudo) throws RespostaWsInvalidaException {
//
//        ISessao sessao = gson.fromJson(conteudo, ISessao.class);
//
//        if(sessao.sessoes != null){
//            if(sessao.sessoes.get(0).trabalho.size() == 0){
//                throw new RespostaWsInvalidaException(gson.toJson(Codigo_101, Codigo.class));
//            }
//        }
//
//        Codigo codigo = gson.fromJson(conteudo, Codigo.class);
//
//
//        switch (codigo.codigo){
//
////            case ID_100:
////
////                codigo.mensagem = MSG_100;
////                break;
//
//            case CODIGO_101:
//
//                codigo.mensagem = MSG_101;
//                break;
//
//            case CODIGO_400:
//
//                codigo.mensagem = MSG_400;
//                break;
//
//            case ID_402:
//
//                codigo.mensagem = MSG_402;
//                break;
//
//            default:
//                break;
//        }
//
//        if(codigo.mensagem != null) {
//            throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));
//        }
//    }



    /**
     * Metodo que permite descodificar erros não declarados
     * @param respostaWS a mensagem recebida de uma comunicacão
     * @param subMensagem mensagem extra a figurar
     * @throws RespostaWsInvalidaException
     */
    private void descodificarErro(String respostaWS, String subMensagem) throws RespostaWsInvalidaException {

        Codigo codigo = new Codigo();

        try {

            if(respostaWS.equals(MSG_501) == true){ //Connection reset by peer
                codigo = Codigo_501;
            }

            else if(respostaWS.contains("Connect to") == true){ //Connect to ip timed out
                codigo = Codigo_502;
            }

            else if(respostaWS.contains("HTTP Error 503. The service is unavailable.") == true){ //servidor indisponivel
                codigo = Codigo_503;
            }

            else if(respostaWS.contains("<html>") == true || respostaWS.contains("<!DOCTYPE HTML>") == true){ //html
                codigo = Codigo_503;
            }

            else if(respostaWS.equals("null")){ //null
                codigo = Codigo_504;
            }

            else{ //nao identificado
                codigo = Codigo_500;
                codigo.mensagem += "\n " + subMensagem;
            }

            throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));

        }
        catch (NullPointerException e) {

            codigo = Codigo_500;
            codigo.mensagem += "\n " + e.getMessage();
            throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));
        }

    }


}
