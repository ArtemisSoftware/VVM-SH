package com.vvm.sh.servicos;

import java.util.ArrayList;
import java.util.List;

public abstract class GestorServicos {


    private final int LIMITE_SERVICOS_WS = 128;

    //protected Context contexto;
    //protected Notario notario;

    /**
     * Handler para notificacaoes internas
     */
    //protected final Handler handlerNotificacoesUI;

    /**
     * Variavel que contem a lista de servicos a serem executados
     */
    protected List<Servico> servicos;

    private int numeroPedidos, numeroPedidosTotal;


    public GestorServicos(/*Context contexto, Handler handlerUI*/) {

        /*
        notario = new Notario(handlerUI);
        this.contexto = contexto;

        FONTE = MetodosApp.obterNomeClasse(this.getClass());

        handlerNotificacoesUI = gerarHandler();
*/
        servicos = new ArrayList<>();
/*
        processador = obterProcessador(contexto, handlerUI);
        */
    }




    /**
     * Metodo que permite obter os dados do carregamento
     */
    public void obterDados(){

        /*
        if(verificarLigacaoRede() == true){

            iniciarVariaveisCarregamento();
            IniciarGestor();
        }
        else{
            handlerNotificacoesUI.sendEmptyMessage(NotificacaoUIIF.ERRO_REDE_INDISPONIVEL);
        }
        */
    }



    /**
     * Metodo que permite obter o numero de pedidos a realizadar
     * @return o numero de pedidos a realizadar
     */
    public int obterNumeroPedidosTotal(){
        return numeroPedidosTotal;
    }



    /**
     * Metodo que permite atualizar o numero de pedidos pendentes
     */
    private void atualizarPedidosPendentes(){
        ++numeroPedidos;
    }


/*
    @Override
    protected Handler gerarHandler() {

        Handler handlerNotificacoesUI = new Handler(){

            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {

                    case NotificacaoUIIF.ERRO_REDE_INDISPONIVEL:

                        terminarCarregamento();
                        break;

                    default:

                        gerirNotificacoes(msg);
                        break;
                }

                super.handleMessage(msg);
            }
        };

        return handlerNotificacoesUI;
    }
*/






    /**
     * Metodo que inicializa as variaveis do gestor
     */
    protected void iniciarVariaveis(){

        servicos.clear();
        numeroPedidos = 0;
        numeroPedidosTotal = 0;
    }


    /**
     * Metodo que permite adicionar servicos a pilha de servicos a serem executados
     * @param servico um servico a ser executado
     */
    protected void adicionarServicos(Servico servico){

        if((servicos.size() + 1) < LIMITE_SERVICOS_WS){

            servicos.add(servico);
        }
        else{
            //--LOG--LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Atingido o numero m-ximo de pedidos ao ws");
        }
    }



    /**
     * Metodo que permite executar os servicos existentes na lista de servicos
     * @param fase o identificador da fase que inicia a execucao de servicos
     */
    /*
    protected void executarServicos(int fase){
        this.FASE = fase;

        executarServicos();
    }
*/

    /**
     * Metodo que permite executar os servicos existentes na lista de servicos
     */
    /*
    protected void executarServicos(){

        if(servicos.size() == 0){

            LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Não existem servicos para executar");

            MetodosDialogo.dialogoAlerta(contexto, SintaxeIF.TITULO_GESTOR, "Não existem servicos para executar");
            terminarCarregamento();

        }
        else{
            numeroPedidosTotal = servicos.size();
            LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("Numero de servicos as executar > " + numeroPedidosTotal);

            int index = 1;

            for (Servico servico : servicos) {

                notario.atualizarUI(NotificacaoUIIF.REGISTAR_PROGRESSO_EXECUCAO_SERVICOS, "Numero de pedidos: " + index + " / " + servicos.size(), index, servicos.size());

                servico.execute(FONTE);

                ++index;
            }
        }
    }
*/


    /**
     * Metodo que permite adicionar as respostas dos servicos.<br>
     * Caso todas as respostas já estejam registadas inicia o processamento das mesmas
     *//*
    protected void registarExecucaoServicos(Comunicado comunicado){

        atualizarPedidosPendentes();

        try{
            processador.adicionarRespostaWs(comunicado.obterDadosWs());

            notario.atualizarUI(NotificacaoUIIF.REGISTAR_PROGRESSO_EXECUCAO_SERVICOS, "Numero de respostas do ws: " + numeroPedidos + " / " + numeroPedidosTotal, numeroPedidos, numeroPedidosTotal);

        }
        catch(NullPointerException e){}

        if(numeroPedidos == numeroPedidosTotal){
            iniciarProcessamento();
        }
    }
*/




    /**
     * Metodo que termina uma fase de carregamento.<br><br>
     *
     * >Reinicia as variaveis do carregamento<br>
     *
     * <b>O metodo Não termina a caixa de dialogo caso esta esteja aberta</b>
     *
     *//*
    protected void terminarFase(){

        FASE = -1000;
        notario.atualizarUI(obterNotificacaoFinal());
        iniciarVariaveisCarregamento();
    }
*/


    /**
     * Metodo que verifica se existe ligação - rede
     * @return true caso exista ligação e false caso contrário
     *//*
    private boolean verificarLigacaoRede() {

        NetworkInfo netInfo = ((ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_REDE).adicionarEstadoRede(netInfo);

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {

            return true;
        }

        LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto(SintaxeIF.LIGACAO_REDE_INDISPONIVEL);
        notario.atualizarUI(NotificacaoUIIF.DISPONIBILIDADE_REDE, SintaxeIF.LIGACAO_REDE_INDISPONIVEL);
        return false;
    }
*/



}
