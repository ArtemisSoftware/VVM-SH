package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Identificadores;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "questionarioChecklistResultado",

        foreignKeys = @ForeignKey(entity = AreaChecklistResultado.class,
                parentColumns = "id",
                childColumns = "idArea",
                onDelete = CASCADE))
public class QuestionarioChecklistResultado {


    @NonNull
    public int idArea;

    @NonNull
    public String idSeccao;

    @NonNull
    public String idItem;



    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String tipo;



    public String resposta;
    public String nr;
    public String ni;


    @NonNull
    @ColumnInfo(name = "ut1", defaultValue = Identificadores.VALOR_0)
    public int ut1;

    @NonNull
    @ColumnInfo(name = "ut1_CategoriasRisco", defaultValue = Identificadores.VALOR_0)
    public int ut1_CategoriasRisco;

    @NonNull
    @ColumnInfo(name = "ut1_LocalRisco_A", defaultValue = Identificadores.VALOR_0)
    public boolean ut1_LocalRisco_A;

    @NonNull
    @ColumnInfo(name = "ut1_LocalRisco_B", defaultValue = Identificadores.VALOR_0)
    public boolean ut1_LocalRisco_B;

    @NonNull
    @ColumnInfo(name = "ut1_LocalRisco_C", defaultValue = Identificadores.VALOR_0)
    public boolean ut1_LocalRisco_C;

    @NonNull
    @ColumnInfo(name = "ut1_LocalRisco_D", defaultValue = Identificadores.VALOR_0)
    public boolean ut1_LocalRisco_D;

    @NonNull
    @ColumnInfo(name = "ut1_LocalRisco_E", defaultValue = Identificadores.VALOR_0)
    public boolean ut1_LocalRisco_E;

    @NonNull
    @ColumnInfo(name = "ut1_LocalRisco_F", defaultValue = Identificadores.VALOR_0)
    public boolean ut1_LocalRisco_F;

    @NonNull
    @ColumnInfo(name = "ut2", defaultValue = Identificadores.VALOR_0)
    public int ut2;

    @NonNull
    @ColumnInfo(name = "ut2_CategoriasRisco", defaultValue = Identificadores.VALOR_0)
    public int ut2_CategoriasRisco;

    @NonNull
    @ColumnInfo(name = "ut2_LocalRisco_A", defaultValue = Identificadores.VALOR_0)
    public boolean ut2_LocalRisco_A;

    @NonNull
    @ColumnInfo(name = "ut2_LocalRisco_B", defaultValue = Identificadores.VALOR_0)
    public boolean ut2_LocalRisco_B;

    @NonNull
    @ColumnInfo(name = "ut2_LocalRisco_C", defaultValue = Identificadores.VALOR_0)
    public boolean ut2_LocalRisco_C;

    @NonNull
    @ColumnInfo(name = "ut2_LocalRisco_D", defaultValue = Identificadores.VALOR_0)
    public boolean ut2_LocalRisco_D;

    @NonNull
    @ColumnInfo(name = "ut2_LocalRisco_E", defaultValue = Identificadores.VALOR_0)
    public boolean ut2_LocalRisco_E;

    @NonNull
    @ColumnInfo(name = "ut2_LocalRisco_F", defaultValue = Identificadores.VALOR_0)
    public boolean ut2_LocalRisco_F;

    public String observacao;


    @NonNull
    public int origem;



}
