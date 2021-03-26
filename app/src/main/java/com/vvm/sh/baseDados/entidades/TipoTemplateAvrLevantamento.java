package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tiposTemplateAvrLevantamentos",

        foreignKeys = @ForeignKey(
                entity = Atualizacao.class,
                parentColumns = {"descricao", "api"},
                childColumns = {"tipo", "api"},
                onDelete = CASCADE)

)
public class TipoTemplateAvrLevantamento {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    @NonNull
    public String tipo;

    @NonNull
    public int api;

    @NonNull
    public int idModelo;


    @NonNull
    public String tarefa;


    @NonNull
    public String perigo;

    @NonNull
    @ColumnInfo(name = "ativo")
    public int ativo;
}
