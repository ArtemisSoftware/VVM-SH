package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "planoAcao",
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class PlanoAcao {

    @PrimaryKey
    @NonNull
    public int idTarefa;


    @NonNull
    @ColumnInfo(name = "anuidade")
    public String anuidade;


    @ColumnInfo(name = "tst")
    public String tst;


    @ColumnInfo(name = "cap")
    public String cap;

    @ColumnInfo(name = "email")
    public String email;



}
