package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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


    public RelatorioAmbientalResultado(int idAtividade, int id, int tipo, @NonNull String marca, @NonNull String numeroSerie, @NonNull Date data, @NonNull Date inicio, @NonNull Date termino, int idNebulosidade) {
        this.idAtividade = idAtividade;
        this.id = id;
        this.tipo = tipo;
        this.marca = marca;
        this.numeroSerie = numeroSerie;
        this.data = data;
        this.inicio = inicio;
        this.termino = termino;
        this.idNebulosidade = idNebulosidade;
    }

    @Ignore
    public RelatorioAmbientalResultado(int idAtividade, int tipo, @NonNull String marca, @NonNull String numeroSerie,
                                       @NonNull Date data, @NonNull Date inicio, @NonNull Date termino, Tipo nebulosidade) {
        this.idAtividade = idAtividade;
        this.tipo = tipo;
        this.marca = marca;
        this.numeroSerie = numeroSerie;
        this.data = data;
        this.inicio = inicio;
        this.termino = termino;
        this.idNebulosidade = nebulosidade.id;
    }
}
