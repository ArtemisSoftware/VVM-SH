package com.vvm.sh.ui.ocorrencias.modelos;

public class OcorrenciaRegisto {

    public int id;
    public String descricao, detalhe;
    public String codigo;
    //public boolean registo, selecionado;

    public OcorrenciaRegisto(int id, String descricao, String codigo, String detalhe) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
        this.detalhe = detalhe;
        //this.registo = registo;
    }

    public String obterDescricao(){
        return codigo + " " + descricao;
    }

    public boolean itemRegisto(){

        if(detalhe.equals("") == true){
            return false;
        }
        else{
            return true;
        }
    }

}
