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

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "formandosResultado",
        indices = {@Index(value="idAtividade", unique = false) },

        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "idAtividade",
                onDelete = CASCADE))
public class FormandoResultado {


    @NonNull
    @ColumnInfo(name = "idAtividade")
    public int idAtividade;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    @NonNull
    @ColumnInfo(name = "nome")
    public String nome;

    @NonNull
    @ColumnInfo(name = "biCartaoCidadao")
    public String biCartaoCidadao;

    @NonNull
    @ColumnInfo(name = "sexo")
    public String sexo;

    @NonNull
    @ColumnInfo(name = "niss")
    public String niss;

    @NonNull
    @ColumnInfo(name = "dataNascimento")
    public Date dataNascimento;

    @NonNull
    @ColumnInfo(name = "naturalidade")
    public String naturalidade;

    @NonNull
    @ColumnInfo(name = "nacionalidade")
    public String nacionalidade;

    @NonNull
    @ColumnInfo(name = "selecionado", defaultValue = Sintaxe.Codigos.NAO_SELECIONADO)
    public boolean selecionado;

    @NonNull
    @ColumnInfo(name = "origem", defaultValue = Identificadores.Origens.ORIGEM_BD + "")
    public int origem;

    @Ignore
    public FormandoResultado() {
    }

    @Ignore
    public FormandoResultado(int idAtividade,
                             @NonNull String nome, @NonNull String biCartaoCidadao, @NonNull String sexo,
                             @NonNull String niss, @NonNull Date dataNascimento, @NonNull String naturalidade,
                             @NonNull String nacionalidade) {

        this.idAtividade = idAtividade;
        this.nome = nome;
        this.biCartaoCidadao = biCartaoCidadao;
        this.sexo = sexo;
        this.niss = niss;
        this.dataNascimento = dataNascimento;
        this.naturalidade = naturalidade;
        this.nacionalidade = nacionalidade;
        this.origem = Identificadores.Origens.ORIGEM_BD;
    }


    @Ignore
    public FormandoResultado(int idAtividade, int id,
                             @NonNull String nome, @NonNull String biCartaoCidadao, @NonNull String sexo,
                             @NonNull String niss, @NonNull Date dataNascimento, @NonNull String naturalidade,
                             @NonNull String nacionalidade) {

        this.idAtividade = idAtividade;
        this.id = id;
        this.nome = nome;
        this.biCartaoCidadao = biCartaoCidadao;
        this.sexo = sexo;
        this.niss = niss;
        this.dataNascimento = dataNascimento;
        this.naturalidade = naturalidade;
        this.nacionalidade = nacionalidade;
        this.origem = Identificadores.Origens.ORIGEM_BD;
    }


    public FormandoResultado(int idAtividade, int id,
                             @NonNull String nome, @NonNull String biCartaoCidadao, @NonNull String sexo,
                             @NonNull String niss, @NonNull Date dataNascimento, @NonNull String naturalidade,
                             @NonNull String nacionalidade, boolean selecionado, int origem) {

        this.idAtividade = idAtividade;
        this.id = id;
        this.nome = nome;
        this.biCartaoCidadao = biCartaoCidadao;
        this.sexo = sexo;
        this.niss = niss;
        this.dataNascimento = dataNascimento;
        this.naturalidade = naturalidade;
        this.nacionalidade = nacionalidade;
        this.selecionado = selecionado;
        this.origem = origem;
    }

}
