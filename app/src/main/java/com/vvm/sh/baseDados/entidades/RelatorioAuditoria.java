package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "relatorioAuditoria",


        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class RelatorioAuditoria {

    @NonNull
    @ColumnInfo(name = "idTarefa")
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public Date data;


    @NonNull
    public String descricao;

    @NonNull
    public int idChecklist;

    @NonNull
    public int idArea;
}
