package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.vvm.sh.baseDados.entidades.Tarefa;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "sinistralidadesResultado",
        primaryKeys = {"idTarefa"},
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class SinistralidadeResultado {


    @NonNull
    public int idTarefa;

    @NonNull
    @ColumnInfo(name = "acidentesComBaixa")
    public int acidentesComBaixa;


    @NonNull
    @ColumnInfo(name = "diasUteisPerdidos")
    public double diasUteisPerdidos;


    @NonNull
    @ColumnInfo(name = "totalTrabalhadores")
    public int totalTrabalhadores;


    @NonNull
    @ColumnInfo(name = "horasAnoTrabalhadores")
    public double horasAnoTrabalhadores;


    @NonNull
    @ColumnInfo(name = "faltas")
    public double faltas;


}
