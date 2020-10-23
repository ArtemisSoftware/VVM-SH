package com.vvm.sh.api.modelos.bd;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;

public class RegistoVisitaBd {

    @Embedded
    public RegistoVisitaResultado resultado;


    @ColumnInfo(name = "idImagem")
    public int idImagem;
}
