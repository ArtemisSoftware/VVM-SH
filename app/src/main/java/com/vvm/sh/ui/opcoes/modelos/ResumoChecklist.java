package com.vvm.sh.ui.opcoes.modelos;

import androidx.room.ColumnInfo;

public class ResumoChecklist {

    @ColumnInfo(name = "descricao")
    public String descricao;


    @ColumnInfo(name = "numeroAreas")
    public int numeroAreas;

    @ColumnInfo(name = "numeroSeccoes")
    public int numeroSeccoes;

    @ColumnInfo(name = "numeroItens")
    public int numeroItens;

}
