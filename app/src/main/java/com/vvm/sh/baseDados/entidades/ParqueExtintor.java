package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
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



    @ColumnInfo(name = "idServico")
    public String idServico;

    @ColumnInfo(name = "contrato")
    public String contrato;

    @ColumnInfo(name = "idExtintor")
    public String idExtintor;

    @ColumnInfo(name = "quantidade")
    public int quantidade;

    @ColumnInfo(name = "dataValidade")
    public Date dataValidade;

    @ColumnInfo(name = "idMorada")
    public int idMorada;


    public ParqueExtintor(int idTarefa, String idServico, String contrato, String idExtintor, int quantidade, Date dataValidade, int idMorada) {
        this.idTarefa = idTarefa;
        this.idServico = idServico;
        this.contrato = contrato;
        this.idExtintor = idExtintor;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
        this.idMorada = idMorada;
    }
}
