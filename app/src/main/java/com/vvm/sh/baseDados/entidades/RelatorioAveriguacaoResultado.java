package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "relatorioAveriguacaoResultado",
        primaryKeys = {"","",""},
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class RelatorioAveriguacaoResultado {

    @NonNull
    public int idTarefa;

    @NonNull
    public String idRelatorio;

    @NonNull
    public int idMedida;

    @NonNull
    public int idImplementacao;

    @NonNull
    public int idRisco;

    @NonNull
    public int idPonderacao;


    @NonNull
    public int idTipoMedida;

    @NonNull
    public int data;

    public String observacao;

}
