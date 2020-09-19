package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "registoVisitaResultado",

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class RegistoVisitaResultado {


    @PrimaryKey
    public int idTarefa;

    @NonNull
    public String recebidoPor;

    @NonNull
    public String funcao;

    @NonNull
    public String observacao;


    @NonNull
    public int sincronizacao;



}
