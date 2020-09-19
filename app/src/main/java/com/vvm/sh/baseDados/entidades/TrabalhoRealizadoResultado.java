package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "trabalhoRealizadoResultado",
        indices = {@Index(value="idTarefa", unique = false) },
        primaryKeys = {"idTarefa" , "id"},
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class TrabalhoRealizadoResultado {


    @NonNull
    public int idTarefa;

    @NonNull
    public int id;


    @ColumnInfo(name = "informacao")
    public String informacao;


}
