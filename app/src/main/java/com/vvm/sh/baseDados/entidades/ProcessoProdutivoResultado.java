package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "processosProdutivosResultado",
        primaryKeys = {"id"},
        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "id",
                onDelete = CASCADE))
public class ProcessoProdutivoResultado {

    @NonNull
    public int id;

    @NonNull
    @ColumnInfo(name = "descricao")
    public String descricao;
}
