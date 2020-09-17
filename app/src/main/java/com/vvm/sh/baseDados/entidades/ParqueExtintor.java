package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "parqueExtintores",
        indices = {@Index(value="idTarefa", unique = false) },


        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class ParqueExtintor {

    @NonNull
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    @NonNull
    @ColumnInfo(name = "idServico")
    public String idServico;

    @NonNull
    @ColumnInfo(name = "contrato")
    public String contrato;

    @NonNull
    @ColumnInfo(name = "idExtintor")
    public String idExtintor;

    @NonNull
    @ColumnInfo(name = "quantidade")
    public int quantidade;

    @NonNull
    @ColumnInfo(name = "dataValidade")
    public Date dataValidade;

    @NonNull
    @ColumnInfo(name = "idMorada")
    public String idMorada;


    @Ignore
    public ParqueExtintor() {
    }

    public ParqueExtintor(int idTarefa, String idServico, String contrato, String idExtintor, int quantidade, Date dataValidade, String idMorada) {
        this.idTarefa = idTarefa;
        this.idServico = idServico;
        this.contrato = contrato;
        this.idExtintor = idExtintor;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
        this.idMorada = idMorada;
    }
}
