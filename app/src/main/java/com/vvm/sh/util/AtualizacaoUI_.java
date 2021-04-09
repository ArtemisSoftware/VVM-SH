package com.vvm.sh.util;

import androidx.annotation.NonNull;

import com.vvm.sh.R;

public class AtualizacaoUI_ {

    public enum Estado {

        PROCESSAMENTO_TIPOS,
        PROCESSAMENTO_ATIVIDADES_PLANEAVEIS,
        PROCESSAMENTO_TEMPLATE_AVALIACAO_RISCOS,
        PROCESSAMENTO_EQUIPAMENTOS,
        PROCESSAMENTO_CHECKLIST,


        PROCESSAMENTO_DOWNLOAD_ATUALIZACAO_APP,
        ERRO_DOWNLOAD_ATUALIZACAO_APP,

        PROCESSAMENTO_INSTALACAO_ATUALIZACAO_APP,
        ERRO_INSTALACAO_ATUALIZACAO_APP,

        ERRO,

        PROCESSAMENTO_TRABALHO,
        PROCESSAMENTO_DADOS_UPLOAD
    }


    public Estado estado;


    public int icon = R.drawable.ic_alerta_24dp, indexPercentagem = 0, limitePercentagem = 100, corIcon = -1;
    public boolean loading;

    public String mensagem = "", index = "0", limite = "0";

    public AtualizacaoUI_(String descricao) {
        this.loading = true;
        this.mensagem = descricao;
    }


    public AtualizacaoUI_(Estado estado, int index, int limite, String descricao) {
        this.estado = estado;
        this.loading = true;
        this.index = index + "";
        this.limite = limite + "";
        this.mensagem = descricao + " " + index + " / " + limite;

        try {
            this.indexPercentagem = index * limitePercentagem / limite;
        }
        catch (ArithmeticException e){
            this.indexPercentagem = 0;
        }
    }

    public AtualizacaoUI_(Estado estado, String erro) {

        boolean completo = (erro == null);

        if(completo == true){
            icon = R.drawable.ic_validado_branco;
            corIcon = R.color.cor_sincronizado;
            this.mensagem = "Concluido";
        }
        else{
            icon = R.drawable.ic_erro_24;
            corIcon = R.color.cor_nao_sincronizado;
            this.mensagem = erro;
        }

        this.estado = estado;
        this.loading = false;
    }

    public static  AtualizacaoUI_ erro() {
        return new AtualizacaoUI_(Estado.ERRO,  "");
    }

}
