package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "moradas",
        indices = {@Index(value="idTarefa", unique = false) },
        primaryKeys = {"idTarefa","id", "tipo"},
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class Morada {

    @NonNull
    public int idTarefa;


    @NonNull
    public String id;


    @NonNull
    public int tipo;



    @NonNull
    public String endereco;


    @NonNull
    public String cp4;


    @NonNull
    public String localidade;


    @Ignore
    public Morada() {
    }

    public Morada(int idTarefa, @NonNull String id, @NonNull int tipo, @NonNull String endereco, @NonNull String cp4, @NonNull String localidade) {
        this.idTarefa = idTarefa;
        this.id = id;
        this.tipo = tipo;
        this.endereco = endereco;
        this.cp4 = cp4;
        this.localidade = localidade;
    }


    @Override
    public String toString() {
        return endereco + "  " + cp4 + "-" + localidade;
    }
}
