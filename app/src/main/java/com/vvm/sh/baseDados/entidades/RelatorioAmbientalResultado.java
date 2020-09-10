package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "relatorioAmbientalResultado",
        indices = {@Index(value="idAtividade", unique = false) },

        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "idAtividade",
                onDelete = CASCADE))
public class RelatorioAmbientalResultado {


    @NonNull
    @ColumnInfo(name = "idAtividade")
    public int idAtividade;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public int tipo;


    //TODO:Verificar

    @NonNull
    public String marca;

    @NonNull
    public String numeroSerie;

    @NonNull
    public Date data;

    @NonNull
    public Date inicio;

    @NonNull
    public Date termino;

    @NonNull
    public int idNebulosidade;

    @NonNull
    public String medidaRecomendada;

}
