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
     * @param dados os dados a enviar
     */
    public void atualizarUI(Codigo codigo, String dados){

        if(handler != null){
            Message msg = handler.obtainMessage();
            msg.obj = new Comunicado(codigo, dados);
            handler.sendMessage(msg);
        }
    }


    public enum Codigo {
        CONCLUIR_PEDIDO_VERSAO_APP,
    }


    public class Comunicado {

        private Codigo codigo;
        private String dados;


        public Comunicado(Codigo codigo, String dados){

            this.codigo = codigo;
            this.dados = dados;
        }

        public Codigo obterCodigo(){
            return codigo;
        }

        public String obterDados(){
            return dados;
        }
    }
}
