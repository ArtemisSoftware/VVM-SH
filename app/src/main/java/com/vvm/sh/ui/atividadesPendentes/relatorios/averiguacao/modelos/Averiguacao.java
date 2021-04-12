package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.RelatorioAveriguacaoResultado;

public class Averiguacao {

    @ColumnInfo(name = "id")
    public int id;


    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "tipo")
    public int tipo;


    @ColumnInfo(name = "numeroRegistos")
    public int numeroRegistos;

    @ColumnInfo(name = "total")
    public int total;


    @ColumnInfo(name = "numeroRegistosImplementados")
    public int numeroRegistosImplementados;

    @ColumnInfo(name = "numeroRegistosNaoImplementados")
    public int numeroRegistosNaoImplementados;


    @ColumnInfo(name = "valido")
    public boolean valido;

}
