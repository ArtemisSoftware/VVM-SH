package com.vvm.sh.util;

import android.os.Handler;
import android.os.Message;

public class AtualizacaoUI {

    private Handler handler;


    public AtualizacaoUI(Handler handler){
        this.handler = handler;
    }


    /**
     * Metodo que permite enviar para o UI uma mensagem
     * @param codigo o codigo da atualizacaoUI
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
     * @param codigo o codigo da atualizacaoUI
     * @param dados os dados a enviar
     */
    public void atualizarUI(Codigo codigo, String dados){

        if(handler != null){
            Message msg = handler.obtainMessage();
            msg.obj = new Comunicado(codigo, dados);
            handler.sendMessage(msg);
        }
    }


    public void atualizarUI(Codigo codigo, Object dados){

        if(handler != null){
            Message msg = handler.obtainMessage();
            msg.obj = new Comunicado(codigo, dados);
            handler.sendMessage(msg);
        }
    }

    /**
     * Metodo que permite enviar para o UI uma mensagem
     * @param codigo o codigo da atualizacaoUI
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
        PROCESSAMENTO_TIPOS,
        PROCESSAMENTO_CHECKLIST,
        PROCESSAMENTO_TRABALHO,
        PROCESSAMENTO_TIPOS_CONCLUIDO,
        PROCESSAMENTO_CHECKLIST_CONCLUIDO,
        PROCESSAMENTO_UPLOAD_CONCLUIDO,
        PROCESSAMENTO_DOWNLOAD_CONCLUIDO,
        CONCLUIR_DOWNLOAD_APK,
        ERRO_DOWNLOAD_APK,
        ERRO_INSTALACAO_APK,

        CONCLUIR_ATUALIZACAO_TIPO,
    }


    public class Comunicado {

        private Codigo codigo;
        public String mensagem = "", dados;
        public int posicao = 0, limite = 0;
        public Object objeto;

        public Comunicado(Codigo codigo){

            this.codigo = codigo;
        }

        public Comunicado(Codigo codigo, String dados){

            this.codigo = codigo;
            this.dados = dados;
        }

        public Comunicado(Codigo codigo, Object objeto){

            this.codigo = codigo;
            this.objeto = objeto;
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
        public String obterLimite_(){
            return posicao + "/" + limite;
        }
    }
}
