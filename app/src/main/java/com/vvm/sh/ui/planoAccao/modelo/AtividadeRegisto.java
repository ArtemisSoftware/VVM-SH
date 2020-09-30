package com.vvm.sh.ui.planoAccao.modelo;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;
import com.vvm.sh.baseDados.entidades.PlanoAccaoResultado;

public class AtividadeRegisto {

    @Embedded
    public PlanoAcaoAtividade atividade;


    @Relation(
            parentColumn = "id",
            entityColumn = "idAtividadePlano"
    )
    public PlanoAccaoResultado resultado;

    @ColumnInfo(name = "tipo")
    public int tipo;


    @ColumnInfo(name = "nota")
    public String nota;

    @ColumnInfo(name = "reprogramavel")
    public boolean reprogramavel;

    @ColumnInfo(name = "tipoExecucao")
    public int tipoExecucao;
}
