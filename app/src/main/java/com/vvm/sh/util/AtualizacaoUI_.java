package com.vvm.sh.util;

import com.vvm.sh.R;

public class AtualizacaoUI_ {

    public enum Estado {

        PROCESSAMENTO_TIPOS,
    }


    public Estado estado;


    public int icon = R.drawable.ic_alerta_24dp;
    public boolean loading;

    public String mensagem = "";

    public AtualizacaoUI_(Estado estado, int index, int limite, String descricao) {
        this.estado = estado;
        this.loading = true;
        this.mensagem = descricao + " " + index + " / " + limite;
    }

    public AtualizacaoUI_(Estado estado, boolean completo, String erro) {

        if(completo == true){
            icon = R.drawable.ic_validado_branco;
            this.mensagem = "Concluido";
        }
        else{
            icon = R.drawable.ic_erro_24;
            this.mensagem = erro;
        }

        this.estado = estado;
        this.loading = false;
    }


}
