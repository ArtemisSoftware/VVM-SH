package com.vvm.sh.ui.agenda.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

import java.util.Date;

public class DataAgendamento {

    @ColumnInfo(name = "data")
    public Date data;



    @ColumnInfo(name = "ct_pendencias_upload")
    public int numeroPendenciasUpload;

    @Ignore
    public DataAgendamento(Date data) {
        this.data = data;
        this.numeroPendenciasUpload = 0;
    }

    public DataAgendamento(Date data, int numeroPendenciasUpload) {
        this.data = data;
        this.numeroPendenciasUpload = numeroPendenciasUpload;
    }
}
