package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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


    @NonNull
    public int idArea;


    public String anexoArea;



    //iluminacao

    public String nome;

    public int sexo;

    public int tipoIluminacao;

    public int emedioLx;

    public int eLxArea;

    public String eLx;

    public int idElx;


    //temperatura e humidade


    public double temperatura;

    public double humidadeRelativa;

    public int homens;

    public int mulheres;



    @Ignore
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
        this.emedioLx = Integer.parseInt(emedioLx);
        this.eLxArea = eLxArea;
        this.idElx = idElx;
        this.eLx = eLx;

    }

    @Ignore
    public AvaliacaoAmbientalResultado(int idRealtorio, int idArea, String anexoArea, int homens, int mulheres, double temperatura, double humidadeRelativa) {

        this.idRelatorio = idRelatorio;
        this.idArea = idArea;
        this.anexoArea = anexoArea;

        this.homens = homens;
        this.mulheres = mulheres;
        this.temperatura = temperatura;
        this.humidadeRelativa = humidadeRelativa;
    }
}
