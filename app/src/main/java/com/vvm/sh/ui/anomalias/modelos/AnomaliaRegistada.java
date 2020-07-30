package com.vvm.sh.ui.anomalias.modelos;

public class AnomaliaRegistada {

    public int id;
    public int idAnomalia;
    public String descricao;
    public String observacao;

    public AnomaliaRegistada(int id, int idAnomalia, String descricao, String observacao) {
        this.id = id;
        this.idAnomalia = idAnomalia;
        this.descricao = descricao;
        this.observacao = observacao;
    }
}
