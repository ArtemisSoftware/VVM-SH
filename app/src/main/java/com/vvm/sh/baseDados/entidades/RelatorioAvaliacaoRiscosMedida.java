package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "relatorioAvaliacaoRiscosMedidas",

        primaryKeys = {"idRelatorio", "idMedida"},
        foreignKeys = @ForeignKey(entity = RelatorioAvaliacaoRisco.class,
                parentColumns = "id",
                childColumns = "idRelatorio",
                onDelete = CASCADE))
public class RelatorioAvaliacaoRiscosMedida {


    @NonNull
    @ColumnInfo(name = "idRelatorio")
    public int idRelatorio;

    @NonNull
    public int idMedida;
}
