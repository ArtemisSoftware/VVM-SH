package com.vvm.sh.ui.tarefa.modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.vvm.sh.ui.agenda.modelos.Tarefa;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "emailsResultado",
        primaryKeys = {"idTarefa"},
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class EmailResultado {


    @NonNull
    public int idTarefa;

    @ColumnInfo(name = "endereco")
    public String endereco;


    @NonNull
    @ColumnInfo(name = "autorizacao")
    public String autorizacao;


    @NonNull
    @ColumnInfo(name = "idAutorizacao")
    public int idAutorizacao;


    @Ignore
    public EmailResultado(int idTarefa, String endereco, Tipo autorizacao) {
        this.idTarefa = idTarefa;
        this.endereco = endereco;
        this.autorizacao = autorizacao.descricao;
        this.idAutorizacao = autorizacao.id;
    }


    public EmailResultado(int idTarefa, String endereco, @NonNull String autorizacao, int idAutorizacao) {
        this.idTarefa = idTarefa;
        this.endereco = endereco;
        this.autorizacao = autorizacao;
        this.idAutorizacao = idAutorizacao;
    }
}
