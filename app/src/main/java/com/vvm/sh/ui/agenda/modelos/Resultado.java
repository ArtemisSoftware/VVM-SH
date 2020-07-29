package com.vvm.sh.ui.agenda.modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import com.vvm.sh.util.ResultadoId;

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


    @Ignore
    public Resultado(int idTarefa, ResultadoId id) {
        this.idTarefa = idTarefa;
        this.id = id.getValue();
        this.sincronizado = sincronizado;
    }

    public Resultado(int idTarefa, int id, boolean sincronizado) {
        this.idTarefa = idTarefa;
        this.id = id;
        this.sincronizado = sincronizado;
    }

}
