package com.vvm.sh.servicos;

import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.util.AtualizacaoUI;

public abstract class Servico extends AsyncTask<String, String, Void> {

    //protected Context contexto;


    /**
     * Permite enviar mensagens para fora do servico
     */
    protected AtualizacaoUI atualizacaoUI;


    /**
     * Permite enviar mensagens para a caixa de dialogo do servico
     */
    //protected NotarioServico notarioServico;




    //protected final String FONTE;


    public Servico (Handler handler){

        atualizacaoUI = new AtualizacaoUI(handler);
        /*
        FONTE = MetodosApp.obterNomeClasse(this.getClass());
        LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("A executar o servico: " + FONTE);

 */
    }

/*
    public Servico (Context contexto, Handler handler){


        notario = new Notario(handler);

        this.contexto = contexto;

        FONTE = MetodosApp.obterNomeClasse(this.getClass());
        LogApp_v4.obterInstancia(FONTE, LogIF.ID_LOG_GERAL).adicionarTexto("A executar o servico: " + FONTE);

    }
*/


    @Override
    protected void onPreExecute() {

        /*
        notarioServico = new NotarioServico(this);

            notario.atualizarUI(NotificacaoUIIF.REGISTAR_INICIO_SERVICO, obterMensagemDialogo(), obterLimiteDialogo());
        */
    }


    @Override
    protected Void doInBackground(String... params) {

        executar();
        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        terminarExecucao();
    }



    @Override
    protected void onProgressUpdate(String... texto) {


        //    notario.atualizarUI(NotificacaoUIIF.REGISTAR_PROGRESSO_SERVICO, new Comunicado(texto));

    }


    /**
     * Metodo que permite registar o progresso do servico

     */
    public void registarProgresso(int tipo, String mensagem){

        String texto [] = {mensagem, tipo +""};
        publishProgress(texto); // Calls onProgressUpdate()
    }


    /**
     * Metodo que permite registar o progresso do servico

     */
    public void registarProgresso(int tipo, String mensagem, int posicao, int limite){

        String texto [] = {mensagem, tipo +"", posicao + "", limite + ""};
        publishProgress(texto); // Calls onProgressUpdate()
    }

    //--------------------------------
    //Metodos locais - dialogo
    //--------------------------------



    /**
     * Metodo que permite obter o limite maximo da barra de progresso
     * @return o limite
     */
    protected int obterLimiteDialogo(){
        return 1;
    }


    //---------------------------------
    //Metodos abstratos
    //---------------------------------

    /**
     * Metodo que permite executar a tarefa do servico
     */
    protected abstract void executar();


    /**
     * Metodo que permite executar uma instrução após ter sido executada a tarefa do servico
     */
    protected abstract void terminarExecucao();

}
