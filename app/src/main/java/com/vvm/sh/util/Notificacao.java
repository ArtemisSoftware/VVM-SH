package com.vvm.sh.util;

import android.os.Handler;
import android.os.Message;

public class Notificacao {

    private Handler handler;


    public Notificacao(Handler handler){
        this.handler = handler;
    }


    /**
     * Metodo que permite enviar para o UI uma mensagem
     * @param codigo o codigo da notificacao
     */
    public void atualizarUI(Codigo codigo){

        if(handler != null){
            Message msg = handler.obtainMessage();
            msg.obj = new Comunicado(codigo);
            handler.sendMessage(msg);
        }
    }


    /**
     * Metodo que permite enviar para o UI uma mensagem
     * @param codigo o codigo da notificacao
     * @param dados os dados a enviar
     */
    public void atualizarUI(Codigo codigo, String dados){

        if(handler != null){
            Message msg = handler.obtainMessage();
            msg.obj = new Comunicado(codigo, dados);
            handler.sendMessage(msg);
        }
    }


    /**
     * Metodo que permite enviar para o UI uma mensagem
     * @param codigo o codigo da notificacao
     * @param mensagem mensagem a ser enviada
     * @param progresso
     * @param limite
     */
    public void atualizarUI(Codigo codigo, String mensagem, int progresso, int limite){

        if(handler != null){
            Message msg = handler.obtainMessage();
            msg.obj = new Comunicado(codigo, mensagem, progresso, limite);
            handler.sendMessage(msg);
        }
    }


    public enum Codigo {
        CONCLUIR_PEDIDO_VERSAO_APP,
        PROCESSAMENTO_DADOS,
        CONCLUIR_DOWNLOAD_APK,
        ERRO_DOWNLOAD_APK
    }


    public class Comunicado {

        private Codigo codigo;
        private String mensagem, dados;
        private int posicao, limite;

        public Comunicado(Codigo codigo){

            this.codigo = codigo;
        }

        public Comunicado(Codigo codigo, String dados){

            this.codigo = codigo;
            this.dados = dados;
        }

        public Comunicado(Codigo codigo, String mensagem, int posicao, int limite){

            this.codigo = codigo;
            this.mensagem = mensagem;
            this.posicao = posicao;
            this.limite = limite;
        }


        public Codigo obterCodigo(){
            return codigo;
        }

        public String obterDados(){
            return dados;
        }

        public String obterMensagem(){
            return mensagem;
        }

        public int obterPosicao(){
            return posicao;
        }

        public int obterLimite(){
            return limite;
        }
    }
}
