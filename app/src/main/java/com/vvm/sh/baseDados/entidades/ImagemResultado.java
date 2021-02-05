package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Sintaxe;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "imagensResultado",
        indices = {@Index(value="idTarefa", unique = false) },

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class ImagemResultado {

    @NonNull
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int idImagem;


    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "origem")
    public int origem;

    @NonNull
    @ColumnInfo(name = "capaRelatorio", defaultValue = Sintaxe.Codigos.NAO_SELECIONADO)
    public boolean capaRelatorio;


    @NonNull
    @ColumnInfo(name = "imagem" , typeAffinity = ColumnInfo.BLOB)
    public byte[] imagem;


    @NonNull
    @ColumnInfo(name = "criado", defaultValue = "CURRENT_TIMESTAMP")
    public Date criado;



    public ImagemResultado(int idTarefa, int id, int origem, @NonNull byte[] imagem) {
        this.idTarefa = idTarefa;
        this.id = id;
        this.origem = origem;
        this.imagem = imagem;
        this.criado = new Date();
    }

}
