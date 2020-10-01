package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tiposTemplateAvrLevantamentos")
public class TipoTemplateAvrLevantamento {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

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
