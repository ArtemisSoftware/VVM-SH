package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "ocorrenciaResultado",
        indices = {@Index(value="idTarefa", unique = false) },
        primaryKeys = {"idTarefa","id"},

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class OcorrenciaResultado {



    @NonNull
    public int idTarefa;

    @NonNull
    public int id;


    @ColumnInfo(name = "observacao")
    public String observacao;

    @NonNull
    @ColumnInfo(name = "fiscalizado")
    public boolean fiscalizado;


    @ColumnInfo(name = "dias")
    public String dias;


    public OcorrenciaResultado(int idTarefa, int id, String observacao, boolean fiscalizado, String dias) {
        this.idTarefa = idTarefa;
        this.id = id;
        this.observacao = observacao;
        this.fiscalizado = fiscalizado;
        this.dias = dias;
    }
}
