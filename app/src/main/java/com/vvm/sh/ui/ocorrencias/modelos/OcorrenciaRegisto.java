package com.vvm.sh.ui.ocorrencias.modelos;

public class OcorrenciaRegisto {

    public int id, fiscalizado, ultimoRegisto, idResultado;
    public String descricao, detalhe, observacao;
    public String codigo;
    //public boolean registo, selecionado;

    public OcorrenciaRegisto(int id, String descricao, String codigo, String detalhe, String observacao, int fiscalizado, int ultimoRegisto,
                             int idResultado) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
        this.detalhe = detalhe;
        this.observacao = observacao;
        this.fiscalizado = fiscalizado;
        this.ultimoRegisto = ultimoRegisto;
        this.idResultado = idResultado;
    }

    public String obterDescricao(){
        return codigo + " " + descricao;
    }

    public boolean itemRegisto(){

        if(ultimoRegisto == 0){
            return true;
        }
        else if(detalhe.equals("") == true){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean existeDetalhe(){

        if(detalhe.equals("") == true){
            return false;
        }
        else{
            return true;
        }
    }


    public boolean estadoFiscalizacao(){
        return  fiscalizado > 0;
    }


    public boolean existeResultado(){
        return  idResultado > 0;
    }
}
