package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "itensChecklist",

        primaryKeys = {"idChecklist", "idArea", "idSeccao", "uid"},
        foreignKeys = @ForeignKey(entity = CheckList.class,
                parentColumns = "id",
                childColumns = "idChecklist",
                onDelete = CASCADE))
public class ItemChecklist {


    @NonNull
    public int idChecklist;

    @NonNull
    public int idArea;


    @NonNull
    public String idSeccao;


    @NonNull
    public String uid;

    public String descricao;

    @NonNull
    public String tipo;


    public String codigo;



}

