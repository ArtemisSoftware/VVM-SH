package com.vvm.sh.ui.atividadesPendentes.relatorios;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.CheckBoxIF;
import com.vvm.sh.util.metodos.Conversor;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "formandosResultado",
        indices = {@Index(value="idAtividade", unique = false) },

        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "idAtividade",
                onDelete = CASCADE))
public class Formando {


    @NonNull
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
    @ColumnInfo(name = "selecionado", defaultValue = "0")
    public boolean selecionado;

    @NonNull
    @ColumnInfo(name = "origem", defaultValue = "1")
    public int origem;


    public Formando(int idAtividade, int id,
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
