package com.vvm.sh.api.modelos.bd;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;


public class RelatorioAmbientalBd {

    @Embedded
    public RelatorioAmbientalResultado resultado;


    @ColumnInfo(name = "idMedidaRecomendada")
    public int idMedidaRecomendada;
}
