package com.vvm.sh.api.modelos.bd;

import androidx.room.ColumnInfo;

public class ColaboradorBd {


    @ColumnInfo(name = "id")
    public String id;


    @ColumnInfo(name = "nome")
    public String nome;


    @ColumnInfo(name = "idMorada")
    public String idMorada;


    @ColumnInfo(name = "sexo")
    public String sexo;

    @ColumnInfo(name = "estado")
    public String estado;

    @ColumnInfo(name = "dataNascimento")
    public long dataNascimento;

    @ColumnInfo(name = "dataAdmissao")
    public long dataAdmissao;

    @ColumnInfo(name = "dataAdmissaoFuncao")
    public long dataAdmissaoFuncao;

    @ColumnInfo(name = "idCategoriaProfissional")
    public String idCategoriaProfissional;

    @ColumnInfo(name = "posto")
    public String posto;

    @ColumnInfo(name = "profissao")
    public String profissao;
}
