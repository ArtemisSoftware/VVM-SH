package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "medidasResultado", primaryKeys = {"id", "idMedida", "origem"})
public class MedidaResultado {

    @NonNull
    public int id;

    @NonNull
    public int origem;

    @NonNull
    public int idMedida;


}
