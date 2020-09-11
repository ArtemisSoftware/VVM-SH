package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "avaliacoesAmbientaisResultado",
        indices = {@Index(value="idAtividade", unique = false) },

        foreignKeys = @ForeignKey(entity = RelatorioAmbientalResultado.class,
                parentColumns = "id",
                childColumns = "idRelatorio",
                onDelete = CASCADE))
public class AvaliacaoAmbientalResultado {


    @NonNull
    @ColumnInfo(name = "idRelatorio")
    public int idRelatorio;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    //TODO:Verificar

    @NonNull
    public String idArea;

    @NonNull
    public String anexoArea;

    @NonNull
    public String nome;

    @NonNull
    public String sexo;

    @NonNull
    public String tipoIluminacao;

    @NonNull
    public String emedioLx;

    @NonNull
    public String eLxArea;

    @NonNull
    public String eLx;

    @NonNull
    public String idElx;

    @NonNull
    public String temperatura;

    @NonNull
    public String humidadeRelativa;

    @NonNull
    public int homens;


    @NonNull
    public int mulheres;



}
