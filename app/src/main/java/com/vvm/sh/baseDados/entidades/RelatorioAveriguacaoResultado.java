package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "relatorioAveriguacaoResultado",
        foreignKeys = @ForeignKey(entity = RelatorioAveriguacao.class,
                parentColumns = "id",
                childColumns = "idRelatorio",
                onDelete = CASCADE))
public class RelatorioAveriguacaoResultado {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public int idRelatorio;


    @NonNull
    public boolean implementado;

    public int idMedida;
    public String nc;


    public String risco;

    public int idPonderacao;

    public Date data;




    public RelatorioAveriguacaoResultado(@NonNull int idRelatorio, int idMedida, boolean implementado) {
        this.idRelatorio = idRelatorio;
        this.idMedida = idMedida;
        this.implementado = implementado;
    }

    public RelatorioAveriguacaoResultado(int idRelatorio, int idMedida, Tipo risco, @NonNull Date data) {
        this.idRelatorio = idRelatorio;
        this.idMedida = idMedida;
        this.risco = risco.descricao;
        this.idPonderacao = risco.id;
        this.data = data;
        this.implementado = false;
    }
}
