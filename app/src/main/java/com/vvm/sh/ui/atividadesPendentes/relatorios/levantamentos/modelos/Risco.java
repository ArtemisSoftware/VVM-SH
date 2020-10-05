package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.RiscoResultado;

public class Risco {
    //https://mega.nz/folder/jqJAlYyL#Js1gkGWstR3F9O3gKrINgw
    //http://linkunshortner.glitch.me/
    @Embedded
    public RiscoResultado resultado;

    @ColumnInfo(name = "risco")
    public String risco;
}
