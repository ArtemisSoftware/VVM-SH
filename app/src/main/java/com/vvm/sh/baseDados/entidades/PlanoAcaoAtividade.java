package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "planoAcaoAtividade",
        indices = {@Index(value="idTarefa", unique = false) },

        foreignKeys = @ForeignKey(entity = PlanoAcao.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class PlanoAcaoAtividade {


    @NonNull
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    @ColumnInfo(name = "servId")
    public String servId;

    @NonNull
    @ColumnInfo(name = "descricaoSimples")
    public String descricaoSimples;

    @NonNull
    @ColumnInfo(name = "descricao")
    public String descricao;

    @NonNull
    @ColumnInfo(name = "equipaSst")
    public String equipaSst;

    @NonNull
    @ColumnInfo(name = "sempreNecessario")
    public int sempreNecessario;


    @NonNull
    @ColumnInfo(name = "dataProgramada")
    public String dataProgramada;

    @ColumnInfo(name = "dataExecucao")
    public String dataExecucao;


    @NonNull
    @ColumnInfo(name = "reprogramada")
    public int reprogramada;

    @ColumnInfo(name = "observacao")
    public String observacao;

    @NonNull
    @ColumnInfo(name = "fixo")
    public int fixo;

    @ColumnInfo(name = "anuidade")
    public String anuidade;


}
