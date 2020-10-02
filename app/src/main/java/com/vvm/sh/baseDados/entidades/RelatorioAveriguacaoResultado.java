package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "relatorioAveriguacaoResultado",
        primaryKeys = {"","",""},
        foreignKeys = @ForeignKey(entity = RelatorioAveriguacao.class,
                parentColumns = "id",
                childColumns = "idRelatorio",
                onDelete = CASCADE))
public class RelatorioAveriguacaoResultado {

    @NonNull
    public int idTarefa; //não é necessário

    @NonNull
    public int idRelatorio;

    @NonNull
    public int idMedida;



    @NonNull
    public boolean implementado;



    @NonNull
    public String risco;

    @NonNull
    public int idPonderacao;

    @NonNull
    public Date data;




    @NonNull
    public int idTipoMedida;


    public String observacao;








    public RelatorioAveriguacaoResultado(@NonNull int idRelatorio, int idMedida) {
        this.idRelatorio = idRelatorio;
        this.idMedida = idMedida;
        this.implementado = true;
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
