package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import com.vvm.sh.util.constantes.Identificadores;

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

    @NonNull
    @ColumnInfo(name = "autorizado", defaultValue = Identificadores.VALOR_INT_0 + "")
    public boolean autorizado;

    @Ignore
    public EmailResultado(int idTarefa, String endereco, Tipo autorizacao, boolean autorizado) {
        this.idTarefa = idTarefa;
        this.endereco = endereco;
        this.autorizacao = autorizacao.descricao;
        this.idAutorizacao = autorizacao.id;
        this.autorizado = autorizado;
    }


    public EmailResultado(int idTarefa, String endereco, @NonNull String autorizacao, int idAutorizacao, boolean autorizado) {
        this.idTarefa = idTarefa;
        this.endereco = endereco;
        this.autorizacao = autorizacao;
        this.idAutorizacao = idAutorizacao;
        this.autorizado = autorizado;
    }
}
