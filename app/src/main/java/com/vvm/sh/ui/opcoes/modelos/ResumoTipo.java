package com.vvm.sh.ui.opcoes.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.Atualizacao;

public class ResumoTipo {

    @Embedded
    public Atualizacao atualizacao;

    @ColumnInfo(name = "numeroRegistosSA")
    public int numeroRegistosSA;


    @ColumnInfo(name = "numeroRegistosSHT")
    public int numeroRegistosSHT;
}
