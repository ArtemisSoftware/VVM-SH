package com.vvm.sh.ui.agenda.modelos;

import androidx.room.ColumnInfo;

import java.util.Date;

public class DataAgendamento {

    @ColumnInfo(name = "data")
    public Date data;



    @ColumnInfo(name = "ct_pendencias_upload")
    public int numeroPendenciasUpload;
}
