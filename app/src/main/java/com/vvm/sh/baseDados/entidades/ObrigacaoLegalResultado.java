package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "obrigacaoLegalResultado",
        indices = {@Index(value="idTarefa", unique = false) },
        primaryKeys = {"idTarefa" , "id"},
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class ObrigacaoLegalResultado {


    @NonNull
    public int idTarefa;

    @NonNull
    public int id;


    public ObrigacaoLegalResultado(int idTarefa, int id) {
        this.idTarefa = idTarefa;
        this.id = id;
    }

}
