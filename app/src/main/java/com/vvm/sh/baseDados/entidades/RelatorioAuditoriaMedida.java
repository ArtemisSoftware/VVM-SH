package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "relatorioAuditoriaMedidas",

        primaryKeys = {"idRelatorio", "nc"},
        foreignKeys = @ForeignKey(entity = RelatorioAveriguacao.class,
                parentColumns = "id",
                childColumns = "idRelatorio",
                onDelete = CASCADE))
public class RelatorioAuditoriaMedida {

    @NonNull
    @ColumnInfo(name = "idRelatorio")
    public int idRelatorio;

    @NonNull
    public String nc;
}
