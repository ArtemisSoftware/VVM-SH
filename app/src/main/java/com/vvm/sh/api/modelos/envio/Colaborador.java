package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

public class Colaborador {

    @SerializedName("idColaborador")
    public String idColaborador;

    @SerializedName("nomeColaborador")
    public String nome;

    @SerializedName("dataNascimento")
    public String dataNascimento;

    @SerializedName("dataAdmissao")
    public String dataAdmissao;

    @SerializedName("dataAdmissaoFuncao")
    public String dataAdmissaoFuncao;

    @SerializedName("idCategoriaProfissional")
    public String idCategoriaProfissional;

    @SerializedName("genero")
    public String genero;

    @SerializedName("IdMorada")
    public String idMorada;

    @SerializedName("estado")
    public String estado;

    @SerializedName("posto")
    public String posto;

    @SerializedName("profissao")
    public String profissao;

}
