package com.vvm.sh.baseDados.entidades;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tarefas")
public class Tarefa implements Parcelable {


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

    @NonNull
    @ColumnInfo(name = "app")
    public int app;


    //    @NonNull
//    @ColumnInfo(name = "api")
//    public int api;


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


    public Tarefa(int idTarefa, @NonNull String idUtilizador, @NonNull String ordem, @NonNull String prefixoCt, @NonNull Date data, int app/*, int api*/) {
        this.idTarefa = idTarefa;
        this.idUtilizador = idUtilizador;
        this.ordem = ordem;
        this.prefixoCt = prefixoCt;
        this.data = data;
        this.app = app;
        //this.api = api;
    }




    @Ignore
    protected Tarefa(Parcel in) {
        idTarefa = in.readInt();
        idUtilizador = in.readString();
        ordem = in.readString();
        prefixoCt = in.readString();
        app = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idTarefa);
        dest.writeString(idUtilizador);
        dest.writeString(ordem);
        dest.writeString(prefixoCt);
        dest.writeInt(app);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tarefa> CREATOR = new Creator<Tarefa>() {
        @Override
        public Tarefa createFromParcel(Parcel in) {
            return new Tarefa(in);
        }

        @Override
        public Tarefa[] newArray(int size) {
            return new Tarefa[size];
        }
    };
}
