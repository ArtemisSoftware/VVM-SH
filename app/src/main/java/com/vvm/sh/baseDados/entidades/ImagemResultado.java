package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "imagensResultado",
        indices = {@Index(value="idTarefa", unique = false) },
        primaryKeys = {"id", "origem"},
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class ImagemResultado {

    @NonNull
    public int idTarefa;


    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "origem")
    public int origem;

    @NonNull
    @ColumnInfo(name = "imagem" , typeAffinity = ColumnInfo.BLOB)
    public byte[] imagem;

    public ImagemResultado(int idTarefa, int id, int origem, @NonNull byte[] imagem) {
        this.idTarefa = idTarefa;
        this.id = id;
        this.origem = origem;
        this.imagem = imagem;
    }
}
