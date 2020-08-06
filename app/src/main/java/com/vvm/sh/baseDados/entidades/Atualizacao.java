package com.vvm.sh.baseDados.entidades;

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
    public String descricao;

    @ColumnInfo(name = "seloTemporal")
    public String seloTemporal;


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
