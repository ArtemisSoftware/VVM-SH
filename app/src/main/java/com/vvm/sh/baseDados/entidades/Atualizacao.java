package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "atualizacoes",
        indices = {@Index(value="descricao", unique = false) },
        primaryKeys = {"descricao"})
public class Atualizacao {


    @NonNull
    public String descricao;

    @NonNull
    public int tipo;


    @ColumnInfo(name = "seloTemporal")
    public String seloTemporal;


    @Ignore
    public Atualizacao() {

    }

    @Ignore
    public Atualizacao(String descricao, String seloTemporal) {
        this.descricao = descricao;
        this.seloTemporal = seloTemporal;
    }


    public Atualizacao(String descricao, int tipo, String seloTemporal) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.seloTemporal = seloTemporal;
    }


    public int getTipo() {
        return tipo;
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
