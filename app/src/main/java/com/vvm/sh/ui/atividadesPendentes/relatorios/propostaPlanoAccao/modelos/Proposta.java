package com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.PropostaPlanoAcaoResultado;

public class Proposta {

    @Embedded
    public PropostaPlanoAcaoResultado resultado;



    @ColumnInfo(name = "descricao")
    public String descricao;


    @ColumnInfo(name = "tipo")
    public int tipo;
}
