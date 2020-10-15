package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

public class RelatorioLevantamento {

    @ColumnInfo(name = "id")
    public int id;


    @ColumnInfo(name = "numeroCategoriasProfissionais")
    public int numeroCategoriasProfissionais;

    @ColumnInfo(name = "numeroRiscos")
    public int numeroRiscos;

    @ColumnInfo(name = "validadePerigoTarefa")
    public boolean validadePerigoTarefa;

    @ColumnInfo(name = "validadeCategoriasProfissionais")
    public boolean validadeCategoriasProfissionais;

    @ColumnInfo(name = "validadeRiscos")
    public boolean validadeRiscos;


    @ColumnInfo(name = "valido")
    public boolean valido;

}
