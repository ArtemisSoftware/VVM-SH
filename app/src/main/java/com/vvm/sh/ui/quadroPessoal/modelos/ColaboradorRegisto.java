package com.vvm.sh.ui.quadroPessoal.modelos;

import androidx.room.Ignore;

import com.vvm.sh.util.interfaces.EstadoModelo;

public class ColaboradorRegisto implements EstadoModelo {


    public int id, origem, idResultado, idCategoriaProfissional;
    public String nome, idMorada, morada, estado, posto, idRegisto, sexo, categoriaProfissional, profissao;

    @Ignore
    public String  nacionalidade;


    public long dataNascimento, dataAdmissaoFuncao, dataAdmissao;
    //private String idMorada, morada, idColaborador, posto, dataNascimento, dataAdmissao, dataAdmissaoFuncao, genero, categoriaProfissional, idCategoriaProfissional, situacao, profissao, nacionalidade;
    //private int imagemSituacao, origem, corOrigem;


    @Ignore
    public int estadoModelo;

    @Ignore
    public ColaboradorRegisto(int estadoModelo){
        this.estadoModelo = estadoModelo;
    }

    public ColaboradorRegisto(int id, String nome, String idMorada, String morada, String estado, int origem,
                              String posto, String idRegisto, int idResultado, long dataNascimento, String sexo,
                              int idCategoriaProfissional, String categoriaProfissional, String profissao,
                              long dataAdmissao, long dataAdmissaoFuncao/*, String nacionalidade,
                              ,
                              String situacao,
                        String categoriaProfissional, String idCategoriaProfissional,
                       */){


        this.estadoModelo = EstadoModelo.MODELO;
        this.id = id;
        this.nome = nome;
        this.idMorada = idMorada;
        this.morada = morada;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.estado = estado;
        this.nacionalidade = nacionalidade;
        this.origem = origem;
        this.posto = posto;
        this.idRegisto = idRegisto;
        this.idResultado = idResultado;

        this.idCategoriaProfissional = idCategoriaProfissional;
        this.categoriaProfissional = categoriaProfissional;
        this.profissao = profissao;
        this.dataAdmissao = dataAdmissao;
        this.dataAdmissaoFuncao = dataAdmissaoFuncao;

    }

    @Override
    public int obterEstado() {
        return estadoModelo;
    }

}
