package com.vvm.sh.ui.atividadesPendentes.relatorios;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;

public class AcaoFormacao {

    @Embedded
    public AcaoFormacaoResultado resultado;

    @ColumnInfo(name = "designacao")
    public String designacao;

}
