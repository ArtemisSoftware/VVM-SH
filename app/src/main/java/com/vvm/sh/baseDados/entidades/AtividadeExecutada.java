package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.vvm.sh.baseDados.entidades.Tarefa;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "atividadeExecutadas",
        indices = {@Index(value="idTarefa", unique = false) },
        primaryKeys = {"idTarefa","idServico", "ordem"},
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                                parentColumns = "idTarefa",
                                childColumns = "idTarefa",
                                onDelete = CASCADE))
public class AtividadeExecutada {


    @NonNull
    public int idTarefa;


    @NonNull
    public String idServico;


    @NonNull
    @ColumnInfo(name = "ordem")
    public String ordem;




    @NonNull
    @ColumnInfo(name = "descricao")
    public String descricao;

    @NonNull
    @ColumnInfo(name = "dataProgramada")
    public Date dataProgramada;

    @NonNull
    @ColumnInfo(name = "dataExecucao")
    public Date dataExecucao;


    @Ignore
    public AtividadeExecutada() {

    }

    public AtividadeExecutada(int idTarefa, @NonNull String ordem, String idServico, String descricao, Date dataProgramada, Date dataExecucao) {
        this.idTarefa = idTarefa;
        this.ordem = ordem;
        this.idServico = idServico;
        this.descricao = descricao;
        this.dataProgramada = dataProgramada;
        this.dataExecucao = dataExecucao;
    }
}
