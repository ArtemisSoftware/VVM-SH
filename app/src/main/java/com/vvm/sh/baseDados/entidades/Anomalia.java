package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.baseDados.entidades.Tarefa;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "anomalias",
        indices = {@Index(value="idTarefa", unique = false) },

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class Anomalia{


    @NonNull
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "data")
    public Date data;

    @NonNull
    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "observacao")
    public String observacao;

    @ColumnInfo(name = "contacto")
    public String contacto;

    @NonNull
    @ColumnInfo(name = "tipo")
    public String tipo;


    @Ignore
    public Anomalia() {
    }


    public Anomalia(int idTarefa, @NonNull Date data, @NonNull String descricao, String observacao, String contacto, @NonNull String tipo) {
        this.idTarefa = idTarefa;
        this.data = data;
        this.descricao = descricao;
        this.observacao = observacao;
        this.contacto = contacto;
        this.tipo = tipo;
    }

}
