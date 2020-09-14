package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;

public class AvaliacaoAmbiental {

    @Embedded
    public AvaliacaoAmbientalResultado resultado;

    @ColumnInfo(name = "necessitaMedidas")
    public boolean necessitaMedidas;

    @ColumnInfo(name = "valido")
    public boolean valido;
}
