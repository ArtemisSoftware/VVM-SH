package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos;

import androidx.room.Ignore;

public class RelatorioAmbiental {

    public int idRelatorio = 0;

    public boolean geralValido = false;

    public boolean avaliacoesValido = false;


    @Ignore
    public boolean valido = false;

    public int numeroAvaliacoes = 0;


    public String medida;


    @Ignore
    public RelatorioAmbiental() {
    }

    public RelatorioAmbiental(int idRelatorio, boolean geralValido, int numeroAvaliacoes, boolean avaliacoesValido, String medida) {
        this.idRelatorio = idRelatorio;
        this.geralValido = geralValido;
        this.numeroAvaliacoes = numeroAvaliacoes;
        this.avaliacoesValido = avaliacoesValido;
        this.valido = geralValido & avaliacoesValido;
        this.medida = medida;
    }
}
