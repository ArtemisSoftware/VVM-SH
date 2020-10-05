package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos;

import androidx.room.ColumnInfo;

public class AveriguacaoRegisto {

    @ColumnInfo(name = "id")
    public int id;


    @ColumnInfo(name = "descricao")
    public String descricao;
}
