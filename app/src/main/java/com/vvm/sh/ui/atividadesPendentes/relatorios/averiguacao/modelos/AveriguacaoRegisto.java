package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos;

import androidx.room.ColumnInfo;

public class AveriguacaoRegisto {
//
//    @ColumnInfo(name = "id")
//    public int id;

    @ColumnInfo(name = "idRelatorio")
    public int idRelatorio;

    @ColumnInfo(name = "idMedida")
    public int idMedida;

    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "implementado")
    public boolean implementado;

}
