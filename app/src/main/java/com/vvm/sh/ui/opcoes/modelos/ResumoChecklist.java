package com.vvm.sh.ui.opcoes.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.CheckList;


public class ResumoChecklist {

    @Embedded
    public CheckList checkList;


    @ColumnInfo(name = "numeroAreas")
    public int numeroAreas;

    @ColumnInfo(name = "numeroSeccoes")
    public int numeroSeccoes;

    @ColumnInfo(name = "numeroItens")
    public int numeroItens;

}
