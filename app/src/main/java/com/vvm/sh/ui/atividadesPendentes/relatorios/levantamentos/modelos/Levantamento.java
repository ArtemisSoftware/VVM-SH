package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;

import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.util.interfaces.EstadoModelo;

public class Levantamento implements EstadoModelo {

    @Embedded
    public LevantamentoRiscoResultado resultado;

    @ColumnInfo(name = "modelo")
    public String modelo;

    @ColumnInfo(name = "categoriasProfissionais")
    public int numeroCategoriasProfissionais;

    @ColumnInfo(name = "riscos")
    public int numeroRiscos;


    @ColumnInfo(name = "validadePerigoTarefa")
    public boolean validadePerigoTarefa;

    @ColumnInfo(name = "valido")
    public boolean valido;


    public Levantamento(LevantamentoRiscoResultado resultado, String modelo, int numeroCategoriasProfissionais, int numeroRiscos, boolean validadePerigoTarefa, boolean valido) {
        this.resultado = resultado;
        this.modelo = modelo;
        this.numeroCategoriasProfissionais = numeroCategoriasProfissionais;
        this.numeroRiscos = numeroRiscos;
        this.validadePerigoTarefa = validadePerigoTarefa;
        this.valido = valido;


        this.estadoModelo = EstadoModelo.MODELO;
    }

    @Ignore
    public boolean selecionado;


    @Ignore
    public int estadoModelo;

    @Ignore
    public Levantamento(int estadoModelo){
        this.estadoModelo = estadoModelo;
    }


    @Override
    public int obterEstado() {
        return estadoModelo;
    }
}
