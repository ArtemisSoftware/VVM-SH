package com.vvm.sh.ui.anomalias.modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.ui.agenda.modelos.Tarefa;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "anomaliasResultado",
        indices = {@Index(value="idTarefa", unique = false) },
        primaryKeys = {"idTarefa"},

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class AnomaliaResultado {


    @NonNull
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "idAnomalia")
    public int idAnomalia;

    @ColumnInfo(name = "observacao")
    public String observacao;


    @Ignore
    public AnomaliaResultado(int idTarefa, int idAnomalia, String observacao) {
        this.idTarefa = idTarefa;
        this.idAnomalia = idAnomalia;
        this.observacao = observacao;
    }

    public AnomaliaResultado(int idTarefa, int id, int idAnomalia, String observacao) {
        this.idTarefa = idTarefa;
        this.id = id;
        this.idAnomalia = idAnomalia;
        this.observacao = observacao;
    }
}
