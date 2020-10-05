package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "tiposTemplateAvrRiscos",
        primaryKeys = {"id"})
public class TipoTemplateAvrRisco {


    @NonNull
    public int id;


    @NonNull
    public int idLevantamento;

    @NonNull
    public int idRisco;

    @NonNull
    public int idRiscoEspecifico;

    @NonNull
    public String consequencias;

    @NonNull
    @ColumnInfo(name = "ativo")
    public int ativo;

}
