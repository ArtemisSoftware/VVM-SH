package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
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


}
