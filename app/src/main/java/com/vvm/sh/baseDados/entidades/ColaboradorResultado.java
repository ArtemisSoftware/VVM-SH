package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Identificadores;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "colaboradoresResultado",
        foreignKeys = @ForeignKey(entity = Colaborador.class,
                parentColumns = "id",
                childColumns = "idRegisto",
                onDelete = CASCADE))
public class ColaboradorResultado {


    @NonNull
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    @ColumnInfo(name = "idRegisto")
    public String idRegisto;

    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "idMorada")
    public String idMorada;

    @ColumnInfo(name = "estado")
    public String estado;

    @ColumnInfo(name = "dataNascimento")
    public Date dataNascimento;


    @ColumnInfo(name = "sexo")
    public String sexo;

    @ColumnInfo(name = "nacionalidade")
    public String nacionalidade;



    @ColumnInfo(name = "dataAdmissao")
    public Date dataAdmissao;

    @ColumnInfo(name = "dataAdmissaoFuncao")
    public Date dataAdmissaoFuncao;

    @NonNull
    @ColumnInfo(name = "idCategoriaProfissional", defaultValue = Identificadores.VALOR_0)
    public int idCategoriaProfissional;

    @ColumnInfo(name = "profissao")
    public String profissao;

    @ColumnInfo(name = "posto")
    public String posto;

    @NonNull
    @ColumnInfo(name = "origem")
    public int origem;

    @Ignore
    public ColaboradorResultado(int idTarefa, int id, String idMorada, String posto, int origem) {

        this.idTarefa = idTarefa;
        this.idRegisto = id + "";
        this.idMorada = idMorada;
        this.posto = posto;
        this.origem = origem;
    }

    @Ignore
    public ColaboradorResultado(int idTarefa, int id, String estado, int origem) {

        this.idTarefa = idTarefa;
        this.idRegisto = id + "";
        this.estado = estado;
        this.origem = origem;
    }




    public ColaboradorResultado(int idTarefa, int id, String idRegisto, String nome, String idMorada,
                                String estado, Date dataNascimento, String sexo, String nacionalidade,
                                Date dataAdmissao, Date dataAdmissaoFuncao, int idCategoriaProfissional,
                                String profissao, String posto, int origem) {
        this.idTarefa = idTarefa;
        this.id = id;
        this.idRegisto = idRegisto;
        this.nome = nome;
        this.idMorada = idMorada;
        this.estado = estado;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.nacionalidade = nacionalidade;
        this.dataAdmissao = dataAdmissao;
        this.dataAdmissaoFuncao = dataAdmissaoFuncao;
        this.idCategoriaProfissional = idCategoriaProfissional;
        this.profissao = profissao;
        this.posto = posto;
        this.origem = origem;
    }
}
