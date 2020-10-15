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

@Entity(tableName = "levantamentosRiscoResultado",
        indices = {@Index(value="idAtividade", unique = false) },

        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "idAtividade",
                onDelete = CASCADE))
public class LevantamentoRiscoResultado {

    @NonNull
    @ColumnInfo(name = "idAtividade")
    public int idAtividade;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    @NonNull
    @ColumnInfo(name = "tarefa")
    public String tarefa;

    @NonNull
    @ColumnInfo(name = "perigo")
    public String perigo;

    @ColumnInfo(name = "idModelo")
    public int idModelo;



    @NonNull
    public int origem;


    public LevantamentoRiscoResultado(int idAtividade, int id, String tarefa, String perigo, int idModelo, int origem) {
        this.idAtividade = idAtividade;
        this.id = id;
        this.tarefa = tarefa;
        this.perigo = perigo;
        this.idModelo = idModelo;
        this.origem = origem;
    }

    @Ignore
    public LevantamentoRiscoResultado(int idAtividade, String tarefa, String perigo) {
        this.idAtividade = idAtividade;
        this.tarefa = tarefa;
        this.perigo = perigo;
        this.origem = Identificadores.Origens.ORIGEM_BD;
    }

    @Ignore
    public LevantamentoRiscoResultado(LevantamentoRiscoResultado levantamento) {
        this.idAtividade = levantamento.idAtividade;
        this.tarefa = levantamento.tarefa;
        this.perigo = levantamento.perigo;
        this.origem = Identificadores.Origens.ORIGEM_BD;
    }
}
