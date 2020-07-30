package com.vvm.sh.ui.ocorrencias.modelos;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.vvm.sh.ui.agenda.modelos.Tarefa;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "ocorrenciaResultado",
        indices = {@Index(value="idTarefa", unique = false) },
        primaryKeys = {"id","idTarefa"},

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class OcorrenciaResultado {



    @NonNull
    public int idTarefa;

    @NonNull
    public int id;


//    @NonNull
//    @ColumnInfo(name = "idOcorrencia")
//    public String idOcorrencia;


    @ColumnInfo(name = "observacao")
    public String observacao;


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