package com.vvm.sh.servicos;

import com.vvm.sh.util.constantes.WebService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;



public abstract class ServicoComunicacao extends Servico {


    /**
     * Variavel que contem a resposta do ws após uma comunicacao
     */
    protected String resposta;


    /**
     * Varialve que indica se a comunicacao deva ser bloqueada
     */
    protected boolean BLOQUEAR_COMUNICACAO;


    public ServicoComunicacao(/*Context contexto, Handler handler, boolean ativarDialogo*/) {
        /*
        super(contexto, handler,ativarDialogo);

        datagrama = new GestorDatagramas();

        BLOQUEAR_COMUNICACAO = false;
        */
    }


    @Override
    protected void executar() {
        //enviarInformacao(obterParametros());

        //expedirDados();
        expedirDados_soap();
    }


    //-----------------------------------------------
    //Metodos locais
    //-----------------------------------------------


    /**
     * Metodo que contabiliza o numero de registos recebidos
     * @param rotuloDadosNovos o rotulo que identifica os dados novos
     * @param rotuloDadosAlterados o rotulo que identifica os dados alterados
     * @return o numero de registos recebidos ou -1 quando a resposta do ws possui um erro
     */
    /*
    protected int contabilizarDados(String rotuloDadosNovos, String rotuloDadosAlterados){

        int resultado = 0;

        try {
            resultado = info.getJSONArray(rotuloDadosNovos).length() +  info.getJSONArray(rotuloDadosAlterados).length();
        }
        catch (JSONException e) {
            resultado = -1;
        }

        return resultado;
    }
*/


    //-----------------------------------------------
    //Metodos locais - envio de dados
    //-----------------------------------------------

    /**
     * Metodo que permite enviar dados para o ws
     * @param parametros objeto com os dados a serem enviados
     * @return a resposta do web service
     */
    /*
    protected JSONObject enviarInformacao(Datagrama parametros){

        if(BLOQUEAR_COMUNICACAO){

            LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_REDE).adicionarTextoComunicacaoWS(parametros, "Teste");
            info = MetodosRede.respostaTeste(WebServiceComIF.CODIGO_90);
        }
        else {
            info = expedirDados(parametros);
        }

        return info;
    }
*/

    private void expedirDados_soap() {

        Datagrama datagrama = obterDatagrama();

        try {
            SoapObject request = new SoapObject(WebService.NAMESPACE_WEB_SERVICE, datagrama.obterMetodo());

            /*
            PropertyInfo info = new PropertyInfo();
            info.setName("StationID");
            info.setValue(1);
            info.setType(Integer.class);
            request.addProperty(info);

            PropertyInfo info11 = new PropertyInfo();
            info11.setName("Rows");
            info11.setValue(1);
            info11.setType(Integer.class);
            request.addProperty(info11);
            */

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE httpTransportSE = new HttpTransportSE(WebService.URL_WEB_SERVICE);

            try {
                httpTransportSE.call(datagrama.obterSoapAction(), envelope);
                SoapPrimitive soapPrimitive = (SoapPrimitive)envelope.getResponse();
                this.resposta = soapPrimitive.toString();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }



    /**
     * Metodo que permite expedir dados para o ws
     *
     * @return a resposta do web service
     */

    private /*JSONObject*/void /*String*/ expedirDados() {
        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL(
                    //"https://pokeapi.co/api/v2/language/4/"
                    "https://pokeapi.co/api/v2/pokemon/1"
                    //"http://ws.matheuscastiglioni.com.br/ws/cep/find/13845373/json"
            );

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                resposta.append(scanner.next());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {*/
        //URL url = new URL(/*"http://pokeapi.co/api/v2/pokemon/1/"*/"http://ws.matheuscastiglioni.com.br/ws/cep/find/13845373/json");
            /*urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String linha;
            StringBuffer buffer = new StringBuffer();
            while((linha = reader.readLine()) != null) {
                buffer.append(linha);
                buffer.append("\n");
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;

*/

        /*
        String url = parametros.obterUrl(), resultado;

        BufferedReader inBuffer = null;
        try {

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);

            List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

            for(int i = 0; i < parametros.numeroParametros(); ++i){
                postParameters.add(new BasicNameValuePair(parametros.obterNome(i), parametros.obterValor(i)));
            }

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"UTF-8");
            request.setEntity(formEntity);

            HttpResponse httpResponse = httpClient.execute(request);
            inBuffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            StringBuffer stringBuffer = new StringBuffer("");
            String line = "";
            String newLine = System.getProperty("line.separator");

            while ((line = inBuffer.readLine()) != null) {
                stringBuffer.append(line + newLine);
            }

            inBuffer.close();
            resultado = stringBuffer.toString();
            if(AppConfigIF.DEBUG_APP_WS)Log.d("DEBUG_APP_WS","Resposta do envio: " + resultado);

        }
        catch(Exception e) {
            resultado = e.getMessage();
        }
        finally {
            if (inBuffer != null) {
                try {
                    inBuffer.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

         */

        //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_REDE).adicionarTextoComunicacaoWS(parametros, resultado);
        //return null /*MetodosRede.obterJSON(resultado)*/;
    }





    //--------------------------------------
    //Metodos abstratos
    //--------------------------------------


    /**
     * Metodo que permite obter o datagrama necessario para executar a comunicacao
     * @return os parametros
     */
    abstract protected Datagrama obterDatagrama();


}
