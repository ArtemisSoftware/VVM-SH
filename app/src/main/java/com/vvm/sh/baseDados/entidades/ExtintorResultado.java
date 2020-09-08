package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "extintoresResultado",
        indices = {@Index(value="idTarefa", unique = false) },


        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class ExtintorResultado {

    @NonNull
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "valido")
    public boolean valido;

    @ColumnInfo(name = "dataValidade")
    public Date dataValidade;


    public ExtintorResultado(int idTarefa, boolean valido, Date dataValidade) {
        this.idTarefa = idTarefa;
        this.valido = valido;
        this.dataValidade = dataValidade;
    }
}
