package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "avaliacoesAmbientaisResultado",
        indices = {@Index(value="idRelatorio", unique = false) },

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

    @NonNull
    @ColumnInfo(name = "sexo", defaultValue = Identificadores.SEXO_OMISSAO)
    public int sexo;

    @NonNull
    @ColumnInfo(name = "tipoIluminacao", defaultValue = Identificadores.SEM_VALOR)
    public int tipoIluminacao;

    @NonNull
    @ColumnInfo(name = "emedioLx", defaultValue = Identificadores.SEM_VALOR)
    public int emedioLx;

    @NonNull
    @ColumnInfo(name = "eLxArea", defaultValue = Identificadores.SEM_VALOR)
    public int eLxArea;

    public String eLx;

    @NonNull
    @ColumnInfo(name = "idElx", defaultValue = Identificadores.SEM_VALOR)
    public int idElx;


    //temperatura e humidade

    @NonNull
    @ColumnInfo(name = "temperatura", defaultValue = Identificadores.VALOR_0)
    public double temperatura;

    @NonNull
    @ColumnInfo(name = "humidadeRelativa", defaultValue = Identificadores.VALOR_0)
    public double humidadeRelativa;

    @NonNull
    @ColumnInfo(name = "homens", defaultValue = Identificadores.VALOR_0)
    public int homens;

    @NonNull
    @ColumnInfo(name = "mulheres", defaultValue = Identificadores.VALOR_0)
    public int mulheres;


    public AvaliacaoAmbientalResultado(int idRelatorio, int id, int idArea, String anexoArea, String nome, int sexo, int tipoIluminacao, int emedioLx, int eLxArea, String eLx, int idElx, double temperatura, double humidadeRelativa, int homens, int mulheres) {
        this.idRelatorio = idRelatorio;
        this.id = id;
        this.idArea = idArea;
        this.anexoArea = anexoArea;
        this.nome = nome;
        this.sexo = sexo;
        this.tipoIluminacao = tipoIluminacao;
        this.emedioLx = emedioLx;
        this.eLxArea = eLxArea;
        this.eLx = eLx;
        this.idElx = idElx;
        this.temperatura = temperatura;
        this.humidadeRelativa = humidadeRelativa;
        this.homens = homens;
        this.mulheres = mulheres;
    }

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
    public AvaliacaoAmbientalResultado(int idRelatorio, int idArea, String anexoArea, int homens, int mulheres, double temperatura, double humidadeRelativa) {

        this.idRelatorio = idRelatorio;
        this.idArea = idArea;
        this.anexoArea = anexoArea;

        this.homens = homens;
        this.mulheres = mulheres;
        this.temperatura = temperatura;
        this.humidadeRelativa = humidadeRelativa;
    }
}
