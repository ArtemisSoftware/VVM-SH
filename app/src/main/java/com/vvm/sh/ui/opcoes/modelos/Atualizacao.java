package com.vvm.sh.ui.opcoes.modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "atualizacoes")
public class Atualizacao {


    @PrimaryKey
    @NonNull
    private String descricao;

    @ColumnInfo(name = "seloTemporal")
    private String seloTemporal;


    public Atualizacao(String descricao, String seloTemporal) {
        this.descricao = descricao;
        this.seloTemporal = seloTemporal;
    }


    public String getDescricao() {
        return descricao;
    }

    public String getSeloTemporal() {
        return seloTemporal;
    }
}
