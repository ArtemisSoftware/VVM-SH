package com.vvm.sh.ui.opcoes.modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "atualizacoes")
public class Atualizacao {


    @PrimaryKey
    @NonNull
    private String descricao;

    @ColumnInfo(name = "seloTemporal")
    private String seloTemporal;


    @Ignore
    public Atualizacao() {

    }


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

    public void setDescricao(@NonNull String descricao) {
        this.descricao = descricao;
    }

    public void setSeloTemporal(String seloTemporal) {
        this.seloTemporal = seloTemporal;
    }
}
