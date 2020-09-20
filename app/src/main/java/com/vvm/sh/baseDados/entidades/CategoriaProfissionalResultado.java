package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Identificadores;

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


    @NonNull
    @ColumnInfo(name = "homens", defaultValue = Identificadores.VALOR_0)
    public int homens;

    @NonNull
    @ColumnInfo(name = "mulheres", defaultValue = Identificadores.VALOR_0)
    public int mulheres;


    public CategoriaProfissionalResultado(int id, int idCategoriaProfissional, int idRegisto, int origem, int homens, int mulheres) {
        this.id = id;
        this.idCategoriaProfissional = idCategoriaProfissional;
        this.idRegisto = idRegisto;
        this.origem = origem;
        this.homens = homens;
        this.mulheres = mulheres;
    }

    @Ignore
    public CategoriaProfissionalResultado(int idRegisto, int idCategoriaProfissional, int origem) {
        this.idRegisto = idRegisto;
        this.idCategoriaProfissional = idCategoriaProfissional;
        this.origem = origem;
    }


    @Ignore
    public CategoriaProfissionalResultado(int id, int homens, int mulheres, int origem) {
        this.id = id;
        this.homens = homens;
        this.mulheres = mulheres;
        this.origem = origem;
    }
}
