package com.vvm.sh.ui.opcoes.modelos;

import androidx.room.ColumnInfo;

import static androidx.room.ForeignKey.CASCADE;


//@Entity(tableName = "tipos", primaryKeys = {"id","descricao"}
//        foreignKeys = @ForeignKey(entity = Atualizacao.class,
//                                    parentColumns = "id",
//                                    childColumns = "userId",
//
public class Tipo {


    public int id;
    public String descricao;

//    @ColumnInfo(name = "codigo")
    public String codigo;

//    @ColumnInfo(name = "idPai")
    public String idPai;

//    @ColumnInfo(name = "ativo")
    public int ativo;

//    @ColumnInfo(name = "detalhe")
    public boolean detalhe;

//    @ColumnInfo(name = "tipo")
    public String tipo;


    public Tipo(int id, String descricao, String codigo, String idPai, int ativo, boolean detalhe, String tipo) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
        this.idPai = idPai;
        this.ativo = ativo;
        this.detalhe = detalhe;
        this.tipo = tipo;
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

    public boolean obterDetalhe() {
        return detalhe;
    }

    public String obterTipo() {
        return tipo;
    }
}
