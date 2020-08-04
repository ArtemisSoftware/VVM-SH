package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.constantes.Identificadores;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "crossSellingResultado",
        indices = {@Index(value="idTarefa", unique = false) },
        primaryKeys = {"id","idTarefa"},

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class CrossSellingResultado {


    @NonNull
    public int idTarefa;


    @NonNull
    public int id;


    @NonNull
    @ColumnInfo(name = "idAreaRecomendacao")
    public int idAreaRecomendacao;


    @NonNull
    @ColumnInfo(name = "idDimensao", defaultValue = Identificadores.SEM_VALOR)
    public int idDimensao;


    @NonNull
    @ColumnInfo(name = "idTipo", defaultValue = Identificadores.SEM_VALOR)
    public int idTipo;


    @Ignore
    public CrossSellingResultado(int idTarefa, int idAreaRecomendacao, int idProduto, Tipo dimensao, Tipo tipo) {

        this.idTarefa = idTarefa;
        this.id = idProduto;
        this.idAreaRecomendacao = idAreaRecomendacao;
        this.idDimensao = dimensao.id;
        this.idTipo = tipo.id;
    }


    @Ignore
    public CrossSellingResultado(int idTarefa, int idAreaRecomendacao, int idProduto) {
        this.idTarefa = idTarefa;
        this.id = idProduto;
        this.idAreaRecomendacao = idAreaRecomendacao;
    }

    public CrossSellingResultado(int idTarefa, int idAreaRecomendacao, int id, int idDimensao, int idTipo) {
        this.idTarefa = idTarefa;
        this.id = id;
        this.idAreaRecomendacao = idAreaRecomendacao;
        this.idDimensao = idDimensao;
        this.idTipo = idTipo;
    }
}
