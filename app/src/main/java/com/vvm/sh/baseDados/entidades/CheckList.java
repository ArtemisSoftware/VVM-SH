package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "checklist")
public class CheckList {


    @PrimaryKey
    public int id;

    @NonNull
    public String descricao;

    @NonNull
    public String versao;


}
