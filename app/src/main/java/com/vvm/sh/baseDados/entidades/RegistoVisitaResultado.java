package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "registoVisitaResultado",

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class RegistoVisitaResultado {


    @PrimaryKey
    public int idTarefa;

    @NonNull
    public String recebidoPor;

    @NonNull
    public String funcao;

    public String observacao;

    @NonNull
    @ColumnInfo(name = "homologado", defaultValue = Identificadores.VALOR_INT_0 + "")
    public boolean homologado;


    @NonNull
    @ColumnInfo(name = "sincronizacao", defaultValue = Sintaxe.Codigos.NAO_SELECIONADO)
    public boolean sincronizacao;



    public RegistoVisitaResultado(int idTarefa, @NonNull String recebidoPor, @NonNull String funcao, String observacao, @NonNull boolean homologado) {
        this.idTarefa = idTarefa;
        this.recebidoPor = recebidoPor;
        this.funcao = funcao;

        if(observacao.equals(Sintaxe.SEM_TEXTO) == false){
            this.observacao = observacao;
        }

        this.homologado = homologado;
    }
}
