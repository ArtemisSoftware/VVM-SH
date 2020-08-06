package com.vvm.sh.ui.atividadesPendentes.relatorios;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.FormandoResultado;

public class Formando {

    @Embedded
    public FormandoResultado resultado;


    @ColumnInfo(name = "assinatura")
    public byte[] assinatura;


    @ColumnInfo(name = "completo")
    public boolean completo;


}
