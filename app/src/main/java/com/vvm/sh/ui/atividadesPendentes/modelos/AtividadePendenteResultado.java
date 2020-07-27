package com.vvm.sh.ui.atividadesPendentes.modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.ui.agenda.modelos.Tarefa;
import com.vvm.sh.util.constantes.Sintaxe;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "atividadesPendentesResultado",

        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "id",
                onDelete = CASCADE))
public class AtividadePendenteResultado {



    @NonNull
    public int id;


    @NonNull
    @ColumnInfo(name = "idEstado")
    public int idEstado;


    @ColumnInfo(name = "tempoExecucao")
    public String tempoExecucao;

    @ColumnInfo(name = "dataExecucao")
    public Date dataExecucao;

    @ColumnInfo(name = "idAnomalia")
    public int idAnomalia;

    @ColumnInfo(name = "observacao")
    public String observacao;


    @Ignore
    public AtividadePendenteResultado(int id, int idAnomalia, String observacao) {
        this.id = id;
        this.idEstado = 1;
        this.idAnomalia = idAnomalia;
        this.observacao = observacao;
    }

    @Ignore
    public AtividadePendenteResultado(int id, String tempoExecucao, Date dataExecucao) {
        this.id = id;
        this.idEstado = 2;
        this.tempoExecucao = tempoExecucao;
        this.dataExecucao = dataExecucao;
    }

    public AtividadePendenteResultado(int id, int idEstado, String tempoExecucao, Date dataExecucao, int idAnomalia, String observacao) {
        this.id = id;
        this.idEstado = idEstado;
        this.tempoExecucao = tempoExecucao;
        this.dataExecucao = dataExecucao;
        this.idAnomalia = idAnomalia;
        this.observacao = observacao;
    }
}
