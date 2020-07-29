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
    @ColumnInfo(name = "selecionado")
    public boolean selecionado;

    @NonNull
    @ColumnInfo(name = "origem", defaultValue = "1")
    public int origem;


//    CAMPO_ID_ATIVIDADE_PENDENTE +" INTEGER NOT NULL," +
//    CAMPO_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
//    CAMPO_NOME +" TEXT," +
//    CAMPO_BI_CARTAO_CIDADAO + " TEXT," +
//    CAMPO_SEXO + " TEXT," +
//    CAMPO_NISS + " TEXT," +
//    CAMPO_DATA_NASCIMENTO + " DATETIME," +
//    CAMPO_NATURALIDADE + " TEXT," +
//    CAMPO_NACIONALIDADE + " TEXT," +
//    CAMPO_SELECIONADO + " INTEGER DEFAULT 0," +
//    CAMPO_ID_QUADRO_PESSOAL + " INTEGER," +
//    CAMPO_ORIGEM + " INTEGER DEFAULT " + IdentificadoresIF.ORIGEM_DADOS_WS + ", " +


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
