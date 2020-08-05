package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.baseDados.entidades.Tarefa;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "anomaliasResultado",
        indices = {@Index(value="idTarefa", unique = false) },

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class AnomaliaResultado {


    @NonNull
    @ColumnInfo(name = "idTarefa")
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
