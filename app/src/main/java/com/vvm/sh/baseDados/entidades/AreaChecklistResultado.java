package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "areasChecklistResultado",

        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "idAtividade",
                onDelete = CASCADE))
public class AreaChecklistResultado {


    @NonNull
    public int idAtividade;

    @NonNull
    public int idChecklist;

    @NonNull
    public int idArea;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    public String subDescricao;



}
