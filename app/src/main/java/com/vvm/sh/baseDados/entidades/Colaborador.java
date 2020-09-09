package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "colaboradores",
        indices = {@Index(value="idTarefa", unique = false) },
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class Colaborador {

    @NonNull
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    @NonNull
    @ColumnInfo(name = "idColaborador")
    public String idColaborador;

    @NonNull
    @ColumnInfo(name = "idMorada")
    public String idMorada;

    @NonNull
    @ColumnInfo(name = "nome")
    public String nome;

    @NonNull
    @ColumnInfo(name = "estado")
    public String estado;



    @NonNull
    @ColumnInfo(name = "sexo")
    public String sexo;

    @NonNull
    @ColumnInfo(name = "dataNascimento")
    public Date dataNascimento;

    @NonNull
    @ColumnInfo(name = "nacionalidade")
    public String nacionalidade;


}
