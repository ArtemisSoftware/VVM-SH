package com.vvm.sh.ui.ocorrencias.modelos;

public class OcorrenciaRegisto {

    public int id;
    public String descricao;
    public String codigo;
    public boolean registo, selecionado;

    public OcorrenciaRegisto(int id, String descricao, String codigo, int registo, int selecionado) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
        //this.registo = registo;
    }
}
