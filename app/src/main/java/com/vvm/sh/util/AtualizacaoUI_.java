package com.vvm.sh.util;

import com.vvm.sh.R;

public class AtualizacaoUI_ {

    public enum Estado {

        PROCESSAMENTO_TIPOS,
        PROCESSAMENTO_ATIVIDADES_PLANEAVEIS,
        PROCESSAMENTO_TEMPLATE_AVALIACAO_RISCOS,
        PROCESSAMENTO_EQUIPAMENTOS,
    }


    public Estado estado;


    public int icon = R.drawable.ic_alerta_24dp;
    public boolean loading;

    public String mensagem = "";

    public AtualizacaoUI_(String descricao) {
        this.loading = true;
        this.mensagem = descricao;
    }


    public AtualizacaoUI_(Estado estado, int index, int limite, String descricao) {
        this.estado = estado;
        this.loading = true;
        this.mensagem = descricao + " " + index + " / " + limite;
    }

    public AtualizacaoUI_(Estado estado, String erro) {

        boolean completo = (erro == null);

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
