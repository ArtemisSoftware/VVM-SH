package com.vvm.sh.ui.opcoes.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "atualizacoes")
public class Atualizacao {


    @PrimaryKey
    private String descricao;

    @ColumnInfo(name = "seloTemporal")
    private String seloTemporal;


    public Atualizacao(String descricao, String seloTemporal) {
        this.descricao = descricao;
        this.seloTemporal = seloTemporal;
    }


    public String obterDescricao() {
        return descricao;
    }



    public String obterSeloTemporal() {
        return seloTemporal;
    }
}
