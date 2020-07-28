package com.vvm.sh.ui.agenda.modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "resultados",
        primaryKeys = {"idTarefa", "id"},
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class Resultado {

    @NonNull
    public int idTarefa;

    @NonNull
    public int id;


    @NonNull
    @ColumnInfo(name = "sincronizado")
    public boolean sincronizado;


    public Resultado(int idTarefa, int id, boolean sincronizado) {
        this.idTarefa = idTarefa;
        this.id = id;
        this.sincronizado = sincronizado;
    }
}
