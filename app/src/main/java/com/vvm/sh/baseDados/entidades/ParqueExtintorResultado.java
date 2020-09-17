package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "parqueExtintoresResultado",
        primaryKeys = {"id"},

        foreignKeys = @ForeignKey(entity = ParqueExtintor.class,
                parentColumns = "id",
                childColumns = "id",
                onDelete = CASCADE))
public class ParqueExtintorResultado {

    @NonNull
    public int id;

    @NonNull
    @ColumnInfo(name = "valido")
    public boolean valido;

    @NonNull
    @ColumnInfo(name = "dataValidade")
    public Date dataValidade;


    public ParqueExtintorResultado(int id, boolean valido, Date dataValidade) {
        this.id = id;
        this.valido = valido;
        this.dataValidade = dataValidade;
    }


    @Ignore
    public ParqueExtintorResultado(int id, Date dataValidade) {
        this.id = id;
        this.valido = true;
        this.dataValidade = dataValidade;
    }
}
