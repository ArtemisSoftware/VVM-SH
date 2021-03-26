package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tiposTemplateAvrRiscos",
        primaryKeys = {"id"},

        foreignKeys = @ForeignKey(
                entity = Atualizacao.class,
                parentColumns = {"descricao", "api"},
                childColumns = {"tipo", "api"},
                onDelete = CASCADE)
)
public class TipoTemplateAvrRisco {


    @NonNull
    public int id;


    @NonNull
    public String tipo;

    @NonNull
    public int api;

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
