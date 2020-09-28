package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;

public class Levantamento {

    @Embedded
    public LevantamentoRiscoResultado resultado;

    @ColumnInfo(name = "modelo")
    public String modelo;

    @ColumnInfo(name = "categoriasProfissionais")
    public int numeroCategoriasProfissionais;

    @ColumnInfo(name = "riscos")
    public int numeroRiscos;


    @ColumnInfo(name = "valido")
    public boolean valido;

}