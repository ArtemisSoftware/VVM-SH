package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "planoAccaoResultado",
        indices = {@Index(value="idTarefa", unique = false) },

        foreignKeys = @ForeignKey(entity = PlanoAcaoAtividade.class,
                parentColumns = "id",
                childColumns = "idAtividadePlano",
                onDelete = CASCADE))
public class PlanoAccaoResultado {


    @NonNull
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "idAtividadePlano")
    public int idAtividadePlano;


    @ColumnInfo(name = "servId")
    public String servId;


    @ColumnInfo(name = "data")
    public String data;


    public PlanoAccaoResultado(int idTarefa, int id, int idAtividadePlano, String servId, String data) {
        this.idTarefa = idTarefa;
        this.id = id;
        this.idAtividadePlano = idAtividadePlano;
        this.servId = servId;
        this.data = data;
    }

    @Ignore
    public PlanoAccaoResultado(int idTarefa, int idAtividadePlano, String servId, String data) {
        this.idTarefa = idTarefa;
        this.idAtividadePlano = idAtividadePlano;
        this.servId = servId;
        this.data = data;
    }
}
