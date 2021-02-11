package com.vvm.sh.ui.opcoes.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.Atualizacao;

public class ResumoTipo {

    @ColumnInfo(name = "descricao")
    public String descricao;


    @ColumnInfo(name = "numeroRegistosSA")
    public int numeroRegistosSA;

    @ColumnInfo(name = "numeroRegistosSHT")
    public int numeroRegistosSHT;


    @ColumnInfo(name = "seloTemporalSA")
    public String seloTemporalSA;

    @ColumnInfo(name = "seloTemporalSHT")
    public String seloTemporalSHT;
}
