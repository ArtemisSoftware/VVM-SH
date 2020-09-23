package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "questionarioChecklistResultado",
        indices = {@Index(value="idArea", unique = false) },
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



    public int ut1;
    public int ut1_CategoriasRisco;
    public boolean ut1_LocalRisco_A;
    public boolean ut1_LocalRisco_B;
    public boolean ut1_LocalRisco_C;
    public boolean ut1_LocalRisco_D;
    public boolean ut1_LocalRisco_E;
    public boolean ut1_LocalRisco_F;

    public int ut2;
    public int ut2_CategoriasRisco;
    public boolean ut2_LocalRisco_A;
    public boolean ut2_LocalRisco_B;
    public boolean ut2_LocalRisco_C;
    public boolean ut2_LocalRisco_D;
    public boolean ut2_LocalRisco_E;
    public boolean ut2_LocalRisco_F;

    public String observacao;


    @NonNull
    public int origem;



}
