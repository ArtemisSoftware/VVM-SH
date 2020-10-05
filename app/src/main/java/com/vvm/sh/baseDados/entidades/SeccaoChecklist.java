package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "seccoesChecklist",

        primaryKeys = {"idChecklist", "idArea", "uid"},
        foreignKeys = @ForeignKey(entity = CheckList.class,
                parentColumns = "id",
                childColumns = "idChecklist",
                onDelete = CASCADE))
public class SeccaoChecklist {



    @NonNull
    public int idChecklist;

    @NonNull
    public int idArea;


    @NonNull
    public String uid;

    @NonNull
    public String descricao;

    @NonNull
    public String tipo;

}
