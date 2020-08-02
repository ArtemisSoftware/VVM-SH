package com.vvm.sh.ui.atividadesPendentes.relatorios;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class AcaoFormacao {

    @Embedded
    public AcaoFormacaoResultado resultado;

    @ColumnInfo(name = "designacao")
    public String designacao;

}
