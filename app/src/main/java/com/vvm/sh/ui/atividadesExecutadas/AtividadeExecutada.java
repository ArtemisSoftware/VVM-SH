package com.vvm.sh.ui.atividadesExecutadas;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.ui.agenda.modelos.Tarefa;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "atividadeExecutadas",
        indices = {@Index("idTarefa")},
        primaryKeys = {"idTarefa","ordem"},
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                                parentColumns = "idTarefa",
                                childColumns = "idTarefa",
                                onDelete = CASCADE))
public class AtividadeExecutada {


    @NonNull
    public int idTarefa;


    @NonNull
    public String ordem;



    @ColumnInfo(name = "idServico")
    public String idServico;

    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "dataProgramada")
    public Date dataProgramada;

    @ColumnInfo(name = "dataExecucao")
    public Date dataExecucao;
}
