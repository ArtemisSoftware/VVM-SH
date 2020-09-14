package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "medidasResultado")
public class MedidaResultado {

    @NonNull
    @PrimaryKey
    public int id;

    @NonNull
    @PrimaryKey
    public int origem;

    @NonNull
    @PrimaryKey
    public int idMedida;


}
