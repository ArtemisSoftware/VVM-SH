package com.vvm.sh.ui.planoAccao;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;

public class AtividadeRegisto {

    @Embedded
    public PlanoAcaoAtividade atividade;


    @ColumnInfo(name = "tipo")
    public int tipo;


    @ColumnInfo(name = "nota")
    public String nota;
}
