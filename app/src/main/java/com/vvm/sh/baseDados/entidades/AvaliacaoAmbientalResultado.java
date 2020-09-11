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
    public int idArea;

    @NonNull
    public String anexoArea;

    @NonNull
    public String nome;

    @NonNull
    public int sexo;

    @NonNull
    public int tipoIluminacao;

    @NonNull
    public String emedioLx;

    @NonNull
    public int eLxArea;

    @NonNull
    public String eLx;

    @NonNull
    public int idElx;

    @NonNull
    public String temperatura;

    @NonNull
    public String humidadeRelativa;

    @NonNull
    public int homens;


    @NonNull
    public int mulheres;


    public AvaliacaoAmbientalResultado(int idRelatorio,
                                       @NonNull int idArea, @NonNull String anexoArea, @NonNull String nome,
                                       @NonNull int sexo, @NonNull int tipoIluminacao,
                                       @NonNull String emedioLx, @NonNull int eLxArea, @NonNull int idElx, @NonNull String eLx) {
        this.idRelatorio = idRelatorio;
        this.idArea = idArea;
        this.anexoArea = anexoArea;
        this.nome = nome;
        this.sexo = sexo;
        this.tipoIluminacao = tipoIluminacao;
        this.emedioLx = emedioLx;
        this.eLxArea = eLxArea;
        this.idElx = idElx;
        this.eLx = eLx;

    }
}
