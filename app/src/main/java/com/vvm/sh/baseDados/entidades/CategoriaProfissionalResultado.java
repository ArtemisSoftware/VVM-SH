package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "categoriasProfissionaisResultado")
public class CategoriaProfissionalResultado {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public int idCategoriaProfissional;


    @NonNull
    public int idRegisto;

    @NonNull
    public int origem;


    public int homens;

    public int mulheres;

}
