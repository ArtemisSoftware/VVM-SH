package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "colaboradoresResultado",
        primaryKeys = {"id"},
        foreignKeys = @ForeignKey(entity = Colaborador.class,
                parentColumns = "id",
                childColumns = "id",
                onDelete = CASCADE))
public class ColaboradorResultado {

    @NonNull
    public String id;


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



    //TODO: verificar estes tipos

    @ColumnInfo(name = "dataAdmissao")
    public Date dataAdmissao;

    @ColumnInfo(name = "dataAdmissaoFuncao")
    public Date dataAdmissaoFuncao;

    @ColumnInfo(name = "idCategoriaProfissional")
    public int idCategoriaProfissional;

    @ColumnInfo(name = "profissao")
    public String profissao;

    @ColumnInfo(name = "posto")
    public String posto;

    @ColumnInfo(name = "origem")
    public int origem;

}
