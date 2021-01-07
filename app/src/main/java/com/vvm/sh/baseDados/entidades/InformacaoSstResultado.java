package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "informacaoSstResultado",

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class InformacaoSstResultado {


    @PrimaryKey
    public int idTarefa;


    @NonNull
    @ColumnInfo(name = "sincronizacao", defaultValue = Sintaxe.Codigos.NAO_SELECIONADO)
    public boolean sincronizacao;


    @Ignore
    public InformacaoSstResultado(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public InformacaoSstResultado(int idTarefa, boolean sincronizacao) {
        this.idTarefa = idTarefa;
        this.sincronizacao = sincronizacao;
    }
}
