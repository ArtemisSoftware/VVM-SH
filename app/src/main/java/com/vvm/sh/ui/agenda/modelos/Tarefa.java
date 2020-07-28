package com.vvm.sh.ui.agenda.modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tarefas")
public class Tarefa {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int idTarefa;

    @NonNull
    @ColumnInfo(name = "idUtilizador")
    public String idUtilizador;

    @NonNull
    @ColumnInfo(name = "ordem")
    public String ordem;

    @NonNull
    @ColumnInfo(name = "prefixoCt")
    public String prefixoCt;

    @NonNull
    @ColumnInfo(name = "data")
    public Date data;


    @Ignore
    public Tarefa() {

    }

    @Ignore
    public Tarefa(@NonNull String idUtilizador, @NonNull String ordem, @NonNull String prefixoCt, @NonNull Date data) {
        this.idUtilizador = idUtilizador;
        this.ordem = ordem;
        this.prefixoCt = prefixoCt;
        this.data = data;
    }



    public Tarefa(int idTarefa, @NonNull String idUtilizador, @NonNull String ordem, @NonNull String prefixoCt, @NonNull Date data) {
        this.idTarefa = idTarefa;
        this.idUtilizador = idUtilizador;
        this.ordem = ordem;
        this.prefixoCt = prefixoCt;
        this.data = data;
    }

    /**
     * Metodo que permite obter a marca e a ordem da tarefa
     * @return a marca e a ordem
     */
    public String obterMarcaOrdem(){
        return prefixoCt + " / " + ordem;
    }
}
