package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos;

import androidx.room.Ignore;

public class RelatorioAmbiental {

    public int idRelatorio = 0;

    public boolean geralValido = false;


    @Ignore
    public boolean avaliacoesValido = false;

    @Ignore
    public boolean valido = false;

    @Ignore
    public int numeroAvaliacoes = 0;




    @Ignore
    public RelatorioAmbiental() {
    }

    public RelatorioAmbiental(int idRelatorio, boolean geralValido) {
        this.idRelatorio = idRelatorio;
        this.geralValido = geralValido;
    }
}
