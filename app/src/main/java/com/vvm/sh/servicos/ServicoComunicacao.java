package com.vvm.sh.servicos;

public abstract class ServicoComunicacao extends Servico {


    /**
     * Variavel que contem a resposta do ws após uma comunicacao
     */
/*
    protected JSONObject info;

    protected GestorDatagramas datagrama;
*/

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


    /**
     * Metodo que permite expedir dados para o ws
     * @param parametros objeto com os dados a serem enviados
     * @return a resposta do web service
     */
    /*
    private JSONObject expedirDados(Datagrama parametros){

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

        LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_REDE).adicionarTextoComunicacaoWS(parametros, resultado);

        return MetodosRede.obterJSON(resultado);
    }
*/


    //--------------------------------------
    //Metodos abstratos
    //--------------------------------------


    /**
     * Metodo que permite obter os parametros necessarios para o pedido de dados
     * @return os parametros de pedido de dados
     */
    //abstract protected Datagrama obterParametros();


}
