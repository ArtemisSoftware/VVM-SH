package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tiposExtintor",
        indices = {@Index(value="idTarefa", unique = false) },
        primaryKeys = {"idTarefa","id"},
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class TipoExtintor {


    @NonNull
    public int idTarefa;


    @NonNull
    public String id;


    @NonNull
    @ColumnInfo(name = "descricao")
    public String descricao;


    public TipoExtintor(int idTarefa, @NonNull String id, @NonNull String descricao) {
        this.idTarefa = idTarefa;
        this.id = id;
        this.descricao = descricao;
    }
}
