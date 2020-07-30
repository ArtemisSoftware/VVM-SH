package com.vvm.sh.ui.atividadesPendentes.relatorios;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "acoesFormacaoResultado",
        indices = {@Index(value="idAtividade", unique = false) },
        primaryKeys = {"idAtividade"},
        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "idAtividade",
                onDelete = CASCADE))
public class AcaoFormacao {


    @NonNull
    public int idAtividade;

    @NonNull
    @ColumnInfo(name = "idDesignacao")
    public int idDesignacao;

    @NonNull
    @ColumnInfo(name = "local")
    public String local;

    @NonNull
    @ColumnInfo(name = "data")
    public Date data;


    @NonNull
    @ColumnInfo(name = "inicio")
    public Date inicio;

    @NonNull
    @ColumnInfo(name = "termino")
    public Date termino;


    public AcaoFormacao(int idAtividade, int idDesignacao, @NonNull String local, @NonNull Date data, @NonNull Date inicio, @NonNull Date termino) {
        this.idAtividade = idAtividade;
        this.idDesignacao = idDesignacao;
        this.local = local;
        this.data = data;
        this.inicio = inicio;
        this.termino = termino;
    }
}
