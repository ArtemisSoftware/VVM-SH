package com.vvm.sh.ui.agenda;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tarefas")
public class Tarefa {


    @PrimaryKey
    @NonNull
    public int idTarefa;

    @NonNull
    @ColumnInfo(name = "idUtilizador")
    public String idUtilizador;

    @NonNull
    @ColumnInfo(name = "ordem")
    public String seloTemporal;

    @NonNull
    @ColumnInfo(name = "prefixoCt")
    public String prefixoCt;

    //@NonNull
    //@ColumnInfo(name = "data")
    //private String data;


    @Ignore
    public Tarefa() {

    }

    @Ignore
    public Tarefa(@NonNull String idUtilizador, @NonNull String seloTemporal, @NonNull String prefixoCt) {
        this.idUtilizador = idUtilizador;
        this.seloTemporal = seloTemporal;
        this.prefixoCt = prefixoCt;
    }



    public Tarefa(int idTarefa, @NonNull String idUtilizador, @NonNull String seloTemporal, @NonNull String prefixoCt) {
        this.idTarefa = idTarefa;
        this.idUtilizador = idUtilizador;
        this.seloTemporal = seloTemporal;
        this.prefixoCt = prefixoCt;
    }
}
