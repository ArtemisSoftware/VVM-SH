package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.util.interfaces.EstadoModelo;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "tipos",
        indices = {@Index(value="tipo", unique = false) },
        primaryKeys = {"id","tipo","api"},
        foreignKeys = {
            @ForeignKey(entity = Atualizacao.class,
                        parentColumns = "descricao",
                        childColumns = "tipo",
                        onDelete = CASCADE),

//                @ForeignKey(entity = Atualizacao.class,
//                        parentColumns = "api",
//                        childColumns = "api",
//                        onDelete = CASCADE)
        }
)

public class Tipo implements EstadoModelo {

    @NonNull
    public int id;


    @NonNull
    public String tipo;

    @NonNull
    public int api;

    @NonNull
    @ColumnInfo(name = "descricao")
    public String descricao;

    @NonNull
    @ColumnInfo(name = "codigo")
    public String codigo;

    @NonNull
    @ColumnInfo(name = "idPai")
    public String idPai;

    @NonNull
    @ColumnInfo(name = "ativo")
    public int ativo;

    @NonNull
    @ColumnInfo(name = "detalhe")
    public String detalhe;





    @Ignore
    public Tipo(){}

    @Ignore
    public Tipo(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }




    @Ignore
    public Tipo(int id, String descricao, String codigo) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
    }

    @Ignore
    public Tipo(int id, String descricao, String codigo, String idPai) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
        this.idPai = idPai;
    }


    @Ignore
    public Tipo(int id, String descricao, String codigo, String idPai, int ativo, String detalhe, String tipo) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
        this.idPai = idPai;
        this.ativo = ativo;
        this.detalhe = detalhe;
        this.tipo = tipo;
    }

    public Tipo(int id, String descricao, String codigo, String idPai, int ativo, String detalhe, String tipo, int api) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
        this.idPai = idPai;
        this.ativo = ativo;
        this.detalhe = detalhe;
        this.tipo = tipo;
        this.api = api;

        this.estadoModelo = EstadoModelo.MODELO;
    }


    public int obterId() {
        return id;
    }

    public String obterDescricao() {
        return descricao;
    }

    public String obterCodigo() {
        return codigo;
    }

    public String obterIdPai() {
        return idPai;
    }

    public int obterAtivo() {
        return ativo;
    }

    public String obterDetalhe() {
        return detalhe;
    }

    public String obterTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return descricao.replaceAll("(\\r|\\n|\\t)", "");
    }


    @Ignore
    public int estadoModelo;

    @Ignore
    public Tipo(int estadoModelo){
        this.estadoModelo = estadoModelo;
    }


    @Override
    public int obterEstado() {
        return estadoModelo;
    }
}
