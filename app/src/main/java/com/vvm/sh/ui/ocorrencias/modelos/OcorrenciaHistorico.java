package com.vvm.sh.ui.ocorrencias.modelos;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "ocorrenciasHistorico",
        indices = {@Index(value="idOcorrencia", unique = false) },

        foreignKeys = @ForeignKey(entity = Ocorrencia.class,
                parentColumns = "id",
                childColumns = "idOcorrencia",
                onDelete = CASCADE))
public class OcorrenciaHistorico {



    @NonNull
    public int idOcorrencia;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    @NonNull
    @ColumnInfo(name = "data")
    public Date data;


    @NonNull
    @ColumnInfo(name = "estado")
    public  String estado;


    @ColumnInfo(name = "observacao")
    public  String observacao;


    @NonNull
    @ColumnInfo(name = "departamento")
    public  String departamento;


    @Ignore
    public OcorrenciaHistorico() {
    }

    public OcorrenciaHistorico(int idOcorrencia, @NonNull Date data, @NonNull String estado, String observacao, @NonNull String departamento) {
        this.idOcorrencia = idOcorrencia;
        this.data = data;
        this.estado = estado;
        this.observacao = observacao;
        this.departamento = departamento;
    }
}
