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

@Entity(tableName = "trabalhadoresVulneraveisResultado",
        indices = {@Index(value="idAtividade", unique = false) },

        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "idAtividade",
                onDelete = CASCADE))
public class TrabalhadorVulneravelResultado {

    @NonNull
    @ColumnInfo(name = "idAtividade")
    public int idAtividade;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public int idVulnerabilidade;

    @NonNull
    @ColumnInfo(name = "homens", defaultValue = Identificadores.VALOR_0)
    public int homens;

    @NonNull
    @ColumnInfo(name = "mulheres", defaultValue = Identificadores.VALOR_0)
    public int mulheres;

    @NonNull
    @ColumnInfo(name = "origem" , defaultValue = Identificadores.Origens.ORIGEM_WS + "")
    public int origem;

    @Ignore
    public TrabalhadorVulneravelResultado(int idAtividade, int idVulnerabilidade, int homens, int mulheres) {
        this.idAtividade = idAtividade;
        this.idVulnerabilidade = idVulnerabilidade;
        this.homens = homens;
        this.mulheres = mulheres;
    }


    public TrabalhadorVulneravelResultado(int idAtividade, int id, int idVulnerabilidade, int homens, int mulheres, int origem) {
        this.idAtividade = idAtividade;
        this.id = id;
        this.idVulnerabilidade = idVulnerabilidade;
        this.homens = homens;
        this.mulheres = mulheres;
        this.origem = origem;
    }
}
