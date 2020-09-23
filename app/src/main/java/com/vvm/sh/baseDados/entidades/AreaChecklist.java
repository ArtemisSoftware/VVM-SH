package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "areasChecklist",
        indices = {@Index(value="idChecklist", unique = false) },
        primaryKeys = {"idChecklist", "idArea"},
        foreignKeys = @ForeignKey(entity = CheckList.class,
                parentColumns = "id",
                childColumns = "idChecklist",
                onDelete = CASCADE))
public class AreaChecklist {


    @NonNull
    public int idChecklist;

    @NonNull
    public int idArea;

    @NonNull
    public String descricao;

}
